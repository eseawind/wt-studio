package com.wt.studio.plugin.querydesigner.gef.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamDateModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamDeptTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamHiddenModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamInputModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamUserTreeModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.XYBlockModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;

public class PaletteFactory
{
	private static PaletteContainer createControlGroup(PaletteRoot root)
	{
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry selectionTool = new SelectionToolEntry();
		tools.add(selectionTool);
		root.setDefaultEntry(selectionTool);
		controlGroup.addAll(tools);
		return controlGroup;
	}

	private static PaletteContainer createComponentsDrawer()
	{
		PaletteDrawer drawer = new PaletteDrawer("块类型", null);
		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("查询", "创建一个新的查询块",
				QueryBlockModel.class, new SimpleFactory(QueryBlockModel.class),
				CommonEclipseUtil.getImage("icons/search.png"),
				CommonEclipseUtil.getImage("icons/search.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("表格", "创建一个新的table", TableModel.class,
				new SimpleFactory(TableModel.class), CommonEclipseUtil.getImage("icons/table.gif"),
				CommonEclipseUtil.getImage("icons/table.gif"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("图表", "创建一个新的图表", ChartBlockModel.class,
				new SimpleFactory(ChartBlockModel.class),
				CommonEclipseUtil.getImage("icons/zhuxing.png"),
				CommonEclipseUtil.getImage("icons/zhuxing.png"));
		tools.add(tool);
		drawer.addAll(tools);
		return drawer;
	}

	private static PaletteContainer createChildrenParamDrawer()
	{

		PaletteDrawer drawer = new PaletteDrawer("常用组件", null);

		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("INPUT", "Create a new param",
				ParamInputModel.class, new SimpleFactory(ParamInputModel.class),
				CommonEclipseUtil.getImage("icons/param/input.png"),
				CommonEclipseUtil.getImage("icons/param/input.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("HIDDEN", "Create a new param",
				ParamHiddenModel.class, new SimpleFactory(ParamHiddenModel.class),
				CommonEclipseUtil.getImage("icons/param/hidden.png"),
				CommonEclipseUtil.getImage("icons/param/hidden.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("DATE", "Create a new param",
				ParamDateModel.class, new SimpleFactory(ParamDateModel.class),
				CommonEclipseUtil.getImage("icons/param/date.png"),
				CommonEclipseUtil.getImage("icons/param/date.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("SELECT", "Create a new param",
				ParamTreeModel.class, new SimpleFactory(ParamTreeModel.class),
				CommonEclipseUtil.getImage("icons/param/select.png"),
				CommonEclipseUtil.getImage("icons/param/select.png"));
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("TREE", "Create a new param",
				ParamTreeModel.class, new SimpleFactory(ParamTreeModel.class),
				CommonEclipseUtil.getImage("icons/param/tree.png"),
				CommonEclipseUtil.getImage("icons/param/tree.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("用户", "Create a new param",
				ParamUserTreeModel.class, new SimpleFactory(ParamUserTreeModel.class),
				CommonEclipseUtil.getImage("icons/param/user.png"),
				CommonEclipseUtil.getImage("icons/param/user.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("部门", "Create a new param",
				ParamDeptTreeModel.class, new SimpleFactory(ParamDeptTreeModel.class),
				CommonEclipseUtil.getImage("icons/param/dept.png"),
				CommonEclipseUtil.getImage("icons/param/dept.png"));
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("列组", "创建一个新的列组", ColumnGroupModel.class,
				new SimpleFactory(ColumnGroupModel.class),
				CommonEclipseUtil.getImage("icons/column.png"),
				CommonEclipseUtil.getImage("icons/column.png"));
		tools.add(tool);
		drawer.addAll(tools);
		return drawer;
	}

	private static PaletteContainer createInnerDrawer()
	{
		PaletteDrawer drawer = new PaletteDrawer("内嵌模块", null);
		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("IFrame", "创建一个新的框架",
				FrameBlockModel.class, new SimpleFactory(FrameBlockModel.class), null, null);
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("标题栏目", "创建一个新的标题", TitleModel.class,
				new SimpleFactory(TitleModel.class), null, null);
		tools.add(tool);

		tool = new CombinedTemplateCreationEntry("文字段落", "创建一个新的段落", TextAreaModel.class,
				new SimpleFactory(TextAreaModel.class), null, null);
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("HTML模块", "创建一个新的HTML模块", HtmlAreaModel.class,
				new SimpleFactory(HtmlAreaModel.class), null, null);
		tools.add(tool);
		drawer.addAll(tools);
		return drawer;
	}

	private static PaletteContainer createComposite()
	{
		PaletteDrawer drawer = new PaletteDrawer("布局", null);

		List<PaletteEntry> tools = new ArrayList<PaletteEntry>();
		ToolEntry tool = new CombinedTemplateCreationEntry("垂直容器", "创建一个新的垂直容器",
				VerticalBlockModel.class, new SimpleFactory(VerticalBlockModel.class),
				CommonEclipseUtil.getImage("icons/block.png"),
				CommonEclipseUtil.getImage("icons/block.png"));
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("水平容器", "创建一个新的水平容器", HorizontalBlockModel.class,
				new SimpleFactory(HorizontalBlockModel.class),
				CommonEclipseUtil.getImage("icons/block.png"),
				CommonEclipseUtil.getImage("icons/block.png"));
		tools.add(tool);
		tool = new CombinedTemplateCreationEntry("混合容器", "创建一个新的混合容器", XYBlockModel.class,
				new SimpleFactory(XYBlockModel.class),
				CommonEclipseUtil.getImage("icons/block.png"),
				CommonEclipseUtil.getImage("icons/block.png"));
		tools.add(tool);
		drawer.addAll(tools);
		return drawer;

	}

	private static List<PaletteEntry> createCategories(PaletteRoot root)
	{
		List<PaletteEntry> categories = new ArrayList<PaletteEntry>();

		categories.add(createControlGroup(root));
		categories.add(createComposite());
		categories.add(createComponentsDrawer());
		categories.add(createChildrenParamDrawer());
		categories.add(createInnerDrawer());
		return categories;
	}

	public static PaletteRoot createPalette()
	{
		PaletteRoot paletteRoot = new PaletteRoot();
		paletteRoot.addAll(createCategories(paletteRoot));
		return paletteRoot;
	}
}
