package com.wt.studio.plugin.querydesigner.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.model.DataSourceModel;
import com.wt.studio.plugin.querydesigner.model.InjectModel;

public class DataSourceUtils
{
	private final static String INSERT_DATA_SOURCE = "insert into P_S_DS(ds_id, Ds_Name, DS_DESC, DS_TYPE, DS_URL, DS_USER, DS_PASS) values (?, ?, ?, ?, ?, ?, ?)";
	private final static String INSERT_DATA_OBJECT = "insert into P_S_DOBJECT(DOBJ_ID, DOBJ_NAME, DOBJ_DESC, DOBJ_SQL, DOBJ_PARENT_ID, DOBJ_TYPE, DOBJ_ORDER, DS_ID) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String INSERT_FUNC = "insert into P_S_FUNC (FUNC_ID, FUNC_NAME, FUNC_STATUS, FUNC_DESC, DIV_NAME,  FUNC_SHOW_DATA) values (?, ?, '01', ?, null, ?)";
	private final static String INSERT_BLOCK = "insert into P_S_BLOCK (BLOCK_ID, FUNC_ID, WIDTH, HEIGHT, BLOCK_TYPE, BLOCK_TITLE, BLOCK_CONTENT, BLOCK_ORDER, PARENT_BLOCK_ID,OPER_SHOW_TYPE,BORDER_SHOW,BLOCK_NAME) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
	private final static String INSERT_PARAM = "insert into P_S_PARAM (PARAM_ID, FUNC_ID, PARAM_NAME, SHOW_NAME, IF_SHOW, PARAM_UI_TYPE, CODE_NAME, ROW_NUM, COL_NUM, OPTIONAL,PARAM_ORDER,REF_PARAM_ID) values (?, ?, ?, ?, ?,?, ?, ?, ?, ? ,?,?)";
	private final static String INSERT_SQL = "insert into P_S_SQLAREA (SQL_ID, BLOCK_ID, SQL_ORDER, FUNC_ID, SQL_DES, SQL_AREA,  SQL_TYPE, DIRECT_COLUMN) values (?, ?, ?, ?, ?, ?, null, null)";
	private final static String INSERT_COLUMN = "insert into P_S_COLUMN (COLUMN_ID, SQL_ID, COLUMN_NAME, COLUMN_DESC, COLUMN_ORDER, IF_SHOW, CLICK_URL, FUNC_ID, COLUMN_TYPE, WIDTH, HEIGHT, ALIGN, PARAM_KEY, CLICK_URL_TARGET, COLUMN_OPT, COLUMN_FORMATTER, COLUMN_FORMATOPTIONS, IF_HIDE, PARAM_VALUE_COLUMN_NAME, CORD) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String INSERT_PARAM_SQL = "insert into P_S_PARAM_SQL (PARAM_ID, SQL_ID) values (?, ?)";
	private final static String INSERT_PARAM_CRITERIA = "insert into P_S_PARAM_CRITERIA (SQL_ID, CRITERIA_AREA, PARAM_LOCATION, CRITERIA_DESC, ID, PARAM_NAME) values (?, ?, ?, ?, ?, ?)";
	private final static String INSERR_BLOCK_CHART = "insert into P_S_BLOCK_CHART (CHART_ID,FUNC_ID,BLOCK_ID,SQL_ID,CHART_TYPE,TITLE,SERIES,SERIES_DESC,STACK,STACK_DES,X_AXIS,X_AXIS_DESC,X_AXIS_NUIT,Y_AXIS,Y_AXIS_DESC,Y_AXIS_NUIT,Y_AXIS_ORDER) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String INSERT_PS_BLOCK_MTABLE = "INSERT into P_S_BLOCK_MTABLE(GROUP_ID,FUNC_ID,BLOCK_ID,HIERARCHY,COLSPAN_STYLE,GROUP_TITLE,COLUMN_ID,ORDER_CN,IS_FROZEN,IS_GROUP)VALUES(?,?,?,?,?,?,?,?,?,?)";
	private final static String INSERT_PS_BLOCK_CUSTOMISE = "INSERT into P_S_BLOCK_CUSTOMIZE(FUNC_ID,BLOCK_ID,HTML_INJECT,CSS_INJECT,JS_INJECT)VALUES(?,?,?,?,?)";
	private final static String EXISTS_FUNC = "select 1 from p_s_func where exists(select 1 from p_s_func t where t.func_id = ?)";
	private final static String DELETE_PARAM_SQL = "delete from P_S_PARAM_SQL t where t.param_id in (select p.param_id from p_s_param p where p.func_id = ?)";
	private final static String DELETE_PARAM_CRITERIA = "delete from P_S_PARAM_CRITERIA t where t.sql_id in (select s.sql_id from p_s_sqlarea s where s.func_id = ?)";
	private final static String DELETE_PARAM = "delete from P_S_PARAM t where t.func_id = ?";
	private final static String DELETE_COLUMN = "delete from P_S_COLUMN t where t.func_id = ?";
	private final static String DELETE_SQLAREA = "delete from P_S_SQLAREA t where t.func_id = ?";
	private final static String DELETE_BLOCK = "delete from P_S_BLOCK t where t.func_id = ?";
	private final static String DELETE_FUNC = "delete from P_S_FUNC t where t.func_id = ?";
	private final static String DELETE_BLOCK_CHART = "delete from P_S_BLOCK_CHART t where t.func_id = ?";
	private final static String DELETE_BLOCK_MTABLE = "delete from P_S_BLOCK_MTABLE t where t.func_id = ?";
	private final static String DELETE_BLOCK_CUSTOMISE = "delete from P_S_BLOCK_CUSTOMIZE t where t.func_id = ?";
	private final static String CHECK_BLOCK_NAME = "select 1 from p_s_block where exists(select 1 from p_s_block t where t.func_id = ? and t.block_name= ?)";
	private static int count = 1;

	public static Connection getConnection()
	{
		return CommonEclipseUtil
				.getConnectionByProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
	}

	public static String insert(DataSourceModel model)
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(INSERT_DATA_SOURCE);
			model.setId(uuid());
			stmt.setString(1, model.getId());
			stmt.setString(2, model.getName());
			stmt.setString(3, model.getDesc());
			stmt.setString(4, model.getType());
			stmt.setString(5, model.getUrl());
			stmt.setString(6, model.getUser());
			stmt.setString(7, model.getPass());
			stmt.executeUpdate();

			stmt = connection.prepareStatement(INSERT_DATA_OBJECT);
			for (DataObjectModel objectModels : model.getObjectModels()) {
				objectModels.setId(uuid());
				objectModels.setDsId(model.getId());
				stmt.setString(1, objectModels.getId());
				stmt.setString(2, objectModels.getName());
				stmt.setString(3, objectModels.getDesc());
				stmt.setString(4, objectModels.getSql());
				stmt.setString(5, objectModels.getParentId());
				stmt.setString(6, objectModels.getType());
				stmt.setLong(7, objectModels.getOrder());
				stmt.setString(8, objectModels.getDsId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.clearBatch();
			connection.commit();
			return model.getId();
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			close(connection, stmt, null);
		}
		return null;
	}

