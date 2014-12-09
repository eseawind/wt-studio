package com.wt.studio.plugin.querydesigner.views;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.ViewPart;

import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.model.DataSourceModel;
import com.wt.studio.plugin.querydesigner.panel.DataObjectPanel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.utils.DataSourceUtils;
import com.wt.studio.plugin.querydesigner.utils.Table;
import com.wt.studio.plugin.querydesigner.wizard.CreateDataObjectWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateDataObjectPageOne;

public class QueryDesignerView extends ViewPart
{
	public static final String ID = "com.hirisun.ide.plugin.querydesigner.views.QueryDesignerView";
	private IConnectionProfile connectionProfile;
	private DataObjectPanel dataObjectPanel;

	@Override
	public void createPartControl(Composite parent)
	{
		parent.setLayout(new FillLayout());
		this.intiDataObjectComposite(parent);
		this.contributeToActionBars();
	}

	private void intiDataObjectComposite(Composite parent)
	{
		dataObjectPanel = new DataObjectPanel(parent, SWT.NULL);
		dataObjectPanel.getDataObjectTree().addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e)
			{
			}

			@Override
			public void mouseDown(MouseEvent e)
			{
			}

			@Override
			public void mouseDoubleClick(MouseEvent e)
			{
				Tree tree = (Tree) e.getSource();
				TreeItem[] items = tree.getSelection();
				if (items.length > 0) {
					final Item item = items[0];
					if ("DataObject".equals(item.getData("type"))) {
						CreateDataObjectWizard createDataObjectWizard = new CreateDataObjectWizard(
								QueryDesignerView.this);
						final WizardDialog dialog = new WizardDialog(getViewSite().getShell(),
								createDataObjectWizard);
						dialog.addPageChangedListener(new IPageChangedListener() {
							@Override
							public void pageChanged(PageChangedEvent event)
							{
								if (event.getSelectedPage() instanceof CreateDataObjectPageOne) {
									((CreateDataObjectPageOne) dialog.getCurrentPage())
											.setData((DataObjectModel) item.getData());
									dialog.removePageChangedListener(this);
								}
							}
						});
						dialog.open();

					}
				}
			}
		});
	}

	private IConnectionProfile selectDataSource()
	{
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getViewSite().getShell(), new LabelProvider() {
					@Override
					public String getText(Object element)
					{
						IConnectionProfile connectionProfile = (IConnectionProfile) element;
						return connectionProfile.getName();
					}
				});
		dialog.setElements(CommonEclipseUtil.getConnectionProfiles());
		dialog.setTitle("DataSource Explorer");
		dialog.setMessage("Select a datasource");
		dialog.setMultipleSelection(false);
		if (dialog.open() != Window.OK) {
			// this.hideView();
			return null;
		}
		Object[] result = dialog.getResult();
		return result.length > 0 ? (IConnectionProfile) result[0] : null;
	}

	private Table selectTable()
	{
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				getViewSite().getShell(), new LabelProvider() {
					@Override
					public String getText(Object element)
					{
						Table table = (Table) element;
						return table.getTableName();
					}
				});
		dialog.setElements(CommonEclipseUtil.getTablesFromProfile(connectionProfile).toArray());
		dialog.setTitle("Tables Explorer");
		dialog.setMessage("Select a table");
		dialog.setMultipleSelection(false);
		if (dialog.open() != Window.OK) {
			return null;
		}
		return (Table) dialog.getFirstResult();
	}

	private void createDataObjectWizard()
	{
		new WizardDialog(this.getViewSite().getShell(), new CreateDataObjectWizard(this)).open();
	}

	private void importDataObjectWizard()
	{
		connectionProfile = this.selectDataSource();
		if (connectionProfile == null) {
			return;
		}

		Table table = selectTable();
		if (table != null) {
			dataObjectPanel.addItem(connectionProfile, table.getTableName());
		}
	}

	private void contributeToActionBars()
	{
		makeActions();
		fillLocalToolBar(this.getViewSite().getActionBars().getToolBarManager());
		fillLocalPullDown(this.getViewSite().getActionBars().getMenuManager());
	}

	private void makeActions()
	{
		saveAction = new Action() {
			@Override
			public void run()
			{
				super.run();
				for (DataSourceModel model : dataObjectPanel.getDataSourceModels()) {
					DataSourceUtils.insert(model);
				}
				// List<DataObjectModel> models = dataObjectPanel.getDataObjectModels();
				// CommonEclipseUtil.showInfomation(models.size() + "个数据对象");
			}
		};
		saveAction.setText("保存");
		saveAction.setToolTipText("保存到数据库");
		saveAction.setImageDescriptor(CommonEclipseUtil.getImage("icons/save.png"));

		refreshAction = new Action() {
			@Override
			public void run()
			{
				super.run();
				dataObjectPanel.reLoadDataObject();
			}
		};
		refreshAction.setText("刷新");
		refreshAction.setToolTipText("重新载入数据对象");
		refreshAction.setImageDescriptor(CommonEclipseUtil.getImage("icons/refresh.png"));

		newAction = new Action() {
			@Override
			public void run()
			{
				super.run();
				connectionProfile = selectDataSource();
				if (connectionProfile == null) {
					return;
				}
				QueryDesignerView.this.createDataObjectWizard();
			}
		};
		newAction.setText("新建");
		newAction.setToolTipText("新建数据对象");
		newAction.setImageDescriptor(CommonEclipseUtil.getImage("icons/new.png"));

		impAction = new Action() {
			@Override
			public void run()
			{
				super.run();
				QueryDesignerView.this.importDataObjectWizard();
			}
		};
		impAction.setText("导入");
		impAction.setToolTipText("导入数据对象");
		impAction.setImageDescriptor(CommonEclipseUtil.getImage("icons/import.png"));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(newAction);
		manager.add(impAction);
		manager.add(saveAction);
		manager.add(refreshAction);
	}

	private void fillLocalPullDown(IMenuManager manager)
	{
		manager.add(newAction);
		manager.add(impAction);
		manager.add(saveAction);
		manager.add(refreshAction);
	}

	private void fillContextMenu(IMenuManager manager)
	{
		manager.add(newAction);
		manager.add(impAction);
		manager.add(saveAction);
		manager.add(refreshAction);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public IConnectionProfile getConnectionProfile()
	{
		return connectionProfile;
	}

	public DataObjectPanel getDataObjectPanel()
	{
		return dataObjectPanel;
	}

	private void hideView()
	{
		Display.getCurrent().asyncExec(new Runnable() {
			@Override
			public void run()
			{
				CommonEclipseUtil.getWorkbenchPage().hideView(QueryDesignerView.this);
			}
		});
	}

	@Override
	public void init(IViewSite site) throws PartInitException
	{
		super.init(site);
	}

	@Override
	public void setFocus()
	{
	}

	private Action newAction;
	private Action impAction;
	private Action saveAction;
	private Action refreshAction;
}
