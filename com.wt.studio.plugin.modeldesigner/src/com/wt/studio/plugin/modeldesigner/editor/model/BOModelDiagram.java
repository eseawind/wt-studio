package com.wt.studio.plugin.modeldesigner.editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class BOModelDiagram extends Element {
	private static final long serialVersionUID = -8356627096473811653L;

	private int x;
	private int y;
	private double zoom=1.0d;
	private PageSetting pageSetting;
	private int[] defaultColor;
	private int[] color;
	private List<NodeConnection> connections=new ArrayList<NodeConnection>();
	private List<HdbTableModel> tableModels = new ArrayList<HdbTableModel>();
	private List<ViewModel> viewModels = new ArrayList<ViewModel>();
	private List<NoteModel> noteModels = new ArrayList<NoteModel>();
	public static final String PROP_RECTANGLES = "rectangles";
	public BOModelDiagram() {
		this.pageSetting = new PageSetting();
		this.setDefaultColor(128, 128, 192);
		this.setColor(255, 255, 255);
	}

	public int[] getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(int red, int green, int blue) {
		this.defaultColor = new int[3];
		this.defaultColor[0] = red;
		this.defaultColor[1] = green;
		this.defaultColor[2] = blue;
	}

	public int[] getColor() {
		return color;
	}

	public void setColor(int red, int green, int blue) {
		this.color = new int[3];
		this.color[0] = red;
		this.color[1] = green;
		this.color[2] = blue;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PageSetting getPageSetting() {
		return pageSetting;
	}

	public void setPageSetting(PageSetting pageSetting) {
		this.pageSetting = pageSetting;
	}
	public void addTableModel(HdbTableModel rectangleModel)
	{
		tableModels.add(rectangleModel);
		fireStructureChange(PROP_RECTANGLES,tableModels);
	}

	public void removeTableModel(HdbTableModel rectangleModel)
	{
		tableModels.remove(rectangleModel);
		fireStructureChange(PROP_RECTANGLES,tableModels);
	}
	public List<HdbTableModel> getTableModels()
	{
		return tableModels;
	}
	public void addViewModel(ViewModel rectangleModel)
	{
		viewModels.add(rectangleModel);
		fireStructureChange(PROP_RECTANGLES,viewModels);
	}

	public void removeViewModel(ViewModel rectangleModel)
	{
		viewModels.remove(rectangleModel);
		fireStructureChange(PROP_RECTANGLES,viewModels);
	}
	public List<ViewModel> getViewModels()
	{
		return viewModels;
	}

	public void addNoteModel(NoteModel noteModel)
	{
		noteModels.add(noteModel);
		fireStructureChange(PROP_RECTANGLES,noteModels);
	}

	public void removeNoteModel(NoteModel noteModel)
	{
		noteModels.remove(noteModel);
		fireStructureChange(PROP_RECTANGLES,noteModels);
	}
	public List<NoteModel> getNoteModels()
	{
		return noteModels;
	}

	public void setZoom(double zoom)
	{
		this.zoom=zoom;
	}
	public double getZoom()
	{
		return this.zoom;
	}

	public List<NodeConnection> getConnections()
	{
		connections.clear();
		for(HdbTableModel table:tableModels)
		{
		    connections.addAll(table.getIncomingConnections());
		}
		return connections;
	}

	public HdbTableModel getTableByUuid(String sourceId)
	{
		for(HdbTableModel table:this.tableModels)
		{
			if(table.getUuid().equals(sourceId))
				return table;
		}
		return null;
	}

}