	public static boolean existsFunc(String funcid)
	{
		if (funcid == null || funcid.trim().equals("")) {
			return false;
		}
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(EXISTS_FUNC);
			stmt.setString(1, funcid);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, stmt, rs);
		}
		return false;
	}

	private static void deleteOldData(Connection connection, PreparedStatement stmt, String funcId)
			throws Exception
	{
		stmt = connection.prepareStatement(DELETE_PARAM_SQL);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_PARAM_CRITERIA);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_PARAM);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_PARAM_SQL);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_COLUMN);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_SQLAREA);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_BLOCK);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_FUNC);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_BLOCK_CHART);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_BLOCK_MTABLE);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

		stmt = connection.prepareStatement(DELETE_BLOCK_CUSTOMISE);
		stmt.setString(1, funcId);
		stmt.executeUpdate();

	}

	public static boolean saveFunc(Diagram diagram, boolean exists)
	{
		Connection connection = null;
		PreparedStatement stmt = null;
		count = 1;
		List<String> tempList = new ArrayList<String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			// 删除原有的func_id数据
			if (exists) {
				deleteOldData(connection, stmt, diagram.getId());
			}

			/*
			 * insert into P_S_FUNC (FUNC_ID, FUNC_NAME, FUNC_STATUS, FUNC_DESC,
			 * DIV_NAME, FUNC_SHOW_DATA) values (?, ?, '01', ?, null, ?) 插入func
			 */
			stmt = connection.prepareStatement(INSERT_FUNC);
			stmt.setString(1, diagram.getId());
			stmt.setString(2, diagram.getName());
			stmt.setString(3, diagram.getDesc());
			stmt.setString(4, diagram.getDefaultShowData().toString());
			stmt.executeUpdate();

			for (BlockModel block : diagram.getBlockModels()) {
				GetAllBlockModel(block, connection, stmt, diagram, tempList, tempMap);
			}

			// 插入table块

			// 插入图表类

			// 插入Query
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			close(connection, stmt, null);
		}
		return false;
	}

	private static void InsertTable(Connection connection, PreparedStatement stmt,
			TableModel tableModel, BlockModel parentBlock, Diagram diagram, List<String> tempList,
			Map<String, String> tempMap, int order) throws Exception
	{

		// stmt = connection.prepareStatement(CHECK_BLOCK_NAME);
		// stmt.setString(1, diagram.getId());
		// stmt.setString(2, tableModel.getSqlName());
		// ResultSet rs = stmt.executeQuery();
		// if (rs.next()) {
		// MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
		// "列表名称重复！");
		// return;
		// }

		// insert into P_S_BLOCK (BLOCK_ID, FUNC_ID, WIDTH, HEIGHT,
		// BLOCK_TYPE, BLOCK_TITLE,
		// BLOCK_ORDER) values (?, ?, ?, ?, ?, ?, ?)
		tableModel.setId(uuid());
		stmt = connection.prepareStatement(INSERT_BLOCK);
		stmt.setString(1, tableModel.getId());
		stmt.setString(2, diagram.getId()); // FUNC_ID
		stmt.setString(3, tableModel.getWidth());// WIDTH
		stmt.setString(4, tableModel.getHeight());// HEIGHT
		String type = tableModel.getType() == 0 ? "TABLE" : "CTABLE";
		stmt.setString(5, type);// BLOCK_TYPE
		stmt.setString(6, tableModel.getName());// BLOCK_TITLE
		stmt.setString(7, null);// BLOCK_CONTENT
		stmt.setString(8, Integer.toString(order));// BLOCK_ORDER
		stmt.setString(9, parentBlock == null ? null : parentBlock.getId());// PARENT_BLOCK_ID
		stmt.setString(10, tableModel.isHasCheckBox() ? "checkbox" : null);
		stmt.setString(11, null);
		stmt.setString(12, tableModel.getSqlName());
		stmt.executeUpdate();

		if (type.equals("CTABLE")) {
			InjectModel inject = tableModel.getInject();
			stmt = connection.prepareStatement(INSERT_PS_BLOCK_CUSTOMISE);
			stmt.setString(1, diagram.getId());
			stmt.setString(2, tableModel.getId());
			stmt.setString(3, inject.getHtml());
			stmt.setString(4, inject.getCss());
			stmt.setString(5, inject.getJs());
			stmt.executeUpdate();

		}
		// 插入sql
		/*
		 * insert into P_S_SQLAREA (SQL_ID, BLOCK_ID, SQL_ORDER, FUNC_ID,
		 * SQL_DES, SQL_AREA, SQL_TYPE, DIRECT_COLUMN) values (?, ?, ?, ?, ?, ?,
		 * null, null)
		 */
		String tableSqlId = uuid();

		tempList.add(tableSqlId);
		tempMap.put(tableSqlId, tableModel.getSql());

		stmt = connection.prepareStatement(INSERT_SQL);
		stmt.setString(1, tableSqlId);
		stmt.setString(2, tableModel.getId());
		stmt.setString(3, "1");
		stmt.setString(4, diagram.getId());
		stmt.setString(5, tableModel.getSqlName());
		stmt.setString(6, tableModel.getSql());
		stmt.executeUpdate();
		// 插入列
		for (ColumnModel2 columnModel2 : tableModel.getColumns()) {
			/*
			 * insert into P_S_COLUMN (COLUMN_ID, SQL_ID, COLUMN_NAME,
			 * COLUMN_DESC, COLUMN_ORDER, IF_SHOW, CLICK_URL, FUNC_ID,
			 * COLUMN_TYPE, WIDTH, HEIGHT, ALIGN, PARAM_KEY, CLICK_URL_TARGET,
			 * COLUMN_OPT, COLUMN_FORMATTER, COLUMN_FORMATOPTIONS, IF_HIDE,
			 * PARAM_VALUE_COLUMN_NAME, CORD) values (?, ?, ?, ?, ?, ?, ?, ?, ?,
			 * ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			 */
			String columnId = uuid();
			stmt = connection.prepareStatement(INSERT_COLUMN);
			stmt.setString(1, columnId);
			stmt.setString(2, tableSqlId);
			stmt.setString(3, columnModel2.getName());
			stmt.setString(4, columnModel2.getDescription());
			stmt.setString(5, columnModel2.getOrder());
			stmt.setString(6, columnModel2.getIfshow());
			stmt.setString(7, columnModel2.getClickurl());
			stmt.setString(8, diagram.getId());
			stmt.setString(9, null);
			stmt.setString(10, columnModel2.getWidth());
			stmt.setString(11, null);
			stmt.setString(12, columnModel2.getAlign());
			stmt.setString(13, columnModel2.getParamKey());
			stmt.setString(14, columnModel2.getClickUrlTarget());
			stmt.setString(15, columnModel2.getOptions());
			stmt.setString(16, columnModel2.getFormatter());
			stmt.setString(17, columnModel2.getFormatterOptions());
			stmt.setString(18, columnModel2.getIfHide());
			stmt.setString(19, columnModel2.getParamValueColumnName());
			stmt.setString(20, columnModel2.getCord());
			stmt.executeUpdate();
			/**
			 * INSERT INTO P_S_BLOCK_MTABLE(GROUP_ID,FUNC_ID,BLOCK_ID,HIERARCHY,COLSPAN_STYLE,
			 * GROUP_TITLE,COLUMN_ID,ORDER_CN,IS_FROZEN,IS_GROUP)VALUES(?,?,?,?,?,?,?,?,?,?)
			 */
			stmt = connection.prepareStatement(INSERT_PS_BLOCK_MTABLE);
			stmt.setString(1, uuid());
			stmt.setString(2, diagram.getId());
			stmt.setString(3, tableModel.getId());
			stmt.setString(4, columnModel2.getHierarchy());
			stmt.setString(5, columnModel2.getColspanStyle());
			stmt.setString(6, columnModel2.getGroupTitle());
			stmt.setString(7, columnId);
			stmt.setString(8, "".equals(columnModel2.getGroupOrder()) ? columnModel2.getOrder()
					: columnModel2.getGroupOrder());
			stmt.setString(9, columnModel2.getIsFrozen());
			stmt.setString(10, columnModel2.getIsGroup());
			stmt.executeUpdate();
		}

	}

	private static void InsertChartBlock(Connection connection, PreparedStatement stmt,
			ChartBlockModel chartModel, BlockModel parentBlock, Diagram diagram,
			List<String> tempList, Map<String, String> tempMap, int order) throws Exception
	{

		// stmt = connection.prepareStatement(CHECK_BLOCK_NAME);
		// stmt.setString(1, diagram.getId());
		// stmt.setString(2, chartModel.getSqlSet().getName());
		// ResultSet rs = stmt.executeQuery();
		// if (rs.next()) {
		// MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
		// "图表名称重复！");
		// return;
		// }

		chartModel.setId(uuid());
		stmt = connection.prepareStatement(INSERT_BLOCK);
		stmt.setString(1, chartModel.getId());
		stmt.setString(2, diagram.getId()); // FUNC_ID
		stmt.setString(3, chartModel.getWidth());// WIDTH
		stmt.setString(4, chartModel.getHeight());// HEIGHT
		stmt.setString(5, "MSCHART");// BLOCK_TYPE
		stmt.setString(6, chartModel.getName());// BLOCK_TITLE
		stmt.setString(7, chartModel.getChartType());// BLOCK_CONTENT
		stmt.setString(8, Integer.toString(order));// BLOCK_ORDER
		stmt.setString(9, parentBlock == null ? null : parentBlock.getId());// PARENT_BLOCK_ID
		stmt.setString(10, null);
		stmt.setString(11, null);
		String name = chartModel.getSqlSet().getSqls()
				.get(chartModel.getSqlSet().getSqls().size() - 1).getSqlName();
		stmt.setString(12, name == null ? null : name);
		stmt.executeUpdate();
		// 插入sql
		/*
		 * insert into P_S_SQLAREA (SQL_ID, BLOCK_ID, SQL_ORDER, FUNC_ID,
		 * SQL_DES, SQL_AREA, SQL_TYPE, DIRECT_COLUMN) values (?, ?, ?, ?, ?, ?,
		 * null, null)
		 */

		String chartId = uuid();
		for (ChartSqlAreaModel sqlModel : chartModel.getSqlSet().getSqls()) {
			String chartSqlId = uuid();

			tempList.add(chartSqlId);
			tempMap.put(chartSqlId, sqlModel.getSqlArea());
			stmt = connection.prepareStatement(INSERT_SQL);
			stmt.setString(1, chartSqlId);
			stmt.setString(2, chartModel.getId());
			stmt.setString(3, "1");
			stmt.setString(4, diagram.getId());
			stmt.setString(5, sqlModel.getSqlName());
			stmt.setString(6, sqlModel.getSqlArea());
			stmt.executeUpdate();
			// 插入图表属性
			/*insert  into P_S_BLOCK_CHART(CHART_ID,FUNCID,BLOCK_ID,SQL_ID,CHART_TYPE,TITLE,
			 * SERIES,SERIES_DESC,STACK,STACK_DES,X_AXIS,X_AXIS_DESC,X_AXIS_NUIT,Y_AXIS,
			 * Y_AXIS_DESC,Y_AXIS_NUIT,Y_AXIS_ORDER) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			 * ?, ?, ?, ?)
			 * 
			 */
			stmt = connection.prepareStatement(INSERR_BLOCK_CHART);
			stmt.setString(1, chartId);
			stmt.setString(2, diagram.getId());
			stmt.setString(3, chartModel.getId());
			stmt.setString(4, chartSqlId);
			stmt.setString(5, sqlModel.getChartType());
			stmt.setString(6, chartModel.getName());
			stmt.setString(7, sqlModel.getSeries());
			stmt.setString(8, sqlModel.getSeriesDes());
			stmt.setString(9, sqlModel.getStack());
			stmt.setString(10, sqlModel.getStackDes());
			stmt.setString(11, sqlModel.getXaxis());
			stmt.setString(12, sqlModel.getXdes());
			stmt.setString(13, sqlModel.getXunit());
			stmt.setString(14, sqlModel.getYaxis());
			stmt.setString(15, sqlModel.getYdes());
			stmt.setString(16, sqlModel.getYunit());
			stmt.setString(17, sqlModel.getYaxisNum());
			stmt.executeUpdate();
		}

	}

	private static void InsertFrameBlock(Connection connection, PreparedStatement stmt,
			FrameBlockModel frameBlockModel, BlockModel parentBlock, Diagram diagram,
			List<String> tempList, Map<String, String> tempMap, int order) throws Exception
	{

		frameBlockModel.setId(uuid());
		stmt = connection.prepareStatement(INSERT_BLOCK); // BLOCK_ID
		stmt.setString(1, frameBlockModel.getId());
		stmt.setString(2, diagram.getId()); // FUNC_ID
		stmt.setString(3, frameBlockModel.getWidth());// WIDTH
		stmt.setString(4, frameBlockModel.getHeight());// HEIGHT
		stmt.setString(5, "IFRAME");// BLOCK_TYPE
		stmt.setString(6, frameBlockModel.getName());// BLOCK_TITLE
		stmt.setString(7, frameBlockModel.getUrl());// BLOCK_CONTENT
		stmt.setString(8, Integer.toString(order));// BLOCK_ORDER
		stmt.setString(9, parentBlock == null ? null : parentBlock.getId());// PARENT_BLOCK_ID
		stmt.setString(10, null);
		stmt.setString(11, frameBlockModel.getIsShowBorder());
		stmt.setString(12, null);
		stmt.executeUpdate();
	}

	private static void InsertQueryBlock(Connection connection, PreparedStatement stmt,
			QueryBlockModel queryBlockModel, BlockModel parentBlock, Diagram diagram,
			List<String> tempList, Map<String, String> tempMap, int order) throws Exception
	{

		/*
		 * insert into P_S_BLOCK (BLOCK_ID, FUNC_ID, WIDTH, HEIGHT, BLOCK_TYPE,
		 * BLOCK_TITLE, BLOCK_ORDER) values (?, ?, ?, ?, ?, ?,?)
		 */
		queryBlockModel.setId(uuid());
		stmt = connection.prepareStatement(INSERT_BLOCK); // BLOCK_ID
		stmt.setString(1, queryBlockModel.getId());
		stmt.setString(2, diagram.getId()); // FUNC_ID
		stmt.setString(3, "100%");// WIDTH
		stmt.setString(4, null);// HEIGHT
		stmt.setString(5, "QUERY");// BLOCK_TYPE
		stmt.setString(6, queryBlockModel.getName());// BLOCK_TITLE
		stmt.setString(7, null);// BLOCK_CONTENT
		stmt.setString(8, Integer.toString(order));// BLOCK_ORDER
		stmt.setString(9, parentBlock == null ? null : parentBlock.getId());// PARENT_BLOCK_ID
		stmt.setString(10, null);
		stmt.setString(11, null);
		stmt.setString(12, null);
		stmt.executeUpdate();
		int paramNum = 0;
		int horizonNum = 0;
		// 插入查询参数
		for (int i = 0; i < queryBlockModel.getElements().size(); i++) {
			// 插入参数
			/*
			 * insert into P_S_PARAM (PARAM_ID, FUNC_ID, PARAM_NAME, SHOW_NAME,
			 * IF_SHOW, PARAM_UI_TYPE, CODE_NAME, ROW_NUM, COL_NUM, OPTIONAL)
			 * values (?, ?, ?, ?, ?,? ?, ?, ?, ?)
			 */
			Element element = queryBlockModel.getElements().get(i);
			if (element instanceof ParamModel) {
				paramNum++;
				horizonNum++;
				writeParamModelToDb((ParamModel) element, connection, stmt, diagram, tempList,
						tempMap, order, paramNum, horizonNum);
			} else if (element instanceof QueryHorizontalBlockModel) {
				List<Element> params = ((QueryHorizontalBlockModel) element).getElements();
				horizonNum++;
				for (Element param : params) {
					paramNum++;
					if (param instanceof ParamModel)
						writeParamModelToDb((ParamModel) param, connection, stmt, diagram,
								tempList, tempMap, order, paramNum, horizonNum);
				}
			}

		}

	}

	private static void writeParamModelToDb(ParamModel paramModel, Connection connection,
			PreparedStatement stmt, Diagram diagram, List<String> tempList,
			Map<String, String> tempMap, int order, int paramNum, int horizonNum)
			throws SQLException
	{
		// TODO Auto-generated method stub
		stmt = connection.prepareStatement(INSERT_PARAM);
		// paramModel.setId(uuid());
		stmt.setString(1, paramModel.getId());
		stmt.setString(2, diagram.getId());
		stmt.setString(3, paramModel.getName());
		stmt.setString(4, paramModel.getDescription());
		stmt.setString(5, "Y");
		if (paramModel instanceof ParamTreeModel) {
			String treeType;
			if (paramModel.getTreeType() == 2)
				treeType = "CHECKBOX_TREE";
			else if (paramModel.getTreeType() == 1)
				treeType = "RADIO_TREE";
			else if (paramModel.getTreeType() == 0)
				treeType = "TREE";
			else
				treeType = "SELECT";
			stmt.setString(6, treeType);
		} else {
			stmt.setString(6, paramModel.getType());
		}
		stmt.setString(7, paramModel.getCodeName());
		stmt.setString(8, String.valueOf(horizonNum));
		stmt.setString(9, paramModel.getColNum());
		stmt.setInt(10, paramModel.getOptional());
		stmt.setInt(11, paramNum);
		stmt.setString(12, paramModel.getParentId());
		stmt.executeUpdate();
		if (paramModel.getSql() != null && !"".equals(paramModel.getSql().trim())) {
			// 插入参数关联的sql
			String paramSqlId = uuid();
			stmt = connection.prepareStatement(INSERT_SQL);
			stmt.setString(1, paramSqlId);
			stmt.setString(2, null);
			stmt.setString(3, null);
			stmt.setString(4, null);
			stmt.setString(5, paramModel.getDescription());
			stmt.setString(6, paramModel.getSql());
			stmt.executeUpdate();

			stmt = connection.prepareStatement(INSERT_PARAM_SQL);
			stmt.setString(1, paramModel.getId());
			stmt.setString(2, paramSqlId);
			stmt.executeUpdate();
		}
		// 插入查询参数对应的sql
		// insert into P_S_PARAM_SQL (PARAM_ID, SQL_ID) values
		// (?, ?)
		for (String sqlId : tempList) {
			if (tempMap.get(sqlId).contains(paramModel.getParamLocation())) {
				// 插入查询条件
				/*
				 * insert into P_S_PARAM_CRITERIA (SQL_ID, CRITERIA_AREA,
				 * PARAM_LOCATION, CRITERIA_DESC, ID, PARAM_NAME) values (?,
				 * ?, ?, ?, ?, ?)
				 */
				stmt = connection.prepareStatement(INSERT_PARAM_CRITERIA);
				stmt.setString(1, sqlId);
				stmt.setString(2, paramModel.getCriteriaArea());
				stmt.setString(3, paramModel.getParamLocation());
				stmt.setString(4, paramModel.getDescription());
				stmt.setString(5, uuid());
				stmt.setString(6, paramModel.getName());
				stmt.executeUpdate();
			}
		}
	}

	private static void InsertBlock(Connection connection, PreparedStatement stmt,
			BlockModel blockModel, BlockModel parentBlock, Diagram diagram, List<String> tempList,
			Map<String, String> tempMap, int order) throws Exception
	{
		blockModel.setId(uuid());
		stmt = connection.prepareStatement(INSERT_BLOCK); // BLOCK_ID
		stmt.setString(1, blockModel.getId());
		stmt.setString(2, diagram.getId()); // FUNC_ID
		stmt.setString(3, "100%");// WIDTH
		stmt.setString(4, null);// HEIGHT
		stmt.setString(5, "BLOCK");// BLOCK_TYPE
		stmt.setString(6, blockModel.getName());// BLOCK_TITLE
		stmt.setString(7, null);// BLOCK_CONTENT
		stmt.setString(8, Integer.toString(order));// BLOCK_ORDER
		stmt.setString(9, parentBlock == null ? null : parentBlock.getId());// PARENT_BLOCK_ID
		stmt.setString(10, null);
		stmt.setString(11, null);
		stmt.setString(12, null);
		stmt.executeUpdate();

	}

	private static void GetAllBlockModel(BlockModel block, Connection connection,
			PreparedStatement stmt, Diagram diagram, List<String> tempList,
			Map<String, String> tempMap)
	{
		for (Element element : block.getElements()) {
			if (element instanceof TableModel) {
				try {
					InsertTable(connection, stmt, (TableModel) element, block, diagram, tempList,
							tempMap, count++);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
							"列表配置有数据为空！请确认！");
				}
			} else if (element instanceof ChartBlockModel) {
				try {
					InsertChartBlock(connection, stmt, (ChartBlockModel) element, block, diagram,
							tempList, tempMap, count++);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
							"图表配置有数据为空，请确认！");
				}
			} else if (element instanceof FrameBlockModel) {
				try {
					InsertFrameBlock(connection, stmt, (FrameBlockModel) element, block, diagram,
							tempList, tempMap, count++);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
							"IFrame中链接为空，请确认！");
				}
			} else if (element instanceof BlockModel) {
				try {
					InsertBlock(connection, stmt, (BlockModel) element, block, diagram, tempList,
							tempMap, count++);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GetAllBlockModel((BlockModel) element, connection, stmt, diagram, tempList, tempMap);
			}
		}

		for (Element element : block.getElements()) {
			if (element instanceof QueryBlockModel) {
				try {
					InsertQueryBlock(connection, stmt, (QueryBlockModel) element, block, diagram,
							tempList, tempMap, 0);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
							"查询条件有数据为空，请检查参数对应条件和条件追加位置！");
				}
			}
		}
	}

	private static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void close(Connection connection, PreparedStatement stmt, ResultSet rs)
	{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test()
	{
		DataSourceModel dataSourceModel = new DataSourceModel();
		dataSourceModel.setName("oralce");
		java.util.List<DataObjectModel> objectModels = new ArrayList<DataObjectModel>();
		for (int i = 0; i < 10; i++) {
			DataObjectModel objectModel = new DataObjectModel();
			objectModel.setName("object " + i);
			objectModels.add(objectModel);
		}
		dataSourceModel.setObjectModels(objectModels);
		System.out.println(insert(dataSourceModel));
	}

	public static String replace(String str)
	{
		if (str != null) {
			str = str.replaceAll(" ", "&nbsp;");
			return str;
		}
		return null;
	}
}
