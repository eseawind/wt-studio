package com.wt.studio.plugin.querydesigner.wizard.page;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.wt.studio.plugin.querydesigner.config.Configuration;
import com.wt.studio.plugin.querydesigner.model.ChartGroup;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.CreateChartSqlWizard;

public class CreateSelectChartPage extends WizardPage
{
	String[] types = new String[] { "图形", "计量表" };
	private String[] pics;
	private String[] picValues;
	private Composite showArea;
	private Group picGroup;
	private Label showPicName;
	String[] jiliang = new String[] { "刻度盘", "垂直条形", "水平条形", "灯泡" };
	String[] tu = new String[] { "bar1", "bar4", "bar14", "line1", "line3", "redar1", "chord1",
			"force4", "scatter1" };
	String[] name = new String[] { "标准折线图", "标准条形图", "彩虹柱形图", "标准和弦图", "树状关系图", "K线图", "标准饼图",
			"标准雷达图", "标准地图" };

	private Combo group;
	private List<ChartGroup> groups;
	private SqlSet sqlSet = new SqlSet();

	public CreateSelectChartPage(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
		this.groups = Configuration.getInstance().getChartGroups();
		pics = new String[groups.size()];
		picValues = new String[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			pics[i] = groups.get(i).getChartGroupDisplayName();
			picValues[i] = groups.get(i).getChartGroupName();
		}
	}

	@Override
	public void createControl(Composite parent)
	{
		// TODO Auto-generated method stub
		// parent.setLayout(new FillLayout());
		final Composite com = new Composite(parent, SWT.NULL);
		com.setLayout(new GridLayout());
		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		final Composite selectArea = new Composite(com, SWT.NULL);
		selectArea.setLayout(new GridLayout(3, false));
		selectArea.setLayoutData(data);
		final Composite comboCom = new Composite(selectArea, SWT.NULL);
		comboCom.setLayout(new GridLayout(2, false));
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		selectArea.setLayoutData(data);
		data = new GridData();
		data.widthHint = 120;
		group = new Combo(comboCom, SWT.READ_ONLY);
		group.setItems(pics);
		group.setLayoutData(data);
		group.select(0);

		final ModifyListener groupModify = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0)
			{
				// TODO Auto-generated method stub
				DrawShowPicArea(group.getSelectionIndex());
			}

		};
		group.addModifyListener(groupModify);
		data = new GridData();
		data.widthHint = 120;
		Composite showCom = new Composite(selectArea, SWT.NULL);
		showCom.setLayout(new GridLayout());
		showPicName = new Label(showCom, SWT.NULL);
		showPicName.setText("线形图");
		showPicName.setLayoutData(data);

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		ScrolledComposite sc = new ScrolledComposite(com, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setLayoutData(data);
		sc.setLayout(new FillLayout());
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		showArea = new Composite(sc, SWT.NULL);

		showArea.setLayout(new FillLayout());
		showArea.setLayoutData(data);
		DrawShowPicArea(0);
		setChartType(groups.get(0).getCharts().get(0));
		sc.setContent(showArea);// 设置面板的有效性
		sc.setMinSize(new Point(800, 500));// 面板的最小大小
		setControl(com);
	}

	public IWizardPage getNextPage()
	{

		return super.getNextPage();
	}

	public void DrawShowPicArea(int num)
	{
		if (picGroup != null) {
			picGroup.dispose();
		}
		List<ChartType> charts = groups.get(num).getCharts();
		picGroup = new Group(showArea, SWT.NULL);
		picGroup.setText("图形区域");
		picGroup.setLayout(new GridLayout(4, false));
		for (ChartType type : charts) {
			final Button label = new Button(picGroup, SWT.NULL);
			GridData data = new GridData();
			data.heightHint = 95;
			data.widthHint = 180;
			label.setLayoutData(data);
			label.setData(type);
			String url = type.getImgUrl();
			if (type.getImgUrl() != null && !"null".equals(type.getImgUrl())) {
				label.setImage(CommonEclipseUtil.getImage(type.getImgUrl()).createImage());
			} else {
				label.setImage(CommonEclipseUtil.getImage("icons/example/bar1.png").createImage());
			}
			label.setToolTipText(type.getDisplayName());
			label.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0)
				{

				}

				@Override
				public void widgetSelected(SelectionEvent arg0)
				{
					// TODO Auto-generated method stub
					showPicName.setText(label.getToolTipText());
					setChartType((ChartType) label.getData());
				}

			});
		}
		showPicName.setText(groups.get(num).getCharts().get(0).getDisplayName());
		showArea.layout();
	}

	private void setChartType(ChartType chart)
	{
		// TODO Auto-generated method stub

		sqlSet.setChartType(chart);
		refreshPageOne(sqlSet);
	}

	private void refreshPageOne(SqlSet sqlSet)
	{
		CreateSqlChartPage pageSql = (CreateSqlChartPage) getWizard().getPage(
				CreateChartSqlWizard.PAGE_SQL);
		pageSql.CreateSqlSet(sqlSet);
	}

	public void setData(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
		String groupName = this.sqlSet.getChartType().getChartGroup();
		int index = Arrays.asList(picValues).indexOf(groupName);
		if (index == -1)
			index = 0;
		group.select(index);
		DrawShowPicArea(index);
		showPicName.setText(this.sqlSet.getChartType().getDisplayName());
		CreateSqlChartPage pageSql = (CreateSqlChartPage) getWizard().getPage(
				CreateChartSqlWizard.PAGE_SQL);
		pageSql.setData(sqlSet);
	}

}
