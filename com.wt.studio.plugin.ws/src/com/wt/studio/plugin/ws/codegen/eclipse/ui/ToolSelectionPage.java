package com.wt.studio.plugin.ws.codegen.eclipse.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.wt.studio.plugin.ws.Activator;
import com.wt.studio.plugin.ws.codegen.eclipse.CodeGenWizard;

public class ToolSelectionPage extends AbstractWizardPage {
   
    private Button java2WSDLRadioButton;
    private Button wsdl2JavaRadioButton;
    public ToolSelectionPage() {
        super("page0");
       
    }
    
    /**
     * Creates a default value for the settings on this page
     */
    protected void initializeDefaultSettings() {
        settings.put(PREF_TOOL_SELECTION_JAVA2WSDL, false);
        settings.put(PREF_TOOL_SELECTION_WSDL2JAVA, true);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
public void createControl(Composite parent) {
        
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 9;

              
        Label label = new Label(container, SWT.NULL);
        label.setText(Activator.getResourceString("page0.options.desc"));
             
        wsdl2JavaRadioButton = new Button(container,SWT.RADIO);
        wsdl2JavaRadioButton.setText(Activator.getResourceString("page0.wsdl2java.caption"));
        wsdl2JavaRadioButton.setToolTipText(Activator.getResourceString("page0.wsdl2java.desc"));
        wsdl2JavaRadioButton.setSelection(settings.getBoolean(PREF_TOOL_SELECTION_WSDL2JAVA));
        wsdl2JavaRadioButton.addSelectionListener(new SelectionAdapter(){
           public void widgetSelected(SelectionEvent e)
           {
              handleCheckboxSelection();
           }
        });
        
        java2WSDLRadioButton = new Button(container,SWT.RADIO);
        java2WSDLRadioButton.setText(Activator.getResourceString("page0.java2wsdl.caption"));
        java2WSDLRadioButton.setToolTipText(Activator.getResourceString("page0.java2wsdl.desc"));
        java2WSDLRadioButton.setSelection(settings.getBoolean(PREF_TOOL_SELECTION_JAVA2WSDL));
        java2WSDLRadioButton.addSelectionListener(new SelectionAdapter(){
           public void widgetSelected(SelectionEvent e)
           {
              handleCheckboxSelection();
           }
        });
        
        Label fillLabel = new Label(container, SWT.NULL);
        fillLabel.setText(Activator.getResourceString("general.empty"));

        Label hintLabel = new Label(container, SWT.NULL);
        hintLabel.setText(Activator.getResourceString("page0.hint.desc"));

        ///////////////////////////////////////
        //java2WSDLRadioButton.setEnabled(false);
        //////////////////////////////////////
        
        handleCheckboxSelection();
        setControl(container);

    }

	private void handleCheckboxSelection(){
	    CodeGenWizard wizard = (CodeGenWizard)this.getWizard();
	    if (wsdl2JavaRadioButton.getSelection()){
	        settings.put(PREF_TOOL_SELECTION_WSDL2JAVA,true);
	        settings.put(PREF_TOOL_SELECTION_JAVA2WSDL,false);
	       wizard.setSelectedWizardType(WSDL_2_JAVA_TYPE); 
	    }else if (java2WSDLRadioButton.getSelection()){
	        settings.put(PREF_TOOL_SELECTION_WSDL2JAVA,false);
	        settings.put(PREF_TOOL_SELECTION_JAVA2WSDL,true);
	        wizard.setSelectedWizardType(JAVA_2_WSDL_TYPE); 
	    }
	}
	
	
    /* (non-Javadoc)
     * @see org.apache.axis2.tool.codegen.eclipse.ui.CodegenPage#getPageType()
     */
    public int getPageType() {
         return UNSPECIFIED_TYPE;
    }
    
        
}
