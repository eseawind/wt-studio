package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class FunctionColumnModel extends Element  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212781445386078002L;
	private String title;
    private String id;
    private String uuid;
    private String dataType;
	private String dbColumnName;
	private boolean isPK;
	private boolean isFK;
	final public static String PROP_INPUTS = "INPUTS";
    final public static String PROP_OUTPUTS = "OUTPUTS";
    final public static String PROP_SELECT = "SELECT";
    private boolean isSelected=false;
    
    
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
    public String getDbColumnName()
	{
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName)
	{
		this.dbColumnName = dbColumnName;
	}
    public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}
    public String getUuid()
   	{
   		return uuid;
   	}

   	public void setUuid(String uuid)
   	{
   		this.uuid = uuid;
   	}
    public boolean isSelected()
	{
		return isSelected;
	}

	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
		fireStructureChange(PROP_SELECT, isSelected);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	protected List outputs = new ArrayList();
    protected List inputs = new ArrayList();
    
    @SuppressWarnings("unchecked")
   	public void addInput(ColumnConnection connection) {
           this.inputs.add(connection);
           fireStructureChange(PROP_INPUTS, connection);
       }

       @SuppressWarnings("unchecked")
   	public void addOutput(ColumnConnection connection) {
    	   if(connection!=null)
    	   {
           this.outputs.add(connection);
           fireStructureChange(PROP_OUTPUTS, connection);
       }
       }

       public List getIncomingConnections() {
           return this.inputs;
       }
       public void setIncomingConnections(List inputs){
       	this.inputs=inputs;
       }
       public void setOutgoingConnections(List outputs){
       	this.outputs=outputs;
       }

       public List getOutgoingConnections() {
           return this.outputs;
       }

       public void removeInput(ColumnConnection connection) {
           this.inputs.remove(connection);
           fireStructureChange(PROP_INPUTS, connection);
       }

       public void removeOutput(ColumnConnection connection) {
           this.outputs.remove(connection);
           fireStructureChange(PROP_OUTPUTS, connection);
       }


	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 400;
		}
		if (rectangle.height < 0) {
			rectangle.height = 500;
		}// TODO Auto-generated method stub
		return rectangle;
	}



}
