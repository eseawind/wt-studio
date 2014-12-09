package com.wt.studio.plugin.modeldesigner;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.modeldesigner"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
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

	/**
	 * 获取注册图标
	 * 
	 * @param key
	 * @return
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		//return getDefault().getImageRegistry().getDescriptor(key);
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, key);
		return descriptor;
		//return ImageDescriptor.createFromFile(null,getClassForStatic().getProtectionDomain().getCodeSource().getLocation().toString().replaceFirst("file:/", "")+key);
	}

	/**
	 * 注册图片
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry imageregistry) {
		super.initializeImageRegistry(imageregistry);
		imageregistry.put(ImageResource.ARROW,
				loadImageDescriptor("icons/arrow16.gif"));
		imageregistry.put(ImageResource.TABLE,
				loadImageDescriptor("icons/model_new.gif"));
		imageregistry.put(ImageResource.VIEW,
				loadImageDescriptor("icons/view.gif"));
		imageregistry.put(ImageResource.RELATION_1_N,
				loadImageDescriptor("icons/relation_1_n.gif"));
		imageregistry.put(ImageResource.RELATION_N_N,
				loadImageDescriptor("icons/relation_n_n.gif"));
		imageregistry.put(ImageResource.RELATION_SELF,
				loadImageDescriptor("icons/relation_self.gif"));
		imageregistry.put(ImageResource.ZOOM_IN,
				loadImageDescriptor("icons/magnifier-zoom.png"));
		imageregistry.put(ImageResource.ZOOM_OUT,
				loadImageDescriptor("icons/magnifier-zoom-out.png"));
		imageregistry.put(ImageResource.ZOOM_ADJUST,
				loadImageDescriptor("icons/magnifier-zoom-actual.png"));
		imageregistry.put(ImageResource.PKImage,
				loadImageDescriptor("icons/pkey.gif"));
		imageregistry.put(ImageResource.FKImage,
				loadImageDescriptor("icons/fkey.gif"));
		imageregistry.put(ImageResource.EMPTY,
				loadImageDescriptor("icons/empty.gif"));
		imageregistry.put(ImageResource.VO,
				loadImageDescriptor("icons/vo.png"));
		imageregistry.put(ImageResource.BO,
				loadImageDescriptor("icons/bo.png"));
		imageregistry.put(ImageResource.MO,
				loadImageDescriptor("icons/mo.png"));
		imageregistry.put(ImageResource.LIST,
				loadImageDescriptor("icons/control/list.png"));
		imageregistry.put(ImageResource.PICTURE,
				loadImageDescriptor("icons/picture.png"));
		imageregistry.put(ImageResource.CHECK,
				loadImageDescriptor("icons/check.png"));
		imageregistry.put(ImageResource.RADIO,
				loadImageDescriptor("icons/radio.png"));
		imageregistry.put(ImageResource.PAGE_DATE,
				loadImageDescriptor("icons/control/date.png"));

	}

	private static ImageDescriptor loadImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
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
		ILog log = Activator.getDefault().getLog();
		Status status = new Status(IStatus.INFO, Activator.getDefault().getDescriptor()
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
		ILog log = Activator.getDefault().getLog();
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		String msg = stringWriter.getBuffer().toString();
		Status status = new Status(IStatus.ERROR, Activator.getDefault().getDescriptor()
				.getUniqueIdentifier(), IStatus.ERROR, msg, null);
		log.log(status);
	}

	
	/**
	 * Return the target program setted in EasyExplorePreferencePage.
	 * 
	 * @return String
	 */
	public String getTarget()
	{
		return getPreferenceStore().getString(PLUGIN_ID);
	}

	
	public boolean isSupported()
	{
		String osName = System.getProperty("os.name");
		return ((osName.indexOf("Windows") != -1) || (osName.indexOf("Mac") != -1));
	}
	private static final Class getClassForStatic() {  
        return new Object() {  
            public Class getClassForStatic() {      
                return this.getClass();      
            }    
        }.getClassForStatic();  
    }  
}
