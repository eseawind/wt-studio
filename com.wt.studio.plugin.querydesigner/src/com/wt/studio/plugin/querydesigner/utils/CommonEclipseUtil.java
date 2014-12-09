package com.wt.studio.plugin.querydesigner.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.wt.studio.plugin.querydesigner.Activator;

public class CommonEclipseUtil
{
	public static String getWorkspacePath()
	{
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
	}

	public static URL getResource(String name)
	{
		return Platform.getBundle(Activator.PLUGIN_ID).getResource(name);
	}

	public static ImageDescriptor getImage(String imagePath)
	{
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, imagePath);
		return descriptor;
	}

	public static IWorkbenchPage getWorkbenchPage()
	{
		IWorkbench workbench;
		label: try {
			workbench = PlatformUI.getWorkbench();
			if (workbench == null) {
				break label;
			}
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			if (workbenchWindow == null) {
				break label;
			}
			return workbenchWindow.getActivePage();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static IEditorPart getActiveEditor()
	{
		return getWorkbenchPage().getActiveEditor();
	}

	public static IViewPart findView(String id)
	{
		return getWorkbenchPage().findView(id);
	}

	/**
	 * 方法说明：获取已配置的数据源描述文件
	 * 
	 * @return
	 */
	public static IConnectionProfile[] getConnectionProfiles()
	{
		return ProfileManager.getInstance().getProfiles();
	}

	public static java.sql.Connection getConnectionByProfile(IConnectionProfile profile)
	{
		if (profile == null) {
			return null;
		}
		return (java.sql.Connection) profile.createConnection(
				profile.getProvider().getConnectionFactory("java.sql.Connection").getId())
				.getRawConnection();
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
	 * @param dbURL
	 *            数据库连接串
	 * @param username
	 *            数据库用户名
	 * @param password
	 *            数据库密码
	 */
	public static void createConnectiontProfile(DataBaseType dataBaseType, String profileName,
			String description, String jarList, String driverClass, String dbURL, String username,
			String password)
	{
		try {
			ProfileManager pm = ProfileManager.getInstance();
			IConnectionProfile profile = pm.getProfileByName(profileName);
			if (profile != null) {
				pm.deleteProfile(profile);
			}
			Properties baseProperties = new Properties();
			baseProperties.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, jarList);
			baseProperties.setProperty(IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID,
					driverClass);
			baseProperties.setProperty(IJDBCConnectionProfileConstants.URL_PROP_ID, dbURL);
			baseProperties.setProperty(IJDBCConnectionProfileConstants.USERNAME_PROP_ID, username);
			baseProperties.setProperty(IJDBCConnectionProfileConstants.PASSWORD_PROP_ID, password);
			baseProperties.setProperty(IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
					String.valueOf(true));
			pm.createProfile(profileName, description, dataBaseType.getProviderID(), baseProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getColumnsFromSql(IConnectionProfile profile, String sql)
	{
		List<String> result = new ArrayList<String>();
		if (sql != null) {
			Connection connection = getConnectionByProfile(profile);
			if (connection != null) {
				try {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						result.add(rsmd.getColumnLabel(i));
					}
					rs.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						connection.close();
					} catch (Exception e2) {
					}
				}
			}
		}
		return result;
	}

	public static List<Table> getTablesFromProfile(IConnectionProfile profile)
	{
		List<Table> result = new ArrayList<Table>();
		Connection connection = getConnectionByProfile(profile);
		if (connection != null) {
			try {
				DatabaseMetaData dbmd = connection.getMetaData();
				ResultSet rs = dbmd.getTables(
						null,
						profile.getBaseProperties()
								.getProperty(IJDBCConnectionProfileConstants.USERNAME_PROP_ID)
								.toUpperCase(), null, new String[] { "TABLE", "VIEW" });
				while (rs.next()) {
					Table table = new Table();
					table.setTableName(rs.getString("TABLE_NAME"));
					table.setTableType(rs.getString("TABLE_TYPE"));
					result.add(table);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String getMetaDataXML()
	{
		try {
			return FileLocator.toFileURL(getResource("/xml/metadata.xml")).getFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> dataObjectMap = new HashMap<String, String>();

	public static Map<String, String> listDataObjectsFromProfile(IConnectionProfile profile)
	{
		return dataObjectMap;
	}

	public static void errorDialogWithStackTrace(String msg, Throwable t)
	{

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);

		final String trace = sw.toString(); // stack trace as a string

		// Temp holder of child statuses
		List<Status> childStatuses = new ArrayList<Status>();

		// Split output by OS-independend new-line
		for (String line : trace.split(System.getProperty("line.separator"))) {
			// build & add status
			childStatuses.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, line));
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, IStatus.ERROR,
				childStatuses.toArray(new Status[] {}), // convert to array of statuses
				t.getLocalizedMessage(), t);

		ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Error", msg, ms);
	}

	public static boolean testSql(IConnectionProfile connectionProfile, String sql)
			throws SQLException
	{
		Connection connection = getConnectionByProfile(connectionProfile);
		if (connection != null) {
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeQuery();
				pstmt.close();
				return true;
			} catch (SQLException e2) {
				throw e2;
			} finally {
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void showError(String message)
	{
		MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", message);
	}

	public static void showInfomation(String message)
	{
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Info", message);
	}

	public static void main(String[] args)
	{
	}

}