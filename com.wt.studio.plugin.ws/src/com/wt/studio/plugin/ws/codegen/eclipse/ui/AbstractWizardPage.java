package com.wt.studio.plugin.ws.codegen.eclipse.ui;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;

import com.wt.studio.plugin.ws.Activator;
import com.wt.studio.plugin.ws.codegen.eclipse.CodeGenWizard;
import com.wt.studio.plugin.ws.codegen.eclipse.util.SettingsConstants;

public abstract class AbstractWizardPage extends WizardPage implements SettingsConstants {
    
    protected IDialogSettings settings;
    protected boolean restoredFromPreviousSettings = false;
    
    public AbstractWizardPage(String pageName){
        super(pageName+".name");
        init(pageName);
    }
    
    protected void init(String pageName){
        setTitle(Activator.getResourceString(pageName+".title"));
        setDescription(Activator.getResourceString(pageName+".desc"));
        setImageDescriptor(Activator.getWizardImageDescriptor());
        
        /*
         * Get the settings for this page. If there is no section in the
         * Plugin's settings for this OptionsPage, create a new section
         */
        IDialogSettings rootSettings = Activator.getDefault()
                .getDialogSettings();
        IDialogSettings section = rootSettings.getSection(this.getClass()
                .getName());
        if (section == null) {
            settings = rootSettings.addNewSection(this.getClass().getName());
            restoredFromPreviousSettings = false;
            initializeDefaultSettings();
        } else {
            restoredFromPreviousSettings=true;
            settings = section;
        }
    }

    protected void updateStatus(String message) {
    	setErrorMessage(message);
        setPageComplete(message == null);
    }

    protected abstract void initializeDefaultSettings(); 
   
    public abstract int getPageType() ;
    
    /**
     * a convenient method to get the wizard
     * @return
     */
    public CodeGenWizard getCodegenWizard(){
    	return (CodeGenWizard)getWizard();
    }
}
