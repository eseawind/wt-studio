package com.wt.studio.plugin.wizard.projects.dbhelp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.osgi.framework.Bundle;

import com.wt.studio.plugin.platform.util.DataBaseType;

/**
 * <pre>
 * 业务名:数据库信息
 * 功能说明: 连接创建数据库类
 * 编写日期:	2012-12-17
 * 作者:	DongYibo
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HelpDBInit {
	public static final String oracleDriverClass = "oracle.jdbc.driver.OracleDriver";
	public static final String mysqlDriverClass = "com.mysql.jdbc.Driver";
	public static final String patter = "$TS";
	public static final String MYSQL_PARAMS = "?useUnicode=true&characterEncoding=UTF-8";
	List<String> sqlFileList = new ArrayList<String>();

	static {
		try {
			Class.forName(oracleDriverClass);
			Class.forName(mysqlDriverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件读放内容到按分号放到sqlFileList
	 * 
	 * @param fileNameList
	 *            fileNameList
	 * @param patter
	 *            模式
	 * @param tableSpaceName
	 *            替换表空间
	 * @return list
	 */
	public List<String> readSqlFiles(List<String> fileNameList, String patter,
			String tableSpaceName) {
		List<String> sqlList = new ArrayList<String>();
		for (String fileName : fileNameList) {
			File myFile = new File(fileName);
			if (!fileExists()) {
				System.err.println("Can't Find " + fileName);
				continue;
			}

			StringBuffer temp = new StringBuffer();
			try {
				BufferedReader in = new BufferedReader(new FileReader(myFile));
				String str;
				while ((str = in.readLine()) != null) {
					temp.append(str);
				}
				in.close();
			} catch (IOException e) {
				e.getStackTrace();
			}

			String sqls[] = temp.toString().split(";");

			for (String sql : sqls) {
				if (!"".equals(sql)) {
					sqlList.add(sql.replace(patter, tableSpaceName));
				}
			}

		}
		return sqlList;
	}

	/**
	 * 
	 * 方法说明：判断文件存在否
	 * 
	 * @return boolean
	 */
	private boolean fileExists() {
		return true;
	}

	/**
	 * 
	 * 方法说明：连接数据库
	 * 
	 * @param url
	 *            地址
	 * @param dsClass
	 *            class
	 * @param user
	 *            用户名
	 * @param pass
	 *            密码
	 * @return connection
	 * @throws SQLException
	 */
	private static Connection getConnection(String url, String dsClass,
			String user, String pass) throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}

	/**
	 * 
	 * 方法说明：加载方法
	 * 
	 * @param fileNameList
	 *            fileNameList
	 * @param url
	 *            地址
	 * @param dbClass
	 *            class
	 * @param user
	 *            用户
	 * @param pass
	 *            密码
	 * @param patter
	 *            patter
	 * @param tableSpaceName
	 *            表空间
	 * @throws Exception
	 */
	protected void initDataStore(List<String> fileNameList, String url,
			String dbClass, String user, String pass, String patter,
			String tableSpaceName) throws Exception {
		List<String> readSqlFiles = readSqlFiles(fileNameList, patter,
				tableSpaceName);
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = getConnection(url, dbClass, user, pass);
			for (String sqlText : readSqlFiles) {
				state = conn.prepareStatement(sqlText);
				state.execute();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			state.close();
			conn.close();
			state = null;
			conn = null;
		}
	}

	/**
	 * 测试连接数据库
	 * 
	 * @param ip
	 *            地址
	 * @param port
	 *            端口
	 * @param dbName
	 *            表名
	 * @param userName
	 *            用户名
	 * @param userPass
	 *            密码
	 * @return boolean
	 * @throws Exception
	 */
	public boolean doTestDBLink(String type, String ip, String port,
			String dbName, String userName, String userPass) throws Exception {
		boolean isTrue = true;
		StringBuffer dburl = getDBURL(type, ip, port, dbName);
		String classDriver = null;
		String sqlText = null;
		if (type.equals("Oracle")) {
			classDriver = oracleDriverClass;
			sqlText = "select * from dual";
		} else if (type.equals("Mysql")) {
			classDriver = mysqlDriverClass;
			sqlText = "select 1+1 x";
		}
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = getConnection(dburl.toString(), classDriver, userName,
					userPass);
			state = conn.prepareStatement(sqlText);
			state.execute();
		} catch (Exception e) {
			isTrue = false;
			throw e;
		} finally {
			if (state != null) {
				state.close();
			}
			if (conn != null) {
				conn.close();
			}
			state = null;
			conn = null;
		}
		return isTrue;
	}

	/**
	 * 
	 * 方法说明：获取表
	 * 
	 * @param verName
	 *            表空间
	 * @return list
	 */
	protected static List<String> getHEAFileList(String verName, String type) {
		List<String> fList = new ArrayList<String>();
		if (type.equals("Oracle")) {
			if ("V2.3".equals(verName)) {
				String[] fileArray = new String[] { "0_hea_db.sql",
						"1_hea_data.sql" };
				Bundle bundle = Platform
						.getBundle("com.hirisun.ide.plugin.wizard.projects");
				for (String file : fileArray) {
					String u1 = HelpDBInit.class.getResource(
							verName + "/" + file).getPath();
					try {
						String u = FileLocator
								.toFileURL(bundle.getResource(u1)).toString();

						fList.add(u.substring(6, u.length()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (type.equals("Mysql")) {
			if ("V2.3".equals(verName)) {
				String[] fileArray = new String[] { "0_hea_db_mysql.sql", "1_hea_data_mysql.sql" };
				Bundle bundle = Platform
						.getBundle("com.hirisun.ide.plugin.wizard.projects");
				for (String file : fileArray) {
					String u1 = HelpDBInit.class.getResource(
							verName + "/" + file).getPath();
					try {
						String u = FileLocator
								.toFileURL(bundle.getResource(u1)).toString();

						fList.add(u.substring(6, u.length()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return fList;
	}

	/**
	 * 
	 * 方法说明：获取uum列
	 * 
	 * @param verName
	 *            verName
	 * @return list
	 */
	protected static List<String> getUUMFileList(String verName) {
		List<String> fList = new ArrayList<String>();
		if ("V2.3".equals(verName)) {
			String[] fileArray = new String[] { "0_uum_db_main.sql" };
			for (String file : fileArray) {
				fList.add(HelpDBInit.class.getResource(verName + "/" + file)
						.getPath());
			}
		}
		return fList;
	}

	public static StringBuffer getDBURL(String type, String ip, String port,
			String dbName) {
		StringBuffer result = null;
		if (type.equals("Oracle")) {
			result = new StringBuffer("jdbc:oracle:thin:@").append(ip)
					.append(":").append(port).append(":").append(dbName);

		} else if (type.equals("Mysql")) {
			result = new StringBuffer("jdbc:mysql://").append(ip).append(":")
					.append(port).append("/").append(dbName);
		}
		return result;
	}

	/**
	 * 创建数据库
	 * 
	 * @param verName
	 *            verName
	 * @param ip
	 *            地址
	 * @param port
	 *            端口
	 * @param dbName
	 *            数据库名
	 * @param userName
	 *            用户名
	 * @param userPass
	 *            密码
	 * @param tableSpaceName
	 *            表空间
	 * @throws Exception
	 */
	public void createDB(String verName, String type, String ip, String port,
			String dbName, String userName, String userPass,
			String tableSpaceName) {

		StringBuffer dburl = getDBURL(type, ip, port, dbName);
		String classDriver = getDriverClass(type);

		if (tableSpaceName == null || "".equals(type)) {
			tableSpaceName = "USER";
		}

		try {
			new HelpDBInit().initDataStore(getHEAFileList(verName, type),
					dburl.toString(), classDriver, userName, userPass, patter,
					tableSpaceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取表内容
	 * 
	 * @param dburl
	 *            连接串
	 * @param userName
	 *            用户名
	 * @param userPass
	 *            密码
	 * @return list
	 * @throws Exception
	 */
	public static List<TableModel> getTableList(String name, String type,
			String dburl, String userName, String userPass) throws Exception {
		List<TableModel> result = new ArrayList<TableModel>();
		TableModel model = null;
		StringBuffer sqlText = new StringBuffer();
		String driverClass = null;

		if (type.equals("Oracle")) {
			driverClass = oracleDriverClass;
			sqlText.append("  SELECT T.TABLE_NAME tableName, C.COMMENTS comm")
					.append("   FROM USER_TABLES T, USER_TAB_COMMENTS C")
					.append("  WHERE T.TABLE_NAME = C.TABLE_NAME ORDER BY T.TABLE_NAME");
		} else if (type.equals("Mysql")) {
			driverClass = mysqlDriverClass;
			sqlText.append("SELECT T.TABLE_NAME tableName, T.TABLE_COMMENT comm FROM information_schema.TABLES T where T.TABLE_SCHEMA = '"
					+ HelpDBInit.getDBInstance(name) + "'");
		}

		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = getConnection(dburl, driverClass, userName, userPass);

			state = conn.prepareStatement(sqlText.toString());
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
				model = new TableModel();

				model.setTableName(rs.getString("tableName"));
				model.setComments(rs.getString("comm"));

				result.add(model);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (state != null) {
				state.close();
			}
			if (conn != null) {
				conn.close();
			}
			state = null;
			conn = null;
		}
		return result;
	}

	/**
	 * 获取表列内容
	 * 
	 * @param dburl
	 *            连接串
	 * @param userName
	 *            用户名
	 * @param userPass
	 *            密码
	 * @param tableName
	 *            表空间
	 * @return list
	 * @throws Exception
	 */
	public static List<ColumeModel> getTableColumeList(String type,
			String dburl, String userName, String userPass, String tableName)
			throws Exception {
		List<ColumeModel> result = new ArrayList<ColumeModel>();
		ColumeModel model = null;
		StringBuffer sqlText = new StringBuffer();
		String driverClass = null;
		if (type.equals("Oracle")) {
			driverClass = oracleDriverClass;
			sqlText.append(
					" SELECT T.COLUMN_NAME columeName, T.DATA_TYPE dataType, T.DATA_LENGTH, C.COMMENTS comm, lower(T.COLUMN_NAME) modelName, "
							+ "(SELECT count(1)\n"
							+ "   FROM USER_CONSTRAINTS TT, USER_CONS_COLUMNS CC\n"
							+ "  WHERE TT.TABLE_NAME = UPPER(?)\n"
							+ "    AND TT.CONSTRAINT_TYPE = 'P'\n"
							+ "    AND TT.TABLE_NAME = CC.TABLE_NAME\n"
							+ "    AND TT.CONSTRAINT_NAME = CC.CONSTRAINT_NAME\n"
							+ "    and CC.Column_Name = t.COLUMN_NAME) isKey,\n"
							+ "DECODE(T.NULLABLE,'N','1','') ISNOTNULL")
					.append("  FROM USER_TAB_COLS T, USER_COL_COMMENTS C ")
					.append(" WHERE T.TABLE_NAME = C.TABLE_NAME AND ")
					.append("       T.COLUMN_NAME = C.COLUMN_NAME AND ")
					.append("       T.TABLE_NAME = upper(?) ")
					.append(" ORDER BY T.COLUMN_ID");
		} else if (type.equals("Mysql")) {
			driverClass = mysqlDriverClass;
			sqlText.append("SELECT C.COLUMN_NAME columeName, C.COLUMN_TYPE dataType, C.COLUMN_COMMENT comm, lower(C.COLUMN_NAME) modelName, ");
			sqlText.append("       IF(C.COLUMN_KEY = 'PRI',1,0) isKey, '' ISNOTNULL ");
			sqlText.append("  FROM information_schema. COLUMNS C WHERE TABLE_NAME = ? and TABLE_NAME = ?");

		}
		Connection conn = null;
		PreparedStatement state = null;
		try {
			conn = getConnection(dburl, driverClass, userName, userPass);
			state = conn.prepareStatement(sqlText.toString());
			state.setString(1, tableName);
			state.setString(2, tableName);
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
				model = new ColumeModel();

				model.setColumeName(rs.getString("columeName"));
				if (type.equals("Oracle")) {
					model.setDataType(rs.getString("dataType"));
				} else if (type.equals("Mysql")) {
					model.setDataType(StringUtils.split(
							rs.getString("dataType"), "(")[0]);
				}
				model.setComment(rs.getString("comm"));
				model.setName(rs.getString("modelName"));
				model.setIsKey(StringUtils.equals("1", rs.getString("isKey")) ? "PK" : "");
				model.setRequid(StringUtils.equals("1", rs.getString("ISNOTNULL")) ? "非空" : "");
				model.setManageControlType("INPUT");
				model.setIsListShow(StringUtils.equals("1", rs.getString("isKey")) ? "" : "是");
				result.add(model);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (state != null) {
				state.close();
			}
			if (conn != null) {
				conn.close();
			}
			state = null;
			conn = null;
		}
		return result;
	}

	/**
	 * 方法说明：
	 * 
	 * @param dataBaseType
	 *            数据库类型
	 * @param profileName
	 *            数据源名称
	 * @param description
	 *            数据源描述
	 * @param jarList
	 *            数据库驱动jar包位置
	 * @param driverClass
	 *            数据库驱动类
	 * @param driverURL
	 *            数据库连接串
	 * @param userName
	 *            数据库用户名
	 * @param password
	 *            数据库密码
	 */
	public static void createConnectiontProfile(DataBaseType dataBaseType,
			String profileName, String description, String jarList,
			String driverClass, String driverURL, String userName,
			String password) {
		try {
			ProfileManager pm = ProfileManager.getInstance();
			IConnectionProfile defaultProfile = pm
					.getProfileByName(profileName);
			if (defaultProfile != null) {
				pm.deleteProfile(defaultProfile);
			}
			Properties baseProperties = new Properties();
			baseProperties.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST,
					jarList);
			baseProperties.setProperty(
					IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID,
					driverClass);
			baseProperties.setProperty(
					IJDBCConnectionProfileConstants.URL_PROP_ID, driverURL);
			baseProperties.setProperty(
					IJDBCConnectionProfileConstants.USERNAME_PROP_ID, userName);
			baseProperties.setProperty(
					IJDBCConnectionProfileConstants.PASSWORD_PROP_ID, password);
			baseProperties.setProperty(
					IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
					String.valueOf(true));
			pm.createProfile(profileName, description,
					dataBaseType.getProviderID(), baseProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static IConnectionProfile[] getDBProfile() {
		IConnectionProfile[] profiles = ProfileManager.getInstance()
				.getProfiles();
		return profiles;
	}

	public static ArrayList<IConnectionProfile> getOracleDBProfileList() {
		ArrayList<IConnectionProfile> dbLinks = new ArrayList<IConnectionProfile>();
		IConnectionProfile[] profiles = ProfileManager.getInstance()
				.getProfiles();
		for (IConnectionProfile profile : profiles) {
			if (StringUtils.contains(profile.getProviderId(), "oracle")) {
				dbLinks.add(profile);
			}
			if (StringUtils.contains(profile.getProviderId(), "mysql")) {
				dbLinks.add(profile);
			}
		}
		return dbLinks;
	}

	public static ArrayList<String> getDBNames() {
		ArrayList<String> dbLinks = new ArrayList<String>();
		IConnectionProfile[] profiles = ProfileManager.getInstance()
				.getProfiles();
		for (IConnectionProfile profile : profiles) {
			if (StringUtils.contains(profile.getProviderId(), "oracle")) {
				dbLinks.add(profile.getName());
			}
			if (StringUtils.contains(profile.getProviderId(), "mysql")) {
				dbLinks.add(profile.getName());
			}
		}
		return dbLinks;
	}

	private static IConnectionProfile getDBProfile(String name) {
		IConnectionProfile[] profiles = ProfileManager.getInstance()
				.getProfiles();
		for (IConnectionProfile profile : profiles) {
			if (profile.getName().equals(name)) {
				return profile;
			}
		}
		return null;
	}

	public static String getDBUrl(String name) {
		IConnectionProfile profile = getDBProfile(name);
		if (profile == null) {
			return "";
		}
		return profile.getBaseProperties().getProperty(
				IJDBCConnectionProfileConstants.URL_PROP_ID);
	}

	public static String getDBLocal(String name) {
		String dbUrl = getDBUrl(name);
		if (dbUrl.equals("")) {
			return "";
		}
		String result = null;
		if (StringUtils.indexOf(dbUrl, "jdbc:oracle") >= 0) {
			result = StringUtils.split(dbUrl, "@")[1].split(":")[0];
		} else if (StringUtils.indexOf(dbUrl, "jdbc:mysql") >= 0) {
			result = StringUtils.split(dbUrl, "//")[1].split(":")[0];
		}
		return result;
	}

	public static String getDBPort(String name) {
		String dbUrl = getDBUrl(name);
		if (dbUrl.equals("")) {
			return "";
		}
		String result = null;
		if (StringUtils.indexOf(dbUrl, "jdbc:oracle") >= 0) {
			result = StringUtils.split(dbUrl, "@")[1].split(":")[1];
		} else if (StringUtils.indexOf(dbUrl, "jdbc:mysql") >= 0) {
			result = StringUtils.split(dbUrl, "//")[1].split(":")[1];
		}
		return result;
	}

	public static String getDBInstance(String name) {
		String dbUrl = getDBUrl(name);
		if (dbUrl.equals("")) {
			return "";
		}
		String result = null;
		if (StringUtils.indexOf(dbUrl, "jdbc:oracle") >= 0) {
			result = StringUtils.split(dbUrl, "@")[1].split(":")[2];
		} else if (StringUtils.indexOf(dbUrl, "jdbc:mysql") >= 0) {
			result = StringUtils.split(dbUrl, "//")[2].split("\\?")[0];
		}
		return result;
	}

	public static String getDBUser(String name) {
		IConnectionProfile profile = getDBProfile(name);
		if (profile == null) {
			return "";
		}
		return profile.getBaseProperties().getProperty(
				IJDBCConnectionProfileConstants.USERNAME_PROP_ID);
	}

	public static String getDBPass(String name) {
		IConnectionProfile profile = getDBProfile(name);
		if (profile == null) {
			return "";
		}
		return profile.getBaseProperties().getProperty(
				IJDBCConnectionProfileConstants.PASSWORD_PROP_ID);

	}

	public static String getDBType(String name) {
		String dbUrl = getDBUrl(name);
		if (dbUrl.equals("")) {
			return "";
		}
		String result = null;
		if (StringUtils.indexOf(dbUrl, "jdbc:oracle") >= 0) {
			result = "Oracle";
		} else if (StringUtils.indexOf(dbUrl, "jdbc:mysql") >= 0) {
			result = "Mysql";
		}
		return result;
	}

	public static String getDriverClass(String type) {
		String result = null;
		if (type.equals("Oracle")) {
			result = HelpDBInit.oracleDriverClass;

		} else if (type.equals("Mysql")) {
			result = HelpDBInit.mysqlDriverClass;
		}
		return result;
	}

	public static String getHiberNateDialect(String type) {
		String result = null;
		if (type.equals("Oracle")) {
			result = "org.hibernate.dialect.Oracle10gDialect";

		} else if (type.equals("Mysql")) {
			result = "org.hibernate.dialect.MySQLDialect";
		}
		return result;
	}
}
