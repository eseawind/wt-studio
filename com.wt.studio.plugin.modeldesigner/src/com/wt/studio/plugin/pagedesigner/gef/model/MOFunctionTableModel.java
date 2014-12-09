package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class MOFunctionTableModel  extends FunctionModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8240779412801209657L;
	private String dbUrl="";
	public static final String PROP_DBURL="dbUrl";
	final public static String PROP_INPUTS = "INPUTS";
	protected List<TableConnection> outputs = new ArrayList<TableConnection>();
    protected List<TableConnection> inputs = new ArrayList<TableConnection>();
    final public static String PROP_OUTPUTS = "OUTPUTS";
	private String dbfile;
	private String tableName;
	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getDbFile()
	{
		return dbfile;
	}

	public void setDbFile(String dbTable)
	{
		this.dbfile = dbTable;
		
	}

	public MOFunctionTableModel()
	{
		this.title="MO";
	}

	public String getDbUrl()
	{
		return dbUrl;
	}

	public void setDbUrl(String dbUrl)
	{
		this.dbUrl = dbUrl;
		firePropertyChange(PROP_DBURL, null, title);
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, "title"),
				new TextPropertyDescriptor(PROP_DBURL, "dbUrl"),
				};
	}
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getTitle();
		}else if(PROP_DBURL.equals(id)){
			return this.getDbUrl();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id)
	{
		return true;
	}

	@Override
	public void resetPropertyValue(Object id)
	{
	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_NAME.equals(id)) {
			this.setTitle((String) value);
			firePropertyChange(PROP_NAME, null, title);
		}else if(PROP_DBURL.equals(id)){
			this.setDbUrl((String)value);
			firePropertyChange(PROP_DBURL, null, title);
		}
	}
	  @SuppressWarnings("unchecked")
	   	public void addInput(TableConnection connection) {
	           this.inputs.add(connection);
	           fireStructureChange(PROP_INPUTS, connection);
	       }

	       @SuppressWarnings("unchecked")
	   	public void addOutput(TableConnection connection) {
	    	   if(connection!=null)
	    	   {
	           this.outputs.add(connection);
	           fireStructureChange(PROP_OUTPUTS, connection);
	       }
	       }

	       public List<TableConnection> getIncomingConnections() {
	           return this.inputs;
	       }
	       public void setIncomingConnections(List<TableConnection> inputs){
	       	this.inputs=inputs;
	       }
	       public void setOutgoingConnections(List outputs){
	       	this.outputs=outputs;
	       }

	       public List getOutgoingConnections() {
	           return this.outputs;
	       }

	       public void removeInput(TableConnection connection) {
	           this.inputs.remove(connection);
	           fireStructureChange(PROP_INPUTS, connection);
	       }

	       public void removeOutput(TableConnection connection) {
	           this.outputs.remove(connection);
	           fireStructureChange(PROP_OUTPUTS, connection);
	       }

	public MOFunctionTableModel copyData()
	{
		// TODO Auto-generated method stub
		MOFunctionTableModel copy=new MOFunctionTableModel();
		copy.setTitle(this.getTitle());
		copy.setId(this.getId());
		copy.setColumns(this.getColumns());
		copy.setDbFile(this.getDbFile());
		copy.setDbUrl(this.getDbUrl());
		return copy;
	}

	public void getCopydata(MOFunctionTableModel tempModel)
	{
		// TODO Auto-generated method stub
		this.setId(tempModel.getId());
		this.setColumns(tempModel.getColumns());
		this.setDbFile(tempModel.getDbFile());
		this.setDbUrl(tempModel.getDbUrl());
		this.setTitle(tempModel.getTitle());
		this.setTableName(tempModel.getTableName());
		firePropertyChange(PROP_NAME, null, title);
		fireStructureChange(PROP_COLUMN,null);
	}

	public FunctionColumnModel getPKColumn()
	{
		for(FunctionColumnModel column:this.columns)
		{
			if(column.isPK())
				return column;
		}
		return null;
	}
}
