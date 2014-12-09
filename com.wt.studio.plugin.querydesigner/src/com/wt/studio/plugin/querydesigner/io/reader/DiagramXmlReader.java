package com.wt.studio.plugin.querydesigner.io.reader;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.querydesigner.config.Configuration;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.PageModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamDateModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamDeptTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamHiddenModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamInputModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamUserTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.querydesigner.model.ChartGroup;
import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.InjectModel;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;

public class DiagramXmlReader
{

	public Diagram parseDiagram(String xml) throws DocumentException, MalformedURLException
	{
		/*File file = new File("D:\\龚泽强\\HEA Studio V1.1.0-SR1-win32-x86_32" + "\\HEA Studio\\"
				+ "workspace\\com.hirisun.ide.plugin.querydesigner\\src\\model.xml");
		URL url = file.toURI().toURL();*/
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Element diagramElement = root.element("diagram");
		Diagram diagram = new Diagram();
		diagram.setName(diagramElement.attributeValue("name"));
		diagram.setId(diagramElement.attributeValue("id"));
		diagram.setDesc(diagramElement.attributeValue("des"));
		diagram.setStatus(diagramElement.attributeValue("status"));
		diagram.setDefaultShowData(Integer.parseInt((diagramElement
				.attributeValue("defaultShowData"))));
		Element verticalBlockModel = diagramElement.element("pageModel");
		if (verticalBlockModel == null) {
			verticalBlockModel = diagramElement.element("verticalBlockModel");
		}
		PageModel page = new PageModel();
		page.setRectangle(parseRectangle(verticalBlockModel.attributeValue("layout")));
		Iterator block = verticalBlockModel.elementIterator();
		while (block.hasNext()) {
			Element child = (Element) block.next();
			if (child.getName().equals("verticalBlockModel"))
				parseVerticalBlockModelXml(page, child);
			else if (child.getName().equals("horizontalBlockModel"))
				parseHorizontalBlockModelXml(page, child);
			else if (child.getName().equals("queryBlockModel"))
				parseQueryBlockModelXml(page, child);
			else if (child.getName().equals("chartBlockModel"))
				parseChartBlockModelXml(page, child);
			else if (child.getName().equals("tableModel"))
				parseTableModelXml(page, child);
			else if (child.getName().equals("frameModel"))
				parseFrameModelXml(page, child);
			else if (child.getName().equals("titleModel"))
				parseTitleModelXml(page, child);
			else if (child.getName().equals("textAreaModel"))
				parseTextAreaModelXml(page, child);
			else if (child.getName().equals("htmlAreaModel"))
				parseHtmlAreaModelXml(page, child);
		}
		diagram.addBlockModel(page);
		return diagram;

	}

	private Rectangle parseRectangle(String layout)
	{

		if (layout == null) {
			return new Rectangle(0, 0, 15, 20);
		}
		Rectangle rec = new Rectangle();
		String[] args = layout.split(",");
		int x = Integer.parseInt(args[0]);
		rec.setX(x);
		int y = Integer.parseInt(args[1]);
		rec.setY(y);
		int width = Integer.parseInt(args[2]);
		rec.setWidth(width);
		int height = Integer.parseInt(args[3]);
		rec.setHeight(height);
		return rec;

	}

