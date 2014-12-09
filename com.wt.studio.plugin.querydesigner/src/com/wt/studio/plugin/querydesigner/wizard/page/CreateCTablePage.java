package com.wt.studio.plugin.querydesigner.wizard.page;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.querydesigner.model.InjectModel;

public class CreateCTablePage extends WizardPage
{
	private Text html;
	private Text css;
	private Text js;
	private InjectModel injectModel = new InjectModel();

	public InjectModel getInjectModel()
	{
		return injectModel;
	}

	public void setModel(InjectModel model)
	{
		this.injectModel = model;
	}

	public CreateCTablePage(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent)
	{
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		composite.setLayout(gridLayout);

		Group group = new Group(composite, SWT.NULL);
		FillLayout fillLayout = new FillLayout();
		fillLayout.spacing = 5;
		group.setLayout(new FillLayout());
		group.setText("HTML");
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		group.setLayoutData(gridData);
		html = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);
		group = new Group(composite, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("CSS");
		group.setLayoutData(gridData);
		css = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);
		group = new Group(composite, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("JS");
		group.setLayoutData(gridData);
		js = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);
		ModifyListener listener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				Widget w = e.widget;
				if (w == html) {
					injectModel.setHtml(html.getText());
				} else if (w == css) {
					injectModel.setCss(css.getText());
				} else if (w == js) {
					injectModel.setJs(js.getText());
				}

			}

		};
		html.addModifyListener(listener);
		css.addModifyListener(listener);
		js.addModifyListener(listener);
		setControl(composite);
	}

	public boolean isPageComplete()
	{
		if (html != null && css != null && js != null) {
			String shtml = html.getText().trim();
			String scss = css.getText().trim();
			String sjs = js.getText().trim();
			if (!"".equals(shtml) && !"".equals(scss) && !"".equals(sjs)) {
				return true;
			}
		}
		return false;
	}

	public void setData(InjectModel model)
	{
		html.setText(model.getHtml());
		css.setText(model.getCss());
		js.setText(model.getJs());
	}

	public IWizardPage getNextPage()
	{
		return super.getNextPage();
	}
}
