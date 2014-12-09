package com.wt.studio.plugin.querydesigner.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.DrillDownAdapter;

import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;

public class CreateTableSqlPageThree extends WizardPage
{

	private SqlAreaModel sqlAreaModel;
	private TreeViewer treeViewer;
	private DrillDownAdapter drillDownAdapter;

	class TreeObject implements IAdaptable
	{
		private String name;
		private TreeParent parent;

		public TreeObject(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		public void setParent(TreeParent parent)
		{
			this.parent = parent;
		}

		public TreeParent getParent()
		{
			return parent;
		}

		public String toString()
		{
			return getName();
		}

		public Object getAdapter(Class key)
		{
			return null;
		}
	}

	class TreeParent extends TreeObject
	{
		private ArrayList<TreeObject> children;

		public TreeParent(String name)
		{
			super(name);
			children = new ArrayList<TreeObject>();
		}

		public void addChild(TreeObject child)
		{
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child)
		{
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren()
		{
			return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
		}

		public boolean hasChildren()
		{
			return children.size() > 0;
		}
	}

	class ViewLabelProvider extends LabelProvider
	{

		public String getText(Object obj)
		{
			return obj.toString();
		}

		public Image getImage(Object obj)
		{
			return null;

		}
	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider
	{
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput)
		{
		}

		public void dispose()
		{
		}

		public Object[] getElements(Object parent)
		{
			return ((List) parent).toArray();
		}

		public Object getParent(Object child)
		{
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent)
		{
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent)
		{
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		private void initialize()
		{

		}

	}

	public CreateTableSqlPageThree(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		composite.setLayout(gridLayout);
		final Button b1 = new Button(composite, SWT.NULL);
		b1.setText("SQL对象");
		DragSource source = new DragSource(b1, DND.DROP_MOVE | DND.DROP_COPY);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		source.addDragListener(new DragSourceListener() {

			@Override
			public void dragFinished(DragSourceEvent arg0)
			{

			}

			@Override
			public void dragSetData(DragSourceEvent event)
			{
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = b1.getText();
				}

			}

			@Override
			public void dragStart(DragSourceEvent arg0)
			{

			}

		});
		Composite treeComposite = new Composite(composite, SWT.NULL);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 250;
		gridData.widthHint = 70;
		treeComposite.setLayoutData(gridData);
		treeComposite.setLayout(new FillLayout());
		treeViewer = new TreeViewer(treeComposite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(treeViewer);
		treeViewer.setContentProvider(new ViewContentProvider());
		treeViewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		List<TreeParent> Data = new ArrayList();
		TreeParent tree1 = new TreeParent("父节点一");
		tree1.addChild(new TreeObject("子节点"));
		tree1.addChild(new TreeObject("子节点"));
		TreeParent tree2 = new TreeParent("父节点二");
		Data.add(tree1);
		Data.add(tree2);
		treeViewer.setInput(Data);
		DropTarget target = new DropTarget(treeViewer.getTree(), DND.DROP_DEFAULT | DND.DROP_MOVE);
		final TextTransfer textTransfer = TextTransfer.getInstance();
		final FileTransfer fileTransfer = FileTransfer.getInstance();
		Transfer[] types = new Transfer[] { fileTransfer, textTransfer };
		target.setTransfer(types);
		target.addDropListener(new DropTargetListener() {

			@Override
			public void dragEnter(DropTargetEvent event)
			{
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;

			}

			@Override
			public void dragLeave(DropTargetEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void dragOperationChanged(DropTargetEvent event)
			{
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;

			}

			@Override
			public void dragOver(DropTargetEvent event)
			{
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;

			}

			@Override
			public void drop(DropTargetEvent event)
			{
				Button item = (Button) event.item;
				if (item == null) {
					event.detail = DND.DROP_NONE;
					return;
				}
				if (fileTransfer.isSupportedType(event.currentDataType)) {
					String[] files = (String[]) event.data;
					if (files != null && files.length > 0) {
						item.setText(files[0]);
					}
				}
				if (textTransfer.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;
					if (text != null) {
						item.setText(text);
					}
				}
			}

			@Override
			public void dropAccept(DropTargetEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		});
		setControl(composite);
	}

}
