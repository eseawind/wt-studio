package com.wt.studio.plugin.modeldesigner.editor.model;

import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;

public class HdbColumnModel extends BONodeModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5624072939249279504L;
	private String name="";
	private String code="";
    private String dataType="";
    private int length=255;
    private boolean isPK=false;
    private boolean isFK=false;
    private boolean isSelected=false;
    public boolean isSelected()
	{
		return isSelected;
	}
	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}


	private String id;
    public String getId()
    {
    	return id;
    }
    public void setId(String id)
    {
    	this.id=id;
    }
	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getCode()
	{
		return code;
	}


	public void setCode(String code)
	{
		this.code = code;
	}


	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}


	public int getLength()
	{
		return length;
	}


	public void setLength(int length)
	{
		this.length = length;
	}


	public boolean isPK()
	{
		return isPK;
	}


	public void setPK(boolean isPK)
	{
		this.isPK = isPK;
	}


	public boolean isFK()
	{
		return isFK;
	}


	public void setFK(boolean isFK)
	{
		this.isFK = isFK;
	}


	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 100;
		}
		if (rectangle.height < 0) {
			rectangle.height = 40;
		}
		return rectangle;
	}


}
