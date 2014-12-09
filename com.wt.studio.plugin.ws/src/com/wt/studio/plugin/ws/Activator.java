package com.wt.studio.plugin.ws;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.ws"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	// Resource bundle.
	private ResourceBundle resourceBundle;
	private static ImageDescriptor wizardImageDescriptor;

	/**
	 * The constructor
	 */
	public Activator() {
		try {
			resourceBundle = ResourceBundle
					.getBundle("com.hirisun.ide.plugin.ws.codegen.resource.Codegen");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static String getResourceString(String key) {
		ResourceBundle bundle = Activator.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public static ImageDescriptor getWizardImageDescriptor() {
		if (wizardImageDescriptor == null) {
			wizardImageDescriptor = Activator.imageDescriptorFromPlugin(
					"Axis2_Codegen_Wizard", "icons/ws.gif");
		}
		return wizardImageDescriptor;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
