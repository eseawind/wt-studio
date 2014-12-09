package com.wt.studio.plugin.tools.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.wt.studio.plugin.tools.Activator;

public class MonitorUtil {
	private static int flag = 0;

	/**
	 * 
	 * 方法说明：启动Eclipse自动运行的job创建方法
	 * 
	 */
	public static void jobWork() {
		Job job = new Job("Monitor") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					doLongThing();
					return Status.OK_STATUS;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					schedule(1000);
				}
				return Status.OK_STATUS;
			}
		};

		job.setUser(true);
		job.schedule();
	}

	/**
	 * 
	 * 方法说明：Post方式发送监控信息
	 * 
	 * @throws IOException
	 */
	private static void doLongThing() throws IOException {
		try {
			// We simulate a long running operation here
			Thread.sleep(1000 );
			Map<String, String> params = new HashMap<String, String>();
			params.put("Userid", getUser());
			params.put("Version", getVersion());
			params.put("Content", flag == 0 ? "启动......." : "使用.......");
			HttpClientUtil.httpPostForm(
					"http://localhost:8080/monitor/rest/app/monitor",
					params);
			flag++;
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	/**
	 * 
	 * 方法说明：取的用户名
	 *
	 * @return
	 */
	private static String getUser() {
		String user = Activator.getDefault().getPreferenceStore()
				.getString("UserNameStringPreference");
		return user;
	}
	/**
	 * 
	 * 方法说明：取得版本号
	 *
	 * @return
	 */
	private static String getVersion() {
		String version = Platform.getBundle("com.hirisun.ide.plugin.tools").getVersion()
				.toString();
		return version;
	}	
}