	private void parseVerticalBlockModelXml(BlockModel block, Element child)
	{
		VerticalBlockModel vertical = new VerticalBlockModel();
		vertical.setRectangle(parseRectangle(child.attributeValue("layout")));
		vertical.addElement(-1, new GhostModel());
		block.addElement(-1, vertical);
		Iterator blocks = child.elementIterator();
		while (blocks.hasNext()) {
			Element blockChild = (Element) blocks.next();
			if (blockChild.getName().equals("verticalBlockModel"))
				parseVerticalBlockModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("horizontalBlockModel"))
				parseHorizontalBlockModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("queryBlockModel"))
				parseQueryBlockModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("chartBlockModel"))
				parseChartBlockModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("tableModel"))
				parseTableModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("frameModel"))
				parseFrameModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("titleModel"))
				parseTitleModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("textAreaModel"))
				parseTextAreaModelXml(vertical, blockChild);
			else if (blockChild.getName().equals("htmlAreaModel"))
				parseHtmlAreaModelXml(vertical, blockChild);
		}
	}

	private void parseHorizontalBlockModelXml(BlockModel block, Element child)
	{
		HorizontalBlockModel horizontal = new HorizontalBlockModel();
		horizontal.setRectangle(parseRectangle(child.attributeValue("layout")));
		horizontal.addElement(-1, new GhostModel());
		block.addElement(-1, horizontal);
		Iterator blocks = child.elementIterator();
		while (blocks.hasNext()) {
			Element blockChild = (Element) blocks.next();
			if (blockChild.getName().equals("verticalBlockModel"))
				parseVerticalBlockModelXml(horizontal, blockChild);
			else if (blockChild.getName().equals("horizontalBlockModel"))
				parseHorizontalBlockModelXml(horizontal, blockChild);
			else if (blockChild.getName().equals("queryBlockModel"))
				parseQueryBlockModelXml(horizontal, blockChild);
			else if (blockChild.getName().equals("chartBlockModel"))
				parseChartBlockModelXml(horizontal, blockChild);
			else if (blockChild.getName().equals("tableModel"))
				parseTableModelXml(horizontal, blockChild);
			else if (blockChild.getName().equals("frameModel"))
				parseFrameModelXml(horizontal, blockChild);
		}
	}

	private void parseQueryBlockModelXml(BlockModel block, Element child)
	{
		QueryBlockModel query = new QueryBlockModel();
		query.setRectangle(parseRectangle(child.attributeValue("layout")));
		query.setName(child.attributeValue("name"));
		query.setId(child.attributeValue("id"));
		Iterator params = child.elementIterator();
		while (params.hasNext()) {
			Element param = (Element) params.next();
			if (param.getName().equals("paramModel")) {
				parseParamModelXml(query, param);
			} else if (param.getName().equals("queryHorizontalBlockModel")) {
				parseQueryHorizontalBlockModel(query, param);
			}

		}
		block.addElement(-1, query);

	}

	private void parseQueryHorizontalBlockModel(QueryBlockModel query,
			Element queryHorizontalElement)
	{
		// TODO Auto-generated method stub
		QueryHorizontalBlockModel queryHorizontal = new QueryHorizontalBlockModel();
		Iterator params = queryHorizontalElement.elementIterator();
		while (params.hasNext()) {
			Element param = (Element) params.next();
			if (param.getName().equals("paramModel")) {
				parseParamModelXml(queryHorizontal, param);
			}

		}

		query.addElement(-1, queryHorizontal);

	}

	public void parseChartBlockModelXml(BlockModel block, Element child)
	{
		ChartBlockModel chart = new ChartBlockModel();
		chart.setRectangle(parseRectangle(child.attributeValue("layout")));
		chart.setName(child.attributeValue("name"));
		chart.setBlockName(child.attributeValue("blockName"));
		chart.setId(child.attributeValue("id"));
		chart.setChartType(child.attributeValue("chartType"));
		chart.setChartTypeDisplayName(child.attributeValue("chartTypeDisplayName"));
		chart.setHeight(child.attributeValue("height"));
		chart.setWidth(child.attributeValue("width"));
		Element sqlSetElement = child.element("sqlSet");
		if (sqlSetElement != null) {
			Iterator chartChildren = sqlSetElement.elementIterator();
			SqlSet sqlSet = new SqlSet();
			while (chartChildren.hasNext()) {
				Element chartChild = (Element) chartChildren.next();
				if (chartChild.getName().equals("chartSqlAreaModel"))
					parseChartSqlAreaModel(sqlSet, chartChild);
				else if (chartChild.getName().equals("chartType"))
					parseChartType(sqlSet, chartChild);
				else if (chartChild.getName().equals("name")) {
					sqlSet.setName(chartChild.getTextTrim());
				}
			}
			// sqlSet.setChartType(chartType);
			chart.setSqlSet(sqlSet);
		}
		block.addElement(-1, chart);
	}

	public void parseTableModelXml(BlockModel block, Element child)
	{
		TableModel table = new TableModel();
		table.setRectangle(parseRectangle(child.attributeValue("layout")));
		table.setName(child.attributeValue("name"));
		table.setBlockName(child.attributeValue("blockName"));
		table.setHeight(child.attributeValue("height"));
		table.setWidth(child.attributeValue("width"));
		if (child.attributeValue("type") != null) {
			table.setType(Integer.parseInt(child.attributeValue("type")));
		}
		if (child.attributeValue("hasCheckBox") != null) {
			if ("false".equals(child.attributeValue("hasCheckBox")))
				table.setHasCheckBox(false);
			else
				table.setHasCheckBox(true);
		}
		Element sql = child.element("sql");
		table.setSqlName(sql.elementText("sqlName"));
		table.setSql(sql.elementText("sqlArea"));
		Iterator columns = child.elementIterator("columnModel");
		TableSqlAreaModel tableSqlModel = new TableSqlAreaModel();
		tableSqlModel.setSqlName(sql.elementText("sqlName"));
		tableSqlModel.setSqlArea(sql.elementText("sqlArea"));
		Element inject = child.element("injectModel");
		InjectModel injectModel = new InjectModel();
		if (inject != null) {
			injectModel.setHtml(inject.elementText("html"));
			injectModel.setCss(inject.elementText("css"));
			injectModel.setJs(inject.elementText("js"));
		}
		while (columns.hasNext()) {
			Element columnElement = (Element) columns.next();
			parseColumnModel(table, columnElement, tableSqlModel);
		}
		Iterator columnGroup = child.elementIterator("columnGroupModel");
		while (columnGroup.hasNext()) {
			Element columnGroupElement = (Element) columnGroup.next();
			parseColumnGroup(table, columnGroupElement, tableSqlModel);
		}
		table.setSqlAreaModel(tableSqlModel);
		table.setInject(injectModel);
		block.addElement(-1, table);
	}

	public void parseFrameModelXml(BlockModel block, Element child)
	{
		FrameBlockModel frame = new FrameBlockModel();
		frame.setRectangle(parseRectangle(child.attributeValue("layout")));
		frame.setName(child.attributeValue("name"));
		frame.setHeight(child.attributeValue("height"));
		frame.setWidth(child.attributeValue("width"));
		frame.setUrl(child.elementText("url"));
		if (child.elementText("isShowBorder") != null) {
			frame.setIsShowBorder(child.elementText("isShowBorder"));
		}
		block.addElement(-1, frame);
	}

	public void parseTitleModelXml(BlockModel block, Element child)
	{
		TitleModel title = new TitleModel();
		title.setRectangle(parseRectangle(child.attributeValue("layout")));
		title.setName(child.attributeValue("name"));
		title.setHeight(child.attributeValue("height"));
		title.setWidth(child.attributeValue("width"));
		block.addElement(-1, title);
	}

	public void parseTextAreaModelXml(BlockModel block, Element child)
	{
		TextAreaModel text = new TextAreaModel();
		text.setRectangle(parseRectangle(child.attributeValue("layout")));
		text.setName(child.attributeValue("name"));
		text.setHeight(child.attributeValue("height"));
		text.setWidth(child.attributeValue("width"));
		text.setTextArea(child.elementText("textArea"));
		block.addElement(-1, text);
	}

	public void parseHtmlAreaModelXml(BlockModel block, Element child)
	{
		HtmlAreaModel html = new HtmlAreaModel();
		html.setRectangle(parseRectangle(child.attributeValue("layout")));
		html.setName(child.attributeValue("name"));
		html.setHeight(child.attributeValue("height"));
		html.setWidth(child.attributeValue("width"));
		html.setTextArea(child.elementText("textArea"));
		block.addElement(-1, html);
	}

	public void parseColumnModel(AbstractBlockModel block, Element child,
			TableSqlAreaModel tableSqlAreaModel)
	{
		ColumnModel2 column = new ColumnModel2();
		column.setAlign(child.elementText("align"));
		column.setClickurl(child.elementText("clickUrl"));
		column.setClickUrlTarget(child.elementText("clickUrlTarget"));
		column.setColspanStyle(child.elementText("colspanStyle"));
		column.setCord(child.elementText("cord"));
		column.setDescription(child.elementText("description"));
		column.setFormatter(child.elementText("formatter"));
		column.setFormatterOptions(child.elementText("formatterOptions"));
		column.setGroupOrder(child.elementText("groupOrder"));
		column.setGroupTitle(child.elementText("groupTitle"));
		column.setHierarchy(child.elementText("hierarchy"));
		column.setIfHide(child.elementText("ifHide"));
		column.setIfshow(child.elementText("ifShow"));
		column.setIsFrozen(child.elementText("isFrozen"));
		column.setName(child.elementText("name"));
		column.setOptions(child.elementText("options"));
		column.setOrder(child.elementText("order"));
		column.setParamKey(child.elementText("paramKey"));
		column.setParamValueColumnName(child.elementText("paramValueColumnName"));
		column.setType(child.elementText("type"));
		column.setWidth(child.elementText("width"));
		if (child.elementText("isGroup") != null) {
			column.setIsGroup(child.elementText("isGroup"));
		}
		tableSqlAreaModel.getCms().add(column);
		block.addElement(-1, column);
	}

	public void parseColumnGroup(TableModel table, Element child,
			TableSqlAreaModel tableSqlAreaModel)
	{
		ColumnGroupModel columnGroup = new ColumnGroupModel();
		columnGroup.addElement(-1, new GhostModel());
		columnGroup.setTitle(child.attributeValue("title"));
		Iterator columns = child.elementIterator("columnModel");
		while (columns.hasNext()) {
			Element column = (Element) columns.next();
			parseColumnModel(columnGroup, column, tableSqlAreaModel);

		}

		table.addElement(-1, columnGroup);
	}

	public void parseParamModelXml(AbstractBlockModel query, Element child)
	{
		String type = child.elementText("type");
		ParamModel param = null;
		if (type.equals("INPUT")) {
			param = new ParamInputModel();
		} else if (type.equals("DATE")) {
			param = new ParamDateModel();
		} else if (type.equals("HIDDEN")) {
			param = new ParamHiddenModel();
		} else if (type.equals("TREE")) {
			param = new ParamTreeModel();
		} else if (type.equals("USERTREE")) {
			param = new ParamUserTreeModel();
		} else {
			param = new ParamDeptTreeModel();
		}
		param.setRectangle(parseRectangle(child.attributeValue("layout")));
		param.setName(child.elementText("name"));
		param.setId(child.elementText("id"));
		param.setCodeName(child.elementText("codeName"));
		param.setColNum(child.elementText("colNum"));
		param.setCriteriaArea(child.elementText("criteriaArea"));
		param.setDescription(child.elementText("description"));
		param.setOptional(Integer.parseInt(child.elementText("optional")));
		param.setParamLocation(child.elementText("paramLocation"));
		param.setRowNum(child.elementText("rowNum"));
		param.setSql(child.elementText("sql"));
		if (child.elementText("treeType") != null) {
			param.setTreeType(Integer.parseInt(child.elementText("treeType")));
		}
		if (child.elementText("parentId") != null) {
			param.setParentId(child.elementText("parentId"));
		}
		param.setMoreType(child.elementText("moreType"));
		if (query instanceof QueryBlockModel) {
			query.addElement(-1, param);
		} else if (query instanceof QueryHorizontalBlockModel) {
			((QueryHorizontalBlockModel) query).addElement(-1, param);
		}
	}

	public void parseChartSqlAreaModel(SqlSet sqlSet, Element child)
	{
		ChartSqlAreaModel sqlModel = new ChartSqlAreaModel();
		sqlModel.setSqlName(child.elementText("sqlName"));
		sqlModel.setSqlArea(child.elementTextTrim("sqlArea"));
		sqlModel.setChartType(child.elementText("chartType"));
		sqlModel.setColumnName(parseColumnNameXml(sqlModel, child));
		sqlModel.setSeries(child.elementText("series"));
		sqlModel.setSeriesDes(child.elementText("seriesDes"));
		sqlModel.setStack(child.elementText("stack"));
		sqlModel.setStackDes(child.elementText("stackDes"));
		sqlModel.setXaxis(child.elementText("Xaxis"));
		sqlModel.setXdes(child.elementText("Xdes"));
		sqlModel.setXunit(child.elementText("Xunit"));
		sqlModel.setYaxisNum(child.elementText("YaxisNum"));
		sqlModel.setYdes(child.elementText("Ydes"));
		sqlModel.setYaxis(child.elementText("Yaxis"));
		sqlModel.setYunit(child.elementText("Yunit"));
		sqlSet.addSql(sqlModel);

	}

	public void parseChartType(SqlSet sqlSet, Element child)
	{
		ChartType chartType = new ChartType();
		List<ChartGroup> groups = Configuration.getInstance().getChartGroups();
		chartType.setName(child.elementText("name"));
		chartType.setDisplayName(child.elementText("displayName"));
		chartType.setChartGroup(child.elementText("groupName"));
		boolean find = false;
		for (ChartGroup group : groups) {
			List<ChartType> charts = group.getCharts();
			for (ChartType chart : charts) {
				if (chart.getName().equals(chartType.getName())) {
					chartType.getProperties().addAll(chart.getProperties());
					find = true;
					break;
				}
			}
			if (find)
				break;
		}
		sqlSet.setChartType(chartType);

	}

	public List<String> parseColumnNameXml(ChartSqlAreaModel sql, Element child)
	{
		List<String> columnName = new ArrayList<String>();
		Element columnNameElement = child.element("columnName");
		Iterator nameList = columnNameElement.elementIterator();
		while (nameList.hasNext()) {
			Element name = (Element) nameList.next();
			columnName.add(name.getStringValue());
		}
		return columnName;
	}

}
