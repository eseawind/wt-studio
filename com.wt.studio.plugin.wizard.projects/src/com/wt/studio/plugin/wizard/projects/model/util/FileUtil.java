package com.wt.studio.plugin.wizard.projects.model.util;

/**
 * 修改文件
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil
{
	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public String read(String filePath)
	{
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();

		try {
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(new FileReader(filePath));

			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				if (line.startsWith("a")) {
					buf.append(line).append(" start with a");
				} else if (line.startsWith("b")) {
					buf.append(line).append(" start with b");
				}
				// 如果不用修改, 则按原来的内容回写
				else {
					buf.append(line);
				}
				buf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}

		return buf.toString();
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 */
	public void write(String filePath, String content)
	{
		BufferedWriter bw = null;

		try {
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(filePath));
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
	}

	public void fileAppender(String fileName, String key, String value) throws IOException
	{

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String newline = System.getProperty("line.separator");
		String line = null;
		// 一行一行的读
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			line = line.replaceAll("\\$\\{" + key + "\\}", value);
			sb.append(line);
			sb.append(newline);
		}
		reader.close();

		// 写回去
//		RandomAccessFile mm = new RandomAccessFile(fileName, "rw");
//		mm.writeBytes(sb.toString());
//		mm.close();
		FileWriter write = new FileWriter(new File(fileName));
		write.write(sb.toString());
		write.close();
	}

}
