package com.wt.studio.plugin.platform.p2;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.*;
import org.eclipse.equinox.p2.core.IAgentLocation;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.ProfileScope;
import org.eclipse.equinox.p2.ui.Policy;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.ServiceReference;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.wt.studio.plugin.platform.Activator;

/**
 * @since 3.6
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		Preferences node = new DefaultScope().getNode(Activator.PLUGIN_ID); //$NON-NLS-1$
		// default values
		node.putBoolean(PreferenceConstants.REPOSITORIES_VISIBLE, false);
		node.putBoolean(PreferenceConstants.SHOW_LATEST_VERSION_ONLY, true);
		node.putBoolean(PreferenceConstants.AVAILABLE_SHOW_ALL_BUNDLES, false);
		node.putBoolean(PreferenceConstants.INSTALLED_SHOW_ALL_BUNDLES, false);
		node.putBoolean(PreferenceConstants.AVAILABLE_GROUP_BY_CATEGORY, true);
		node.putBoolean(PreferenceConstants.SHOW_DRILLDOWN_REQUIREMENTS, false);
		node.putInt(PreferenceConstants.RESTART_POLICY, Policy.RESTART_POLICY_PROMPT_RESTART_OR_APPLY);
	}
}
