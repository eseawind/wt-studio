package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class FrameBlockModel extends AbstractBlockModel implements IPropertySource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7593649110384710193L;

	public static final String PROP_NAME = "标题", PROP_URL = "链接", PROP_WIDTH = "宽度",
			PROP_HEIGHT = "高度", PROP_SHOW_BORDER = "是否显示边框";
	private String name = "IFrame", width = "100%", height = "300";
	private String url = "";
	private String isShowBorder = "Y";

	public String getIsShowBorder()
	{
		return isShowBorder;
	}

	public void setIsShowBorder(String isShowBorder)
	{
		this.isShowBorder = isShowBorder;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public String getWidth()
	{
		return width;

	}

	public void setWidth(String width)
	{
		this.width = width;
		firePropertyChange(PROP_WIDTH, null, width);
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
		firePropertyChange(PROP_HEIGHT, null, height);
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
		firePropertyChange(PROP_URL, null, url);
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_WIDTH, PROP_WIDTH),
				new TextPropertyDescriptor(PROP_HEIGHT, PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_URL, PROP_URL),
				new ComboBoxPropertyDescriptor(PROP_SHOW_BORDER, PROP_SHOW_BORDER, new String[] {
						"是", "否" }) };
	}

	@Override
	public Object getEditableValue()
	{
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		// TODO Auto-generated method stub
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_URL.equals(id)) {
			return this.getUrl();
		} else if (PROP_WIDTH.equals(id)) {
			return this.getWidth();
		} else if (PROP_HEIGHT.equals(id)) {
			return this.getHeight();
		} else if (PROP_SHOW_BORDER.equals(id)) {
			if (this.getIsShowBorder().equals("N"))
				return 1;
			else
				return 0;
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		// TODO Auto-generated method stub
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		} else if (PROP_URL.equals(id)) {
			this.setUrl((String) value);
		} else if (PROP_WIDTH.equals(id)) {
			this.setWidth((String) value);
		} else if (PROP_HEIGHT.equals(id)) {
			this.setHeight((String) value);
		} else if (PROP_SHOW_BORDER.equals(id)) {
			if ((Integer) value == 1) {
				this.setIsShowBorder("N");
			} else
				this.setIsShowBorder("Y");
		}
	}

}
