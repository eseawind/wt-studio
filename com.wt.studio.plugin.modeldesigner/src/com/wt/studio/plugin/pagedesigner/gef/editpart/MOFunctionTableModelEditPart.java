package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

import org.dom4j.DocumentException;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.pagedesigner.gef.dialog.MOFunctionDialog;
import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionTableFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.FunctionModelEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.TableotoGraphicalNodeEditPolicy;

public class MOFunctionTableModelEditPart extends FunctionTableModelEditPart implements NodeEditPart
{

	private FunctionTableFigure figure;
	
	protected IFigure createFigure()
	{
		if(figure==null)
			figure=new FunctionTableFigure();
		figure.setTableModel((MOFunctionTableModel)this.getModel());
		return figure;
	}

	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				performRequestOpen();

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}

	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if ( prop.equals(FunctionModel.PROP_RECTANGLE)||prop.equals(MOFunctionTableModel.PROP_DBURL)
				||prop.equals(MOFunctionTableModel.PROP_NAME)) {
			refreshVisuals();
		} else if(prop.equals(FunctionModel.PROP_COLUMN)){
			refreshChildren();
		}else if(prop.equals(FunctionColumnModel.PROP_INPUTS))
            refreshTargetConnections();
         else if(prop.equals(FunctionColumnModel.PROP_OUTPUTS))
            refreshSourceConnections();
	}
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy("EditPolicy", new FunctionModelEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new TableotoGraphicalNodeEditPolicy());
	}
	private void performRequestOpen() throws FileNotFoundException, MalformedURLException, DocumentException
	{
		// TODO Auto-generated method stub
		MOFunctionTableModel model=(MOFunctionTableModel)this.getModel();
		MOFunctionTableModel tempModel=model.copyData();
		
		MOFunctionDialog funs=new MOFunctionDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),tempModel,this);
		if (funs.open() == IDialogConstants.OK_ID) {
			model.getCopydata(tempModel);
			tempModel=null;
		}
	}
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
	{
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request)
	{
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
	{
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request)
	{
		// TODO Auto-generated method stub
		return new ChopboxAnchor(getFigure());
	}
	  protected List getModelSourceConnections() {
	        return ((MOFunctionTableModel) this.getModel()).getOutgoingConnections();
	    }

	    protected List getModelTargetConnections() {
	        return ((MOFunctionTableModel) this.getModel()).getIncomingConnections();
	    }
}
