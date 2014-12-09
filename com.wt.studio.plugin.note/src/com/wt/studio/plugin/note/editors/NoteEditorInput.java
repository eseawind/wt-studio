package com.wt.studio.plugin.note.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.wt.studio.plugin.note.Activator;

public class NoteEditorInput implements IEditorInput {
	private String title;
	private String cid;

	public NoteEditorInput(String title,String cid) {
		super();
		this.cid=cid;
		this.title=title;
	}

	/**
	 * 返回输入相关的类
	 */
	@Override
	public Object getAdapter(Class arg0) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor("icons/note.png");
	}

	@Override
	public String getName() {
		return title;
	}
    public String getCid(){
    	return cid;
    }
	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return title;
	}

}
