package com.wt.studio.plugin.wizard.projects.uitl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.wt.studio.plugin.platform.util.HeaProjectModel;

public abstract class HEABaseWizard extends Wizard implements INewWizard,
		IExecutableExtension {
	
	@SuppressWarnings("unused")
	private ISelection selection;
	private HeaProjectModel heaProjectModel;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.heaProjectModel = new HeaProjectModel(selection);
	}

	public ISelection getSelection() {
		return selection;
	}

	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	public HeaProjectModel getHeaProjectModel() {
		return heaProjectModel;
	}

	public void setHeaProjectModel(HeaProjectModel heaProjectModel) {
		this.heaProjectModel = heaProjectModel;
	}
	
	public void synResours(StringBuilder path, String moduleName, String className, String key, String keyCode, String code, String stepTab) {
		try {
			File file = new File(path.toString());

			if (!file.exists()) {
				return;
			}

			final String newline = System.getProperty("line.separator");
			InputStream is;
			OutputStream os;
			String line;
			StringBuffer sb = new StringBuffer();
			try {
				is = new FileInputStream(file);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				try {
					boolean flag = true;
					while ((line = reader.readLine()) != null) {

						if (flag && StringUtils.contains(line, keyCode)) {
							line = code;
							sb.append(stepTab + line);
							flag = false;

						} else if (flag && (line.contains(key))) {
							line = code;
							sb.append(stepTab + line + newline + stepTab + key);
							flag = false;
						} else {
							sb.append(line);
						}
						sb.append(newline);
					}
					is.close();
					os = new FileOutputStream(file);
					IOUtils.write(sb.toString(), os);
					os.flush();
					os.close();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}		

}
