package com.wt.studio.plugin.querydesigner.io.writer;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.PageModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;

public class DiagramXmlWriter
{

	public static String createXml(Diagram diagram)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<container  version=\"1.0\">\n");
		xml.append("<diagram ");
		xml.append("name=\"").append(diagram.getName()).append("\" ");
		xml.append("id=\"").append(diagram.getId()).append("\" ");
		xml.append("des=\"").append(diagram.getDesc()).append("\" ");
		xml.append("defaultShowData=\"").append(diagram.getDefaultShowData()).append("\" ");
		xml.append("status=\"").append(diagram.getStatus()).append("\" >\n");
		List<BlockModel> blockModels = diagram.getBlockModels();
		for (BlockModel block : blockModels) {
			if (block instanceof PageModel) {
				xml.append(createXml((PageModel) block));
			}
		}
		xml.append("</diagram>\n");
		xml.append("</container>");
		return xml.toString();

	}

	public static String createXml(PageModel pageModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<pageModel ");
		xml.append("layout=\"").append(pageModel.getRectangle().x + ",")
				.append(pageModel.getRectangle().y + ",")
				.append(pageModel.getRectangle().width + ",")
				.append(pageModel.getRectangle().height).append("\">\n");
		List<Element> elements = pageModel.getElements();
		for (Element element : elements) {
			if (element instanceof VerticalBlockModel)
				xml.append(tab(createXml((VerticalBlockModel) element)));
			else if (element instanceof HorizontalBlockModel)
				xml.append(tab(createXml((HorizontalBlockModel) element)));
			else if (element instanceof QueryBlockModel)
				xml.append(tab(createXml((QueryBlockModel) element)));
			else if (element instanceof ChartBlockModel)
				xml.append(tab(createXml((ChartBlockModel) element)));
			else if (element instanceof TableModel)
				xml.append(tab(createXml((TableModel) element)));
			else if (element instanceof FrameBlockModel)
				xml.append(tab(createXml((FrameBlockModel) element)));
			else if (element instanceof HtmlAreaModel)
				xml.append(tab(createXml((HtmlAreaModel) element)));
			else if (element instanceof TextAreaModel)
				xml.append(tab(createXml((TextAreaModel) element)));
			else if (element instanceof TitleModel)
				xml.append(tab(createXml((TitleModel) element)));

		}
		xml.append("\t</pageModel>\n");
		return xml.toString();

	}

	public static String createXml(VerticalBlockModel verticalBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<verticalBlockModel ");
		xml.append("layout=\"").append(verticalBlockModel.getRectangle().x + ",")
				.append(verticalBlockModel.getRectangle().y + ",")
				.append(verticalBlockModel.getRectangle().width + ",")
				.append(verticalBlockModel.getRectangle().height).append("\">\n");
		List<Element> elements = verticalBlockModel.getElements();
		for (Element element : elements) {
			if (element instanceof VerticalBlockModel)
				xml.append(tab(createXml((VerticalBlockModel) element)));
			else if (element instanceof HorizontalBlockModel)
				xml.append(tab(createXml((HorizontalBlockModel) element)));
			else if (element instanceof QueryBlockModel)
				xml.append(tab(createXml((QueryBlockModel) element)));
			else if (element instanceof ChartBlockModel)
				xml.append(tab(createXml((ChartBlockModel) element)));
			else if (element instanceof TableModel)
				xml.append(tab(createXml((TableModel) element)));
			else if (element instanceof FrameBlockModel)
				xml.append(tab(createXml((FrameBlockModel) element)));
			else if (element instanceof HtmlAreaModel)
				xml.append(tab(createXml((HtmlAreaModel) element)));
			else if (element instanceof TextAreaModel)
				xml.append(tab(createXml((TextAreaModel) element)));
			else if (element instanceof TitleModel)
				xml.append(tab(createXml((TitleModel) element)));
		}
		xml.append("\t</verticalBlockModel>\n");
		return xml.toString();
	}

	public static String createXml(HorizontalBlockModel horizontalBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<horizontalBlockModel ");
		xml.append("layout=\"").append(horizontalBlockModel.getRectangle().x + ",")
				.append(horizontalBlockModel.getRectangle().y + ",")
				.append(horizontalBlockModel.getRectangle().width + ",")
				.append(horizontalBlockModel.getRectangle().height).append("\">\n");
		List<Element> elements = horizontalBlockModel.getElements();
		for (Element element : elements) {
			if (element instanceof VerticalBlockModel)
				xml.append(tab(tab(createXml((VerticalBlockModel) element))));
			else if (element instanceof HorizontalBlockModel)
				xml.append(tab(tab(createXml((HorizontalBlockModel) element))));
			else if (element instanceof QueryBlockModel)
				xml.append(tab(tab(createXml((QueryBlockModel) element))));
			else if (element instanceof ChartBlockModel)
				xml.append(tab(tab(createXml((ChartBlockModel) element))));
			else if (element instanceof TableModel)
				xml.append(tab(tab(createXml((TableModel) element))));
			else if (element instanceof FrameBlockModel)
				xml.append(tab(tab(createXml((FrameBlockModel) element))));
			else if (element instanceof HtmlAreaModel)
				xml.append(tab(createXml((HtmlAreaModel) element)));
			else if (element instanceof TextAreaModel)
				xml.append(tab(tab(createXml((TextAreaModel) element))));
		}
		xml.append("\t</horizontalBlockModel>\n");
		return xml.toString();
	}

	public static String createXml(QueryBlockModel queryBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<queryBlockModel ");
		xml.append("layout=\"").append(queryBlockModel.getRectangle().x + ",")
				.append(queryBlockModel.getRectangle().y + ",")
				.append(queryBlockModel.getRectangle().width + ",")
				.append(queryBlockModel.getRectangle().height).append("\" ");
		xml.append("name=\"").append(queryBlockModel.getName()).append("\">\n");
		List<Element> elements = queryBlockModel.getElements();
		for (Element element : elements) {
			if (element instanceof QueryHorizontalBlockModel) {
				xml.append(tab(createXml((QueryHorizontalBlockModel) element)));
			} else if (element instanceof ParamModel) {
				xml.append(tab(createXml((ParamModel) element)));
			}
		}
		xml.append("\t</queryBlockModel>\n");
		return xml.toString();
	}

	public static String createXml(QueryHorizontalBlockModel queryHorizontalBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<queryHorizontalBlockModel>");
		List<Element> params = queryHorizontalBlockModel.getElements();
		for (Element param : params) {
			if (param instanceof ParamModel)
				xml.append(tab(createXml((ParamModel) param)));
		}
		xml.append("\t</queryHorizontalBlockModel>");
		return xml.toString();
	}

	public static String createXml(ChartBlockModel chartBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<chartBlockModel ");
		xml.append("name=\"").append(chartBlockModel.getName()).append("\" ");
		xml.append("blockName=\"").append(chartBlockModel.getBlockName()).append("\" ");
		xml.append("id=\"").append(chartBlockModel.getId()).append("\" ");
		xml.append("width=\"").append(chartBlockModel.getWidth()).append("\" ");
		xml.append("height=\"").append(chartBlockModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(chartBlockModel.getRectangle().x + ",")
				.append(chartBlockModel.getRectangle().y + ",")
				.append(chartBlockModel.getRectangle().width + ",")
				.append(chartBlockModel.getRectangle().height).append("\" ");
		xml.append("chartType=\"").append(chartBlockModel.getChartType()).append("\" ");
		xml.append("chartTypeDisplayName=\"").append(chartBlockModel.getChartTypeDisplayName())
				.append("\">\n");
		if (chartBlockModel.getSqlSet() != null) {
			xml.append(tab(createXml(chartBlockModel.getSqlSet())));
		}
		xml.append("\t</chartBlockModel>\n");
		return xml.toString();
	}

	public static String createXml(TableModel tableModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<tableModel ");
		xml.append("name=\"").append(tableModel.getName()).append("\" ");
		xml.append("blockName=\"").append(tableModel.getBlockName()).append("\" ");
		xml.append("width=\"").append(tableModel.getWidth()).append("\" ");
		xml.append("height=\"").append(tableModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(tableModel.getRectangle().x + ",")
				.append(tableModel.getRectangle().y + ",")
				.append(tableModel.getRectangle().width + ",")
				.append(tableModel.getRectangle().height).append("\" ");
		xml.append("type=\"").append(tableModel.getType()).append("\" ");
		xml.append("hasCheckBox=\"").append(tableModel.isHasCheckBox()).append("\"");
		xml.append(">\n");
		xml.append("\t\t<sql>\n");
		xml.append("\t\t\t<sqlName><![CDATA[").append(tableModel.getSqlName())
				.append("]]></sqlName>\n");
		xml.append("\t\t\t<sqlArea><![CDATA[").append((tableModel.getSql()))
				.append("]]></sqlArea>\n");
		xml.append("\t\t</sql>\n");
		if (tableModel.getInject() != null) {
			xml.append("\t\t<injectModel>\n");
			xml.append("\t\t\t<html><![CDATA[").append((tableModel.getInject().getHtml()))
					.append("]]></html>\n");
			xml.append("\t\t\t<css><![CDATA[").append((tableModel.getInject().getCss()))
					.append("]]></css>\n");
			xml.append("\t\t\t<js><![CDATA[").append((tableModel.getInject().getJs()))
					.append("]]></js>\n");
			xml.append("\t\t</injectModel>\n");
		}
		List<Element> elements = tableModel.getResult();
		for (Element element : elements) {
			if (element instanceof ColumnModel2)
				xml.append(tab(createXml((ColumnModel2) element)));
			if (element instanceof ColumnGroupModel)
				xml.append(tab(createXml((ColumnGroupModel) element)));
		}
		xml.append("\t</tableModel>\n");
		return xml.toString();
	}

	public static String createXml(FrameBlockModel frameBlockModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<frameModel ");
		xml.append("name=\"").append(frameBlockModel.getName()).append("\" ");
		xml.append("width=\"").append(frameBlockModel.getWidth()).append("\" ");
		xml.append("height=\"").append(frameBlockModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(frameBlockModel.getRectangle().x + ",")
				.append(frameBlockModel.getRectangle().y + ",")
				.append(frameBlockModel.getRectangle().width + ",")
				.append(frameBlockModel.getRectangle().height).append("\">\n");
		xml.append("\t\t<url><![CDATA[").append(frameBlockModel.getUrl()).append("]]></url>\n");
		xml.append("\t\t<isShowBorder><![CDATA[").append(frameBlockModel.getIsShowBorder())
				.append("]]></isShowBorder>\n");
		xml.append("\t</frameModel>\n");
		return xml.toString();
	}

	public static String createXml(TextAreaModel textAreaModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<textAreaModel ");
		xml.append("name=\"").append(textAreaModel.getName()).append("\" ");
		xml.append("width=\"").append(textAreaModel.getWidth()).append("\" ");
		xml.append("height=\"").append(textAreaModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(textAreaModel.getRectangle().x + ",")
				.append(textAreaModel.getRectangle().y + ",")
				.append(textAreaModel.getRectangle().width + ",")
				.append(textAreaModel.getRectangle().height).append("\">\n");
		xml.append("\t\t<textArea><![CDATA[").append(textAreaModel.getTextArea())
				.append("]]></textArea>\n");
		xml.append("\t</textAreaModel>\n");
		return xml.toString();
	}

	public static String createXml(HtmlAreaModel textAreaModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<htmlAreaModel ");
		xml.append("name=\"").append(textAreaModel.getName()).append("\" ");
		xml.append("width=\"").append(textAreaModel.getWidth()).append("\" ");
		xml.append("height=\"").append(textAreaModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(textAreaModel.getRectangle().x + ",")
				.append(textAreaModel.getRectangle().y + ",")
				.append(textAreaModel.getRectangle().width + ",")
				.append(textAreaModel.getRectangle().height).append("\">\n");
		xml.append("\t\t<textArea><![CDATA[").append(textAreaModel.getTextArea())
				.append("]]></textArea>\n");
		xml.append("\t</htmlAreaModel>\n");
		return xml.toString();
	}

	public static String createXml(TitleModel titleModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<titleModel ");
		xml.append("name=\"").append(titleModel.getName()).append("\" ");
		xml.append("width=\"").append(titleModel.getWidth()).append("\" ");
		xml.append("height=\"").append(titleModel.getHeight()).append("\" ");
		xml.append("layout=\"").append(titleModel.getRectangle().x + ",")
				.append(titleModel.getRectangle().y + ",")
				.append(titleModel.getRectangle().width + ",")
				.append(titleModel.getRectangle().height).append("\">\n");
		xml.append("\t</titleModel>\n");
		return xml.toString();
	}

	public static String createXml(Rectangle rec)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<rectangle>\n");
		xml.append("\t\t<height>").append(rec.height).append("</height>\n");
		xml.append("\t\t<width>").append(rec.width).append("</width>\n");
		xml.append("\t\t<x>").append(rec.x).append("</x>\n");
		xml.append("\t\t<y>").append(rec.y).append("</y>\n");
		xml.append("\t</rectangle>\n");
		return xml.toString();
	}

	public static String createXml(ParamModel param)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<paramModel ");
		xml.append("layout=\"").append(param.getRectangle().x + ",")
				.append(param.getRectangle().y + ",").append(param.getRectangle().width + ",")
				.append(param.getRectangle().height).append("\">\n");
		xml.append("\t\t<id>").append(param.getId()).append("</id>\n");
		xml.append("\t\t<name><![CDATA[").append(param.getName()).append("]]></name>\n");
		xml.append("\t\t<description><![CDATA[").append(param.getDescription())
				.append("]]></description>\n");
		xml.append("\t\t<type><![CDATA[").append(param.getType()).append("]]></type>\n");
		xml.append("\t\t<moreType><![CDATA[").append(param.getMoreType())
				.append("]]></moreType>\n");
		xml.append("\t\t<sql><![CDATA[").append(replace(param.getSql())).append("]]></sql>\n");
		xml.append("\t\t<rowNum><![CDATA[").append(param.getRowNum()).append("]]></rowNum>\n");
		xml.append("\t\t<colNum><![CDATA[").append(param.getColNum()).append("]]></colNum>\n");
		xml.append("\t\t<treeType><![CDATA[").append(param.getTreeType())
				.append("]]></treeType>\n");
		xml.append("\t\t<optional><![CDATA[").append(param.getOptional())
				.append("]]></optional>\n");
		xml.append("\t\t<criteriaArea><![CDATA[").append((param.getCriteriaArea()))
				.append("]]></criteriaArea>\n");
		xml.append("\t\t<paramLocation><![CDATA[").append(param.getParamLocation())
				.append("]]></paramLocation>\n");
		xml.append("\t\t<codeName><![CDATA[").append(param.getCodeName())
				.append("]]></codeName>\n");
		xml.append("\t\t<parentId><![CDATA[").append(param.getParentId())
				.append("]]></parentId>\n");
		xml.append("\t</paramModel>\n");
		return xml.toString();
	}

	public static String createXml(ChartSqlAreaModel sqlAreaModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<chartSqlAreaModel>\n");
		xml.append("\t\t<sqlName>").append(sqlAreaModel.getSqlName()).append("</sqlName>\n");
		xml.append("\t\t<sqlArea><![CDATA[").append(replace(sqlAreaModel.getSqlArea()))
				.append("]]></sqlArea>\n");
		xml.append("\t\t<columnName>\n");
		for (String name : sqlAreaModel.getColumnName()) {
			xml.append("\t\t\t<name>").append(name).append("</name>\n");
		}
		xml.append("\t\t</columnName>\n");
		xml.append("\t\t<chartType>").append(sqlAreaModel.getChartType()).append("</chartType>\n");
		xml.append("\t\t<Xaxis>").append(sqlAreaModel.getXaxis()).append("</Xaxis>\n");
		xml.append("\t\t<Xdes>").append(sqlAreaModel.getXdes()).append("</Xdes>\n");
		xml.append("\t\t<Xunit>").append(sqlAreaModel.getXunit()).append("</Xunit>\n");
		xml.append("\t\t<Yaxis>").append(sqlAreaModel.getYaxis()).append("</Yaxis>\n");
		xml.append("\t\t<Ydes>").append(sqlAreaModel.getYdes()).append("</Ydes>\n");
		xml.append("\t\t<Yunit>").append(sqlAreaModel.getYunit()).append("</Yunit>\n");
		xml.append("\t\t<series>").append(sqlAreaModel.getSeries()).append("</series>\n");
		xml.append("\t\t<seriesDes>").append(sqlAreaModel.getSeriesDes()).append("</seriesDes>\n");
		xml.append("\t\t<stack>").append(sqlAreaModel.getStack()).append("</stack>\n");
		xml.append("\t\t<stackDes>").append(sqlAreaModel.getStackDes()).append("</stackDes>\n");
		xml.append("\t\t<YaxisNum>").append(sqlAreaModel.getYaxisNum()).append("</YaxisNum>\n");
		xml.append("\t</chartSqlAreaModel>\n");
		return xml.toString();
	}

	public static String createXml(TableSqlAreaModel tableSqlAreaModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<tableSqlAreaModel>\n");
		xml.append("\t\t<sqlName>").append(tableSqlAreaModel.getSqlName()).append("</sqlName>\n");
		xml.append("\t\t<sqlArea><![CDATA[").append(replace(tableSqlAreaModel.getSqlArea()))
				.append("]]></sqlArea>\n");
		xml.append("\t\t<cms>\n");
		for (ColumnModel2 column : tableSqlAreaModel.getCms()) {
			xml.append(tab(tab(createXml(column))));
		}
		xml.append("\t\t</cms>\n");
		xml.append("\t</tableSqlAreaModel>\n");
		return xml.toString();

	}

	public static String createXml(ColumnModel2 columnModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<columnModel>\n");
		xml.append("\t\t<name>").append(columnModel.getName()).append("</name>\n");
		xml.append("\t\t<description>").append(columnModel.getDescription())
				.append("</description>\n");
		xml.append("\t\t<type>").append(columnModel.getType()).append("</type>\n");
		xml.append("\t\t<order>").append(columnModel.getOrder()).append("</order>\n");
		xml.append("\t\t<ifShow>").append(columnModel.getIfshow()).append("</ifShow>\n");
		xml.append("\t\t<align>").append(columnModel.getAlign()).append("</align>\n");
		xml.append("\t\t<clickUrl><![CDATA[").append(columnModel.getClickurl())
				.append("]]></clickUrl>\n");
		xml.append("\t\t<paramKey>").append(columnModel.getParamKey()).append("</paramKey>\n");
		xml.append("\t\t<clickUrlTarget><![CDATA[").append(columnModel.getClickUrlTarget())
				.append("]]></clickUrlTarget>\n");
		xml.append("\t\t<options>").append(columnModel.getOptions()).append("</options>\n");
		xml.append("\t\t<formatter>").append(columnModel.getFormatter()).append("</formatter>\n");
		xml.append("\t\t<formatterOptions>").append(columnModel.getFormatterOptions())
				.append("</formatterOptions>\n");
		xml.append("\t\t<ifHide>").append(columnModel.getIfHide()).append("</ifHide>\n");
		xml.append("\t\t<paramValueColumnName>").append(columnModel.getParamValueColumnName())
				.append("</paramValueColumnName>\n");
		xml.append("\t\t<cord>").append(columnModel.getCord()).append("</cord>\n");
		xml.append("\t\t<width>").append(columnModel.getWidth()).append("</width>\n");
		xml.append("\t\t<hierarchy>").append(columnModel.getHierarchy()).append("</hierarchy>\n");
		xml.append("\t\t<colspanStyle>").append(columnModel.getColspanStyle())
				.append("</colspanStyle>\n");
		xml.append("\t\t<groupTitle>").append(columnModel.getGroupTitle())
				.append("</groupTitle>\n");
		xml.append("\t\t<groupOrder>").append(columnModel.getGroupOrder())
				.append("</groupOrder>\n");
		xml.append("\t\t<isFrozen>").append(columnModel.getIsFrozen()).append("</isFrozen>\n");
		xml.append("\t\t<isGroup>").append(columnModel.getIsGroup()).append("</isGroup>\n");
		xml.append("\t</columnModel>\n");
		return xml.toString();

	}

	public static String createXml(ColumnGroupModel columnGroupModel)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<columnGroupModel ");
		xml.append("title=\"").append(columnGroupModel.getTitle()).append("\">\n");
		List<Element> elements = columnGroupModel.getElements();
		for (Element element : elements) {
			if (element instanceof ColumnModel2)
				xml.append(tab(createXml((ColumnModel2) element)));
			else if (element instanceof ColumnGroupModel)
				xml.append(tab(createXml((ColumnGroupModel) element)));
		}
		xml.append("\t</columnGroupModel>\n");
		return xml.toString();

	}

	public static String createXml(SqlSet sqlSet)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<sqlSet>\n");
		xml.append("\t\t<name>").append(sqlSet.getName()).append("</name>");
		List<ChartSqlAreaModel> sqls = sqlSet.getSqls();
		for (ChartSqlAreaModel sql : sqls) {
			xml.append(tab(tab(createXml(sql))));
		}
		xml.append(tab(createXml(sqlSet.getChartType())));
		xml.append("\t</sqlSet>\n");
		return xml.toString();
	}

	public static String createXml(ChartType chartType)
	{
		StringBuilder xml = new StringBuilder();
		xml.append("\t<chartType>\n");
		xml.append("\t\t<name>").append(chartType.getName()).append("</name>\n");
		xml.append("\t\t<displayName>").append(chartType.getDisplayName())
				.append("</displayName>\n");
		xml.append("\t\t<groupName>").append(chartType.getChartGroup()).append("</groupName>\n");
		xml.append("\t</chartType>\n");
		return xml.toString();

	}

	private static String tab(String str)
	{
		str = str.replaceAll("\n\t", "\n\t\t");
		str = str.replaceAll("\n<", "\n\t<");

		return "\t" + str;
	}

	public static String replace(String str)
	{
		if (str != null) {
			str = str.replaceAll("\n", " ");
			return str;
		}
		return null;
	}
}
