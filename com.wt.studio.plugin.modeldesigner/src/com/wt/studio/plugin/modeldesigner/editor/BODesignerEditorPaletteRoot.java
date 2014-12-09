package com.wt.studio.plugin.modeldesigner.editor;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;
import com.wt.studio.plugin.modeldesigner.editor.tool.MovablePanningSelectionTool;
import com.wt.studio.plugin.modeldesigner.utils.ConstantResource;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class BODesignerEditorPaletteRoot extends PaletteRoot {

	public BODesignerEditorPaletteRoot() {
		PaletteGroup group = new PaletteGroup("");

		//增加Select 图标
		PanningSelectionToolEntry selectionToolEntry = new PanningSelectionToolEntry(
				ConstantResource.LABEL_SELECT);
		selectionToolEntry.setToolClass(MovablePanningSelectionTool.class);
		selectionToolEntry.setLargeIcon(Activator.getImageDescriptor("icons/arrow16.gif"));
		selectionToolEntry.setSmallIcon(Activator.getImageDescriptor("icons/arrow16.gif"));
		group.add(selectionToolEntry);
		group.add(new CreationToolEntry("Note", "Note", 
				new SimpleFactory(NoteModel.class), 
				null, 
				null));
		
		group.add(new PaletteSeparator());
		//增加Table 图标
		group.add(new CreationToolEntry(
				ConstantResource.LABEL_TABLE, 
				ConstantResource.LABEL_CREATE_TABLE, 
				new SimpleFactory(HdbTableModel.class), 
				Activator.getImageDescriptor("icons/model_new.gif"), 
				Activator.getImageDescriptor("icons/model_new.gif")));
		group.add(new CreationToolEntry(
				ConstantResource.LABEL_VIEW, 
				ConstantResource.LABEL_CREATE_VIEW, 
				new SimpleFactory(ViewModel.class), 
				Activator.getImageDescriptor("icons/view.gif"), 
				Activator.getImageDescriptor("icons/view.gif")));
		group.add(new ConnectionCreationToolEntry("Connection",
				"Create a connection",
				null, null, null));
		
		group.add(new PaletteSeparator());
		
		//添加关系
		/*ConnectionCreationToolEntry toolEntry1N = new ConnectionCreationToolEntry(
				ConstantResource.label_relation_one_to_many,
				ConstantResource.label_create_relation_one_to_many,
				new SimpleFactory(null), Activator
						.getImageDescriptor(ImageResource.RELATION_1_N), Activator
						.getImageDescriptor(ImageResource.RELATION_1_N));
		toolEntry1N.setToolClass(null);		
		group.add(toolEntry1N);		
		
		ConnectionCreationToolEntry toolEntryNN = new ConnectionCreationToolEntry(
				ConstantResource.label_relation_many_to_many,
				ConstantResource.label_create_relation_many_to_many,
				new SimpleFactory(null), Activator
						.getImageDescriptor(ImageResource.RELATION_N_N), Activator
						.getImageDescriptor(ImageResource.RELATION_N_N));
		toolEntryNN.setToolClass(null);		
		group.add(toolEntryNN);		
		
		
		ConnectionCreationToolEntry toolEntrySelf = new ConnectionCreationToolEntry(
				ConstantResource.label_relation_self,
				ConstantResource.label_create_relation_self,
				new SimpleFactory(null), Activator
						.getImageDescriptor(ImageResource.RELATION_SELF), Activator
						.getImageDescriptor(ImageResource.RELATION_SELF));
		toolEntrySelf.setToolClass(null);		
		group.add(toolEntrySelf);
		*/		
		this.add(group);
		this.setDefaultEntry(selectionToolEntry);		
		
	}

}
