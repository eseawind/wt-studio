package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class Diagram extends Element implements IPropertySource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8260380437323503852L;
	private List<BlockModel> blockModels = new ArrayList<BlockModel>();
	private List<QueryBlockModel> queryBlockModels = new ArrayList<QueryBlockModel>();
	private List<ChartBlockModel> chartBlockModels = new ArrayList<ChartBlockModel>();
	private List<TableModel> tables = new ArrayList<TableModel>();

	public static final String PROP_BLOCKMODELS = "blockModels";
	public static final String PROP_QUERYBLOCKMODELS = "queryBlockModels";
	public static final String PROP_CHARTBLOCKMODELS = "chartBlockModels";
	public static final String PROP_TABLES = "tables", PROP_ID = "ID*",
			PROP_NAME = "功能名称", PROP_DESC = "描述", PROP_STATUS = "状态",
			PROP_SHOW_DATA = "默认显示数据";
	private String id = "";
	private String name = "";
	private String desc = "";
	private String status = "01";
	private Integer defaultShowData = 0;
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_DESC, PROP_DESC),
				new ComboBoxPropertyDescriptor(PROP_STATUS, PROP_STATUS,
						new String[] { "启用", "停用" }),
				new ComboBoxPropertyDescriptor(PROP_SHOW_DATA, PROP_SHOW_DATA,
						new String[] { "否", "是" }) };
	}

	public void addBlockModel(BlockModel blockModel) {
		blockModels.add(blockModel);
		fireStructureChange(PROP_BLOCKMODELS, blockModels);
	}

	public void removeBlockModel(BlockModel blockModel) {
		blockModels.remove(blockModel);
		fireStructureChange(PROP_BLOCKMODELS, blockModels);
	}

	public List<BlockModel> getBlockModels() {
		return blockModels;
	}

	public void addQueryBlockModel(QueryBlockModel queryBlockModel) {
		queryBlockModels.add(queryBlockModel);
		fireStructureChange(PROP_QUERYBLOCKMODELS, PROP_QUERYBLOCKMODELS);
	}

	public void removeQueryBlockModel(QueryBlockModel model) {
		queryBlockModels.remove(model);
		fireStructureChange(PROP_QUERYBLOCKMODELS, PROP_QUERYBLOCKMODELS);
	}

	public List<QueryBlockModel> getQueryBlockModels() {
		queryBlockModels.clear();
		// List<BlockModel> allBlockModels = new ArrayList<BlockModel>();
		for (BlockModel block : this.getBlockModels()) {
			getQueryBlockModel(block);
		}
		return queryBlockModels;
	}

	public void getQueryBlockModel(BlockModel block) {
		for (Element element : block.getElements()) {
			if (element instanceof BlockModel)
				getQueryBlockModel((BlockModel) element);
			else if (element instanceof QueryBlockModel)
				queryBlockModels.add((QueryBlockModel) element);
		}
	}

	public void addChartBlockModel(ChartBlockModel chartBlockModel) {
		chartBlockModels.add(chartBlockModel);
		fireStructureChange(PROP_CHARTBLOCKMODELS, PROP_CHARTBLOCKMODELS);
	}

	public void removeChartBlockModel(ChartBlockModel model) {
		chartBlockModels.remove(model);
		fireStructureChange(PROP_CHARTBLOCKMODELS, PROP_CHARTBLOCKMODELS);
	}

	public List<ChartBlockModel> getChartBlockModels() {
		chartBlockModels.clear();
		for (BlockModel block : this.getBlockModels()) {
			getChartBlockModel(block);
		}
		return chartBlockModels;
	}

	public void getChartBlockModel(BlockModel block) {
		for (Element element : block.getElements()) {
			if (element instanceof BlockModel)
				getChartBlockModel((BlockModel) element);
			else if (element instanceof ChartBlockModel)
				chartBlockModels.add((ChartBlockModel) element);
		}
	}

	public void removeTable(TableModel tableModel) {
		this.tables.remove(tableModel);
		fireStructureChange(PROP_TABLES, tables);
	}

	public void addTable(TableModel table) {
		this.tables.add(table);
		fireStructureChange(PROP_TABLES, tables);
	}

	public List<TableModel> getTables() {
		tables.clear();
		for (BlockModel block : this.getBlockModels()) {
			getTableModel(block);
		}
		return tables;
	}

	public void getTableModel(BlockModel block) {
		for (Element element : block.getElements()) {
			if (element instanceof BlockModel)
				getTableModel((BlockModel) element);
			else if (element instanceof TableModel)
				tables.add((TableModel) element);
		}
	}

	@Override
	public Rectangle getRectangle() {
		return rectangle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDefaultShowData() {
		return defaultShowData;
	}

	public void setDefaultShowData(Integer defaultShowData) {
		this.defaultShowData = defaultShowData;
	}

	public String getId() {
		return id.trim();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_DESC.equals(id)) {
			return this.getDesc();
		} else if (PROP_STATUS.equals(id)) {
			return this.getStatus().equals("01") ? 0 : 1;
		} else if (PROP_SHOW_DATA.equals(id)) {
			return this.getDefaultShowData();
		} else if (PROP_ID.equals(id)) {
			return this.getId();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		} else if (PROP_DESC.equals(id)) {
			this.setDesc((String) value);
		} else if (PROP_STATUS.equals(id)) {
			int status = (Integer) value;
			this.setStatus(status == 0 ? "01" : "02");
		} else if (PROP_SHOW_DATA.equals(id)) {
			this.setDefaultShowData((Integer) value);
		} else if (PROP_ID.equals(id)) {
			this.setId((String) value);
		}
	}
}
