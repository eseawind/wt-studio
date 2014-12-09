package com.wt.studio.plugin.tools.startup;

import org.eclipse.ui.IStartup;

import com.wt.studio.plugin.tools.utils.MonitorUtil;

public class MonitorStartup implements IStartup
{

	@Override
	public void earlyStartup()
	{
		MonitorUtil.jobWork();
				
	}

}
