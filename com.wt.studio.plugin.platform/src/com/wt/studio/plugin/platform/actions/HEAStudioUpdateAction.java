package com.wt.studio.plugin.platform.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.wt.studio.plugin.platform.p2.UpdateHandler;

/**
 * 更新插件信息
 * <action class="com.hirisun.ide.plugin.platform.actions.HEAStudioUpdateAction"
 * id="com.hirisun.ide.plugin.platform.actions.HEAStudioUpdateAction"
 * icon="icons/update.ico" menubarPath="heaStudioToolBar/PlatTools"
 * label="Update HEA Studio"> </action>
 * 
 * @author gl
 * 
 */
public class HEAStudioUpdateAction implements IObjectActionDelegate,
		IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window = null;

	@Override
	public void run(IAction arg0) {
		UpdateHandler e = new UpdateHandler();
		e.execute(null);
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		window = arg0;
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {

	}

}
