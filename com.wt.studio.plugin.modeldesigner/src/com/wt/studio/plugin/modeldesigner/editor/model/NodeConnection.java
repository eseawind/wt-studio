package com.wt.studio.plugin.modeldesigner.editor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;


public class NodeConnection extends Element {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7885290329720266118L;
	private BONodeModel source;
    public static String PROP_BENDPOINT="bendpoint";
    List bendpoints=new ArrayList();
    private BONodeModel target;
    private double sourceAngle=Double.MAX_VALUE;
    private double targetAngle=Double.MAX_VALUE;
    protected List<HdbColumnModel> fromTargetColumns=new ArrayList<HdbColumnModel>();
    protected List<String> sourceColumnModels=new ArrayList<String>();
    protected List<String> targetColumnModels=new ArrayList<String>();
    public List<HdbColumnModel> getFromTargetColumns()
	{
		return fromTargetColumns; 
	}

	public void setFromTargetColumns(List<HdbColumnModel> formTargetColumns)
	{
		this.fromTargetColumns = formTargetColumns;
	}

	public List<String> getSourceColumnModels()
	{
		return sourceColumnModels;
	}

	public void setSourceColumnModels(List sourceColumnModels)
	{
		this.sourceColumnModels = sourceColumnModels;
	}

	public List<String> getTargetColumnModels()
	{
		return targetColumnModels;
	}

	public void setTargetColumnModels(List targetColumnModels)
	{
		this.targetColumnModels = targetColumnModels;
	}
    public double getSourceAngle()
	{
		return sourceAngle;
	}

	public void setSourceAngle(double sourceAngle)
	{
		this.sourceAngle = sourceAngle;
	}

	public double getTargetAngle()
	{
		return targetAngle;
	}

	public void setTargetAngle(double targetAngle)
	{
		this.targetAngle = targetAngle;
	}

    public void setSource(BONodeModel source) {
        this.source = source;
    }

    public void setTarget(BONodeModel target) {
        this.target = target;
    }

    public BONodeModel getTarget() {
        return this.target;
    }

    public BONodeModel getSource() {
        return this.source;
    }

    /**
     * @param source
     * @param target
     */
    public void addBendpoint(int index, ConnectionBendpoint point) {
    	
    	getBendpoints().add(index, point);
        firePropertyChange(PROP_BENDPOINT, null, null);
    }

    public void setBendpointRelativeDimensions(int index, Dimension d1, Dimension d2){
        ConnectionBendpoint cbp=(ConnectionBendpoint)getBendpoints().get(index);
        cbp.setRelativeDimensions(d1,d2);
        firePropertyChange(PROP_BENDPOINT, null, null);
    }

    public void removeBendpoint(int index) {
        getBendpoints().remove(index);
        firePropertyChange(PROP_BENDPOINT, null, null);
    }
    public List getBendpoints()
    {
    	return this.bendpoints;
    }
    public void setBendpoints(List bendpoints) {
		this.bendpoints = bendpoints;
	}
    public NodeConnection(BONodeModel source, BONodeModel target) {
        super();
        this.source = source;
        this.target = target;
        source.addOutput(this);
        target.addInput(this);
    }
    public NodeConnection(){
    	 super();
    }


	public void setSource(BONodeModel source, List<String> pColumnModels)
	{
		    this.source=source;
			for(String id:pColumnModels)
			{
				this.targetColumnModels.add(id);
				HdbColumnModel pColumn=new HdbColumnModel();
				HdbColumnModel column=(HdbColumnModel) ((HdbTableModel)this.target).getColumnById(id);
				pColumn.setCode(((HdbTableModel)this.source).checkCodeName(column.getCode()));
				String sourceId=uuid();
				pColumn.setId(sourceId);
				pColumn.setDataType(column.getDataType());
				pColumn.setFK(true);
				pColumn.setLength(column.getLength());
				pColumn.setName(((HdbTableModel)this.source).checkName(column.getName()));
				((HdbTableModel)source).addColumn(pColumn);
				this.sourceColumnModels.add(sourceId);
				this.fromTargetColumns.add(pColumn);
			}
	}

	private static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
}
