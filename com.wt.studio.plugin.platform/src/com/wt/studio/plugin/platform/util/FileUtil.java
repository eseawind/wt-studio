package com.wt.studio.plugin.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tools.zip.ZipEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * <pre>
 * 业务名:文件操作类
 * 功能说明: 文件操作类
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class FileUtil
{

	/**
	 * 
	 * 方法说明：复制文件主方法
	 * 
	 * @param url1
	 *            url1
	 * @param url2
	 *            url1
	 * @throws IOException
	 */
	public static void doCopy(String url1, String url2) throws IOException
	{
		// 创建目标文件夹
		(new File(url2)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(url1)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				copyFile(file[i], new File(url2 + File.separator + file[i].getName()));
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = url1 + File.separator + file[i].getName();
				String targetDir = url2 + File.separator + file[i].getName();
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

	/**
	 * 
	 * 方法说明：复制文件
	 * 
	 * @param sourceFile
	 *            源文件
	 * @param targetFile
	 *            修改后的文件
	 * @throws IOException
	 */
	private static void copyFile(File sourceFile, File targetFile) throws IOException
	{
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 
	 * 方法说明：复制文件夹
	 * 
	 * @param sourceDir
	 *            源文件夹
	 * @param targetDir
	 *            修改后的文件夹
	 * @throws IOException
	 */
	private static void copyDirectiory(String sourceDir, String targetDir) throws IOException
	{
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator
						+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 
	 * 方法说明：删除文件
	 * 
	 * @param file
	 *            文件
	 * @return boolean
	 */
	public static boolean deleteFile(File file)
	{
		if (file == null || !file.exists()) {
			return true;
		} else if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (subFiles != null) {
				for (File subFile : subFiles) {
					if (!deleteFile(subFile)) {
						return false;
					}
				}
			}
		}
		return file.delete();
	}

	/**
	 * 
	 * 方法说明：修改所有文件内容
	 * 
	 * @param path
	 *            路径
	 * @param oldStr
	 *            源文件内容
	 * @param newStr
	 *            修改后的内容
	 */
	public static void updateAllFile(String path, String oldStr, String newStr)
	{
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 将内容插入
			buf = buf.append(newStr);

			br.close();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 方法说明：修改指定文件内容
	 * 
	 * @param path
	 *            路径
	 * @param oldStr
	 *            源文件内容
	 * @param newStr
	 *            修改后的内容
	 */
	public static void updateFile(File path, String oldStr, String newStr)
	{
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(path));
			String tempString = null;
			@SuppressWarnings("unused")
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				sb.append(tempString + "\n");
				line++;
			}
			FileWriter files = new FileWriter(path);
			files.write(sb.toString().replaceAll(oldStr, newStr));
			files.flush();
			reader.close();
			files.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	/**
	 * 
	 * 方法说明：压缩文件主方法
	 * 
	 * @param outputFileName
	 *            输入的文件
	 * @param inputFileName
	 *            输出的文件
	 * @throws Exception
	 */
	public static void doCompress(String outputFileName, String inputFileName) throws Exception
	{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFileName));
		compress(out, new File(inputFileName), "");
		out.close();
	}

	/**
	 * 
	 * 方法说明：压缩文件
	 * 
	 * @param out
	 *            输出流
	 * @param f
	 *            输出文件
	 * @param base
	 *            可无
	 * @throws Exception
	 */
	private static void compress(ZipOutputStream out, File f, String base) throws Exception
	{
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				compress(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFileName
	 *            待复制的文件名
	 * @param destFileName
	 *            目标文件名
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 */
	public static void copyFile(String srcFileName, String destFileName, boolean overlay)
	{
		File srcFile = new File(srcFileName);

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 方法说明：dom读取xml
	 * 
	 * @param file
	 *            路径
	 * @return list
	 * @throws Exception
	 */
	public static List<RefModel> readXml(File file) throws Exception
	{
		List<RefModel> list = new ArrayList<RefModel>();
		InputStream inputStream = new FileInputStream(file);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		Element element = document.getDocumentElement();

		NodeList jndiNodes = element.getElementsByTagName("jee:jndi-lookup");
		for (int i = 0; i < jndiNodes.getLength(); i++) {
			Element jndiElement = (Element) jndiNodes.item(i);
			String id = jndiElement.getAttribute("id");
			RefModel ref = new RefModel();
			ref.setJndiId(id);
			list.add(ref);
		}

		NodeList jdbcNodes = element.getElementsByTagName("bean");
		for (int i = 0; i < jdbcNodes.getLength(); i++) {
			Element jdbclement = (Element) jdbcNodes.item(i);
			NodeList childNodes = jdbclement.getChildNodes();
			if ("heaRoutingDataSourceDataSource".equals(jdbclement.getAttribute("id"))) {
				for (int j = 1; j < childNodes.getLength(); j++) {
					Element ee = (Element) childNodes.item(j);
					RefModel ref = new RefModel();
					if ("targetDataSources".equals(ee.getAttribute("name"))) {
						NodeList childNode = ee.getChildNodes();
						for (int k = 1; k < childNode.getLength(); k++) {
							Element e = (Element) childNode.item(k);
							if ("java.lang.String".equals(e.getAttribute("key-type"))) {
								NodeList node = e.getChildNodes();
								for (int l = 1; l < node.getLength(); l++) {
									Element n = (Element) node.item(l);
									String value = n.getAttribute("value-ref");
									ref.setRef(value);
									l++;
								}
							}
							k++;
						}
					}
					if ("defaultTargetDataSource".equals(ee.getAttribute("name"))) {
						String defValue = ee.getAttribute("ref");
						ref.setDefRef(defValue);
					}
					list.add(ref);
					j++;
				}
			} else {
				RefModel ref = new RefModel();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if ("property".equals(childNodes.item(j).getNodeName())) {
						Element e = (Element) childNodes.item(j);
						if ("jdbcUrl".equals(e.getAttribute("name"))) {
							ref.setJdbcUrl(e.getAttribute("value"));
						}
						if ("user".equals(e.getAttribute("name"))) {
							ref.setUser(e.getAttribute("value"));
						}
						if ("password".equals(e.getAttribute("name"))) {
							ref.setPwd(e.getAttribute("value"));
						}
					}
				}
				list.add(ref);
			}
		}
		return list;
	}

}
