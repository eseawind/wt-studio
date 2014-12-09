package com.wt.studio.plugin.platform.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.platform.exports.WarExportPage;

/**
 * 
 * <pre>
 * 业务名:帮助类
 * 功能说明: 帮助类
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HeaProjectHelp
{
	/**
	 * 
	 * 方法说明：拷贝文件
	 * 
	 * @param heaProjectModel
	 *            heaProjectModel
	 */
	public static void copyPorectFile(HeaProjectModel heaProjectModel)
	{
		String outputFile = heaProjectModel.getRoot() + "/" + "test";
		try {
			FileUtil.doCopy(heaProjectModel.getWebapp(), outputFile);

			IClasspathEntry[] libPath = heaProjectModel.getLibPath();
			for (IClasspathEntry e : libPath) {
				if (e.getEntryKind() == 4) {
					String srcFile = JavaCore.getResolvedClasspathEntry(e).getPath().toString();
					int i = JavaCore.getResolvedClasspathEntry(e).getPath().toString()
							.lastIndexOf("/");
					String fileName = JavaCore.getResolvedClasspathEntry(e).getPath().toString()
							.substring(i + 1);
					FileUtil.copyFile(srcFile, outputFile + "/WEB-INF/lib/" + fileName, true);
				}
			}
			FileUtil.doCopy(heaProjectModel.getBuild(), outputFile + "/WEB-INF");
			FileUtil.deleteFile(new File(outputFile + "/WEB-INF/test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法说明：设置uum
	 * 
	 * @param heaProjectModel
	 *            heaProjectModel
	 * @param uumAddr
	 *            uumAddr
	 */
	public static void settingUUMAddr(HeaProjectModel heaProjectModel, String uumAddr)
	{
		String outputFile = heaProjectModel.getRoot() + "/" + "test";
		FileUtil.updateAllFile(outputFile + "/WEB-INF/classes/uum-rest-client.properties",
				"SERVER_URL=", "SERVER_URL=" + uumAddr);
	}

	/**
	 * 
	 * 方法说明：设置jndi
	 * 
	 * @param heaProjectModel
	 *            heaProjectModel
	 * @param warExprotPage
	 *            warExprotPage
	 */
	public static void settingJNDI(final HeaProjectModel heaProjectModel,
			final WarExportPage warExprotPage)
	{

		Display.getDefault().syncExec(new Runnable() {
			public void run()
			{
				final String oldStr = warExprotPage.getJndiCombo().getText();

				// String oldStr = "";
				// try {
				// oldStr = warExprotPage.getJndiCombo().getText();
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				try {
					String defRef = "";
					File file = new File(heaProjectModel.getDataSource());
					String outputFile = heaProjectModel.getRoot() + "/" + "test";
					if (file.exists()) {
						List<RefModel> jndiList = FileUtil.readXml(file);
						for (RefModel refModel : jndiList) {
							if (null != refModel.getDefRef()) {
								defRef = refModel.getDefRef();
							}
						}
						FileUtil.updateFile(
								new File(
										outputFile
												+ "/WEB-INF/classes/com/hirisun/hea/common/config/applicationContext-dataSource.xml"),
								"ref=\"" + defRef + "\"", "ref=\"" + oldStr + "\"");
					} else {
						List<RefModel> jndiList = FileUtil.readXml(new File(heaProjectModel
								.getSrc()
								+ "/main/resources/com/hirisun/"
								+ heaProjectModel.getName()
								+ "/config/applicationContext-dataSource.xml"));
						for (RefModel refModel : jndiList) {
							if (null != refModel.getDefRef()) {
								defRef = refModel.getDefRef();
							}
						}
						FileUtil.updateFile(new File(outputFile + "/WEB-INF/classes/com/hirisun/"
								+ heaProjectModel.getName()
								+ "/config/applicationContext-dataSource.xml"), "ref=\"" + defRef
								+ "\"", "ref=\"" + oldStr + "\"");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * 方法说明：导出war
	 * 
	 * @param heaProjectModel
	 *            heaProjectModel
	 */
	public static void exportWarfile(HeaProjectModel heaProjectModel)
	{
		String outputFile = heaProjectModel.getRoot() + "/" + "test";
		String newFolder = heaProjectModel.getRoot() + "/" + "war";
		File file = new File(newFolder);
		file.mkdirs();
		try {
			FileUtil.doCompress(file + "/" + heaProjectModel.getName() + "V" + getCurrentDate()
					+ ".war", outputFile);
			FileUtil.deleteFile(new File(outputFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法说明：获取当前时间
	 * 
	 * @return string
	 */
	public static String getCurrentDate()
	{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		return format.format(date);
	}

}
