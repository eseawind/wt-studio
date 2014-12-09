package com.wt.studio.plugin.querydesigner.gef.editors.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.PageModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.XYBlockModel;

public class PartFactory implements EditPartFactory
{
	@Override
	public EditPart createEditPart(EditPart context, Object model)
	{
		EditPart part = null;
		if (model instanceof Diagram) {
			part = new DiagramPart();
		} else if (model instanceof VerticalBlockModel) {
			part = new VerticalBlockModelPart();
		} else if (model instanceof HorizontalBlockModel) {
			part = new HorizontalBlockModelPart();
		} else if (model instanceof TableModel) {
			part = new TableModelPart();
		} else if (model instanceof ParamModel) {
			part = new ParamModelPart();
		} else if (model instanceof QueryBlockModel) {
			part = new QueryBlockModelPart();
		} else if (model instanceof ColumnModel2) {
			part = new ColumnModelPart();
		} else if (model instanceof ChartBlockModel) {
			part = new ChartBlockModelPart();
		} else if (model instanceof GhostModel) {
			part = new GhostModelPart();
		} else if (model instanceof ColumnGroupModel) {
			part = new ColumnGroupEditPart();
		} else if (model instanceof PageModel) {
			part = new PageModelPart();
		} else if (model instanceof FrameBlockModel) {
			part = new FrameModelPart();
		} else if (model instanceof TitleModel) {
			part = new TitleModelPart();
		} else if (model instanceof HtmlAreaModel) {
			part = new HtmlAreaModelPart();
		} else if (model instanceof TextAreaModel) {
			part = new TextAreaModelPart();
		} else if (model instanceof QueryHorizontalBlockModel) {
			part = new QueryHorizontalBlockModelEditPart();
		} else if (model instanceof XYBlockModel) {
			part = new XYBlockModelPart();
		} else {
			return null;
		}
		part.setModel(model);
		return part;
	}
}