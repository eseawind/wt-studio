package com.wt.studio.plugin.easyexplore;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.wt.studio.plugin.easyexplore.preferences.EasyExplorePreferencePage;

/**
 * The activator class controls the plug-in life cycle
 */
public class EasyExploreActivator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.easyexplore"; //$NON-NLS-1$

	// The shared instance
	private static EasyExploreActivator plugin;

	// Resource bundle.
	private ResourceBundle resourceBundle;

	/**
	 * The constructor
	 */
	public EasyExploreActivator()
	{
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static EasyExploreActivator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 * 
	 * @return IWorkspace
	 */
	public static IWorkspace getWorkspace()
	{
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 * 
	 * @param key
	 *            key
	 * @return string
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = EasyExploreActivator.getDefault().getResourceBundle();
		String res = null;
		try {
			res = bundle.getString(key);
		} catch (MissingResourceException e) {
			res = key;
		}
		return res;
	}

	/**
	 * Returns the plugin's resource bundle,
	 * 
	 * @return ResourceBundle
	 */
	public ResourceBundle getResourceBundle()
	{
		return resourceBundle;
	}

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @param msg
	 *            msg
	 */
	@SuppressWarnings("deprecation")
	static public void log(Object msg)
	{
		ILog log = EasyExploreActivator.getDefault().getLog();
		Status status = new Status(IStatus.INFO, EasyExploreActivator.getDefault().getDescriptor()
				.getUniqueIdentifier(), IStatus.ERROR, msg + "\n", null);
		log.log(status);
	}

	/**
	 * 
	 * 方法说明：
	 * 
	 * @param ex
	 *            ex
	 */
	@SuppressWarnings("deprecation")
	static public void log(Throwable ex)
	{
		ILog log = EasyExploreActivator.getDefault().getLog();
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		String msg = stringWriter.getBuffer().toString();
		Status status = new Status(IStatus.ERROR, EasyExploreActivator.getDefault().getDescriptor()
				.getUniqueIdentifier(), IStatus.ERROR, msg, null);
		log.log(status);
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeDefaultPreferences(org.eclipse.jface.preference.IPreferenceStore)
	 * @param store
	 *            store
	 */
	protected void initializeDefaultPreferences(IPreferenceStore store)
	{
		String defaultTarget = "shell_open_command {0}";
		String osName = System.getProperty("os.name");
		if (osName.indexOf("Windows") != -1) {
			defaultTarget = "explorer.exe {0}";
		} else if (osName.indexOf("Mac") != -1) {
			defaultTarget = "open {0}";
		}

		store.setDefault(EasyExplorePreferencePage.P_TARGET, defaultTarget);
	}

	/**
	 * Return the target program setted in EasyExplorePreferencePage.
	 * 
	 * @return String
	 */
	public String getTarget()
	{
		return getPreferenceStore().getString(EasyExplorePreferencePage.P_TARGET);
	}

	/**
	 * Tells whether this platform is currently supported. The implementation of this method must be
	 * in sync with the implementation of <a href="#initializeDefaultPreferences(IPreferenceStore)"
	 * >initializeDefaultPreferences(IPreferenceStore)</a>.
	 * 
	 * @return boolean
	 */
	public boolean isSupported()
	{
		String osName = System.getProperty("os.name");
		return ((osName.indexOf("Windows") != -1) || (osName.indexOf("Mac") != -1));
	}

}
