package com.wt.studio.plugin.platform.p2;

import org.eclipse.equinox.internal.p2.ui.dialogs.UpdateSingleIUWizard;
import org.eclipse.equinox.p2.operations.RepositoryTracker;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.ui.LoadMetadataRepositoryJob;
import org.eclipse.jface.wizard.WizardDialog;

/**
 * UpdateHandler invokes the check for updates UI
 * 
 * @since 3.4
 */
public class UpdateHandler extends PreloadingRepositoryHandler {

	boolean hasNoRepos = false;

	protected void doExecute(LoadMetadataRepositoryJob job) {
		if (hasNoRepos) {
			return;
		}
		UpdateOperation operation = getProvisioningUI().getUpdateOperation(null, null);
		// check for updates
		operation.resolveModal(null);
		if (getProvisioningUI().getPolicy().continueWorkingWithOperation(operation, getShell())) {
			if (UpdateSingleIUWizard.validFor(operation)) {
				// Special case for only updating a single root
				UpdateSingleIUWizard wizard = new UpdateSingleIUWizard(getProvisioningUI(), operation);
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();
				dialog.open();
			} else {
				// Open the normal version of the update wizard
				getProvisioningUI().openUpdateWizard(false, operation, job);
			}
		}
	}

	protected boolean preloadRepositories() {
		hasNoRepos = false;
		RepositoryTracker repoMan = getProvisioningUI().getRepositoryTracker();
		if (repoMan.getKnownRepositories(getProvisioningUI().getSession()).length == 0) {
			hasNoRepos = true;
			return false;
		}
		return super.preloadRepositories();
	}
}
