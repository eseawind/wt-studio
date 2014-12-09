package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.ToggleButton;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowBox;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.draw2d.text.TextFragmentBox;
import org.eclipse.draw2d.widgets.MultiLineLabel;
import org.eclipse.swt.widgets.Text;


import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;
import com.wt.studio.plugin.pagedesigner.gef.layout.HorizonFillLayout;
import com.wt.studio.plugin.pagedesigner.gef.layout.HorizontalLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.ButtonModel;
import com.wt.studio.plugin.pagedesigner.gef.model.CheckBoxModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.DateModel;
import com.wt.studio.plugin.pagedesigner.gef.model.InputModel;
import com.wt.studio.plugin.pagedesigner.gef.model.LabelModel;
import com.wt.studio.plugin.pagedesigner.gef.model.RadioBoxModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.tool.CommonEclipseUtil;

public class ControlModelFigure extends Figure
{

	private ControlModel controlModel;
	private IFigure container;
	private Label descriptionLabel;
	public ControlModelFigure()
	{
		ToolbarLayout flow=new ToolbarLayout();
		flow.setHorizontal(true);
		setLayoutManager(flow);
	}

	public ControlModel getControlModel()
	{
		return controlModel;
	}

	public void setControlModel(ControlModel model)
	{
		this.controlModel = model;
		this.repaint();
	}
	public void setWidget(String type)
	{
		if (container != null) {
			this.remove(container);
		}
		if (descriptionLabel != null) {
			this.remove(descriptionLabel);
		}
		if (ControlModel.TYPE_INPUT.equals(type)) {	
			container = this.createInput(((InputModel)controlModel).getName(),
					((InputModel)controlModel).getIsNecessary());
		} else if (ControlModel.TYPE_BUTTON.equals(type)) {
			container = this.createButton();
		} else if (ControlModel.TYPE_CHECK.equals(type)) {
			container = this.createCheck();
		}else if (ControlModel.TYPE_TEXTAREA.equals(type)) {
			container = this.createTextArea(((TextAreaModel)controlModel).getName());
		}else if (ControlModel.TYPE_LABEL.equals(type)) {
			container = this.createLabel();
		}else if (ControlModel.TYPE_DATE.equals(type)) {
			container = this.createDateControl(((DateModel)controlModel).getName());
		}else if (ControlModel.TYPE_TABLE.equals(type)) {
			container = this.createTableControl();
		}else if (ControlModel.TYPE_LIST.equals(type)) {
			container = this.createListControl();
		}else if(ControlModel.TYPE_PICTURE.equals(type)){
			container=this.createPictureControl();
		}else if(ControlModel.TYPE_EDITOR.equals(type)){
			container=this.createEditorControl();
		}else if(ControlModel.TYPE_RADIO.equals(type)){
			container=this.createRadioControl();
		}
		else {
			container = null;
		}
		if (container != null) {
			this.add(container);
			this.repaint();
		}
	}

	private IFigure createRadioControl()
	{
		String title=((RadioBoxModel)controlModel).getName();
		String value=((RadioBoxModel)controlModel).getValue();
		RadioBoxFigure radio = new RadioBoxFigure(title,value);
		return radio;
	}

	private IFigure createEditorControl()
	{
		FrameBorder editor=new FrameBorder("编辑器");
		editor.setLabel(controlModel.getName());// TODO Auto-generated method stub
		this.setBorder(editor);
		return null;
	}

	private IFigure createPictureControl()
	{
		// TODO Auto-generated method stub
		PictureFigure figure=new PictureFigure();// TODO Auto-generated method stub
		
		//figure.setImage(Activator.getImageDescriptor(ImageResource.PICTURE).createImage());
		return figure;
	}

	private IFigure createTableControl()
	{
		FrameBorder table=new FrameBorder("列表");
		table.setLabel(controlModel.getName());// TODO Auto-generated method stub
		this.setBorder(table);
		ToolbarLayout tool=new ToolbarLayout();
		tool.setHorizontal(true);
		tool.setSpacing(5);
		this.setLayoutManager(tool);
		return null;
	}

	private IFigure createListControl()
	{
		SelectModelFigure select=new SelectModelFigure(controlModel.getName());
		//ImageFigure figure=new ImageFigure();// TODO Auto-generated method stub
		//figure.setImage(Activator.getImageDescriptor(ImageResource.LIST).createImage());
		return select;
	}

	public IFigure createInput(String string,int flag)
	{
		TextFieldFigure input=new TextFieldFigure(string,flag);
		return input;
	}

	public IFigure createButton()
	{
		ButtonFigure button=new ButtonFigure();
		button.setName(((ButtonModel)controlModel).getName());
		return button;
	}
	public IFigure createCheck()
	{
		/*Figure com=new Figure();
		ToolbarLayout tool=new ToolbarLayout();
		tool.setHorizontal(true);
		com.setLayoutManager(new ToolbarLayout());
		Label title=new Label(((CheckBoxModel)controlModel).getName()+"    ");
		com.add(title);
		String[] children=((CheckBoxModel)controlModel).getValue().split(";");
		for(String child:children)
		{
		CheckBoxFigure check = new CheckBoxFigure(child+"    ");
		com.add(check);
		}
		return com;*/
		String title=((CheckBoxModel)controlModel).getName();
		String value=((CheckBoxModel)controlModel).getValue();
		CheckBoxFigure check = new CheckBoxFigure(title,value);
		return check;
		
	}
	public IFigure createTextArea(String string)
	{
		TextAreaFigure text = new TextAreaFigure(string);
		return text;
	}

	public IFigure createLabel()
	{
		Label label = new Label(((LabelModel)controlModel).getName());
		return label;
	}
	public IFigure createDateControl(String string)
	{
		DateControlModelFigure date=new DateControlModelFigure(string);
		return date;
	}
	

}