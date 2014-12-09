package com.wt.studio.plugin.pagedesigner.gef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ButtonModel;
import com.wt.studio.plugin.pagedesigner.gef.model.CheckBoxModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.DateModel;
import com.wt.studio.plugin.pagedesigner.gef.model.EditorModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.InputModel;
import com.wt.studio.plugin.pagedesigner.gef.model.LabelModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.PictureModel;
import com.wt.studio.plugin.pagedesigner.gef.model.RadioBoxModel;
import com.wt.studio.plugin.pagedesigner.gef.model.SelectModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.XYBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.tool.CommonEclipseUtil;


public class PaletteFactory
{
	private static PaletteContainer createControlGroup(PaletteRoot root)
	{
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry selectionTool = new SelectionToolEntry();
		tools.add(selectionTool);
		root.setDefaultEntry(selectionTool);
		// ToolEntry connectionCreationTool = new ConnectionCreationToolEntry("Connection",
		// "Create a connection", null, null, null);
		// tools.add(connectionCreationTool);
		controlGroup.addAll(tools);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer()
	{
		PaletteDrawer drawer = new PaletteDrawer("布局面板", null);
		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("水平布局", "创建一个水平布局",
				HorizonBlockModel.class, new SimpleFactory(HorizonBlockModel.class),
				Activator.getImageDescriptor("icons/block.png"),
				Activator.getImageDescriptor("icons/block.png"));
		tools.add(tool);
		        tool = new CombinedTemplateCreationEntry("垂直布局", "创建一个垂直布局",
				VerticalBlockModel.class, new SimpleFactory(VerticalBlockModel.class),
				Activator.getImageDescriptor("icons/block.png"),
				Activator.getImageDescriptor("icons/block.png"));
		tools.add(tool);
		 tool = new CombinedTemplateCreationEntry("自由布局", "创建一个自由布局",
					XYBlockModel.class, new SimpleFactory(XYBlockModel.class),
					Activator.getImageDescriptor("icons/block.png"),
					Activator.getImageDescriptor("icons/block.png"));
			tools.add(tool);
		drawer.addAll(tools);
		return drawer;
	}

	private static PaletteContainer createChildrenParamDrawer()
	{

		PaletteDrawer drawer = new PaletteDrawer("控件面板", null);
		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("Panel", "创建一个panel模块",
				FormModel.class, new SimpleFactory(FormModel.class),
				Activator.getImageDescriptor("icons/block.png"),
				Activator.getImageDescriptor("icons/block.png"));
	    tools.add(tool);
	    
	    
		tool = new CombinedTemplateCreationEntry("输入框", "创建一个input控件",
				InputModel.class, new SimpleFactory(InputModel.class),
				Activator.getImageDescriptor("icons/input.png"),
				Activator.getImageDescriptor("icons/input.png"));
		tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("按钮", "创建一个按钮控件",
				ButtonModel.class, new SimpleFactory(ButtonModel.class),
				Activator.getImageDescriptor("icons/button.png"),
				Activator.getImageDescriptor("icons/button.png"));
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("复选框", "创建一个复选框控件",
					CheckBoxModel.class, new SimpleFactory(CheckBoxModel.class),
					Activator.getImageDescriptor("icons/check.png"),
					Activator.getImageDescriptor("icons/check.png"));
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("单选框", "创建一个单选框控件",
				RadioBoxModel.class, new SimpleFactory(RadioBoxModel.class),
				Activator.getImageDescriptor("icons/radio.png"),
				Activator.getImageDescriptor("icons/radio.png"));
	    tools.add(tool);
		tool = new CombinedTemplateCreationEntry("文本框", "创建一个文本框控件",
				TextAreaModel.class, new SimpleFactory(TextAreaModel.class),
				Activator.getImageDescriptor("icons/text.gif"),
				Activator.getImageDescriptor("icons/text.gif"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("标题", "创建一个标题控件",
				LabelModel.class, new SimpleFactory(LabelModel.class),
				Activator.getImageDescriptor("icons/label.gif"),
				Activator.getImageDescriptor("icons/label.gif"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("日期控件", "创建一个日期控件",
				DateModel.class, new SimpleFactory(DateModel.class),
				Activator.getImageDescriptor("icons/date.png"),
				Activator.getImageDescriptor("icons/date.png"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("列表控件", "创建一个列表控件",
				TableControlModel.class, new SimpleFactory(TableControlModel.class),
				Activator.getImageDescriptor("icons/table.gif"),
				Activator.getImageDescriptor("icons/table.gif"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("下拉菜单", "创建一个下拉菜单",
				SelectModel.class, new SimpleFactory(SelectModel.class),
				Activator.getImageDescriptor("icons/select.png"),
				Activator.getImageDescriptor("icons/select.png"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("图片", "创建一个图片",
				PictureModel.class, new SimpleFactory(PictureModel.class),
				Activator.getImageDescriptor("icons/picon.png"),
				Activator.getImageDescriptor("icons/picon.png"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("文本编辑器", "创建一个文本编辑器",
				EditorModel.class, new SimpleFactory(EditorModel.class),
				Activator.getImageDescriptor("icons/editor.jpg"),
				Activator.getImageDescriptor("icons/editor.jpg"));
	    tools.add(tool);
	    tool = new CombinedTemplateCreationEntry("列", "创建一个列",
				ColumnModel.class, new SimpleFactory(ColumnModel.class),
				Activator.getImageDescriptor("icons/column.png"),
				Activator.getImageDescriptor("icons/column.png"));
	    tools.add(tool);
		drawer.addAll(tools);
		return drawer;
	}
	private static List<PaletteEntry> createCategories(PaletteRoot root)
	{
		List<PaletteEntry> categories = new ArrayList<PaletteEntry>();

		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());
		categories.add(createChildrenParamDrawer());
		//categories.add(createChildrenChartDrawer());
		return categories;
	}

	public static PaletteRoot createPalette()
	{
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}

	public static PaletteRoot createFunctionPalette()
	{
		PaletteRoot root = new PaletteRoot();
		PaletteGroup controlGroup = new PaletteGroup("Control Group");
		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry selectionTool = new SelectionToolEntry();
		tools.add(selectionTool);
		root.setDefaultEntry(selectionTool);
		controlGroup.addAll(tools);
		root.add(controlGroup);
		
		PaletteDrawer drawer = new PaletteDrawer("模型面板", null);
		List<PaletteEntry> tools1 = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("VO模型", "创建一个VO模型",
				VOFunctionTableModel.class, new SimpleFactory(VOFunctionTableModel.class),
				Activator.getImageDescriptor("icons/block.png"),
				Activator.getImageDescriptor("icons/block.png"));
		tools1.add(tool);
		        tool = new CombinedTemplateCreationEntry("BO模型", "创建一个BO模型",
		        		VOFunctionTableModel.class, new SimpleFactory(BOFunctionTableModel.class),
				Activator.getImageDescriptor("icons/block.png"),
				Activator.getImageDescriptor("icons/block.png"));
		tools1.add(tool);
		tool = new CombinedTemplateCreationEntry("MO模型", "创建一个MO模型",
				    VOFunctionTableModel.class, new SimpleFactory(MOFunctionTableModel.class),
					Activator.getImageDescriptor("icons/block.png"),
					Activator.getImageDescriptor("icons/block.png"));
		tools1.add(tool);
		tool=new ConnectionCreationToolEntry("Connection",
				"Create a connection",
				new SimpleFactory(ColumnConnection.class), null, null);
		tools1.add(tool);
		tool=new ConnectionCreationToolEntry("1:1Relation",
				"Create a 1:1 relation",
				new SimpleFactory(TableotoConnection.class), 
				Activator.getImageDescriptor("icons/relation_1.gif"), 
				Activator.getImageDescriptor("icons/relation_1.gif"));
		tools1.add(tool);
		tool=new ConnectionCreationToolEntry("n:1Relation",
				"Create a n:1 relation",
				new SimpleFactory(TableotnConnection.class), 
				Activator.getImageDescriptor("icons/relation_n.gif"), 
				Activator.getImageDescriptor("icons/relation_n.gif"));
		tools1.add(tool);
		drawer.addAll(tools1);
		root.add(drawer);
		return root;
	}
}
