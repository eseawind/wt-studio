package com.wt.studio.plugin.platform.update;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.configuration.IInstallConfiguration;
import org.eclipse.update.configuration.ILocalSite;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.IVerificationListener;
import org.eclipse.update.core.IVerificationResult;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.operations.IInstallFeatureOperation;
import org.eclipse.update.operations.IOperation;
import org.eclipse.update.operations.IOperationFactory;
import org.eclipse.update.operations.IOperationListener;
import org.eclipse.update.operations.OperationsManager;
import org.eclipse.update.search.IUpdateSearchCategory;
import org.eclipse.update.search.IUpdateSearchResultCollector;
import org.eclipse.update.search.UpdateSearchRequest;
import org.eclipse.update.search.UpdateSearchScope;

import com.wt.studio.plugin.platform.Activator;

public class StudioUpdateThread implements IRunnableWithProgress {
	private final static String STUDIO_SITE = "http://webosgi.hirisun.com/HEAStudioUpdateSite";

	private ArrayList<IFeature> i_featureList;
	
	private MessageConsoleStream consoleStream;

	public StudioUpdateThread() {
		super();
		i_featureList = new ArrayList<IFeature>();
		consoleStream = Activator.getConsole().newMessageStream();
	}
	
	public void run(IProgressMonitor monitor) {
		try {
			consoleStream.println("---------------HEA Studio Update-----------------");
			monitor.beginTask("获取更新站点数据", 100);
			consoleStream.println("获取更新站点数据");
			URL siteURL = new URL(STUDIO_SITE);
			
			// Search for updates to existing features
			searchUpdateSite(siteURL, monitor);
			monitor.worked(30);
			// Install the new features or updated features
			installFeatures(monitor);
			monitor.worked(70);
		} catch (MalformedURLException e) {
			consoleStream.println(e.getMessage());
			e.printStackTrace();
		} catch (OperationCanceledException e) {
			consoleStream.println(e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			consoleStream.println(e.getMessage());
			e.printStackTrace();
		} catch (CoreException e) {
			consoleStream.println(e.getMessage());
			e.printStackTrace();
		}
		monitor.done();
	}

	@SuppressWarnings("deprecation")
	private void searchUpdateSite(URL siteURL, IProgressMonitor monitor)
			throws OperationCanceledException, CoreException {
		// Search for updates to existing features
		UpdateSearchScope updateSearchScope = new UpdateSearchScope();
		updateSearchScope.addSearchSite("HEAStudioUpdateSite", siteURL, null);
		consoleStream.println(siteURL.toString());

		IUpdateSearchCategory updateSearchCatogory = UpdateSearchRequest
				.createDefaultSiteSearchCategory();

		UpdateSearchRequest updateSearchRequest = new UpdateSearchRequest(
				updateSearchCatogory, updateSearchScope);

		IUpdateSearchResultCollector updateSearchResultCollector = new IUpdateSearchResultCollector() {
			@Override
			public void accept(IFeature match) {
				i_featureList.add(match);
				consoleStream.println(match.getLabel());
			}
		};

		updateSearchRequest.performSearch(updateSearchResultCollector,
				new NullProgressMonitor());
	}

	@SuppressWarnings("deprecation")
	private void installFeatures(IProgressMonitor monitor) throws InvocationTargetException,
			CoreException {
		// Get the local site. This is the target to install to.
		ILocalSite localSite = SiteManager.getLocalSite();
		IInstallConfiguration configuration = localSite
				.getCurrentConfiguration();
		IConfiguredSite[] configuredSites = configuration.getConfiguredSites();

		IConfiguredSite targetSite = null;
		for (int i = 0; i < configuredSites.length; i++) {
			IConfiguredSite csite = configuredSites[i];
			if (csite.verifyUpdatableStatus().isOK()) {
				targetSite = csite;
				break;
			}
		}

		if (targetSite != null) {
			ISite[] sourceSites = null;

			if (i_featureList != null) {
				sourceSites = new ISite[i_featureList.size()];
				for (int i = 0; i < sourceSites.length; i++) {
					IFeature feature = i_featureList.get(i);
					sourceSites[i] = feature.getSite();
				}
			}

			if (sourceSites.length > 0) {
				IOperationFactory operationFactory = OperationsManager
						.getOperationFactory();
				ArrayList<IInstallFeatureOperation> featureInstalls = new ArrayList<IInstallFeatureOperation>();
				for (ISite sourceSite : sourceSites) {
					
					IFeatureReference[] featureReferences = sourceSite
							.getFeatureReferences();
					for (IFeatureReference featureReference : featureReferences) {
						monitor.worked(10);
						IFeature feature = featureReference
								.getFeature(new NullProgressMonitor());
						IInstallFeatureOperation installFeature = operationFactory
								.createInstallOperation(targetSite, feature,
										new IFeatureReference[0],
										new IFeature[0],
										new IVerificationListener() {

											@Override
											public int prompt(
													IVerificationResult result) {
												return CHOICE_INSTALL_TRUST_ONCE;
											}
										});

						featureInstalls.add(installFeature);
					}
				}

				IInstallFeatureOperation[] operations = featureInstalls
						.toArray(new IInstallFeatureOperation[featureInstalls
								.size()]);
				IOperation batchOperation = operationFactory.createBatchInstallOperation(operations);
				batchOperation.execute(new NullProgressMonitor(),
						new IOperationListener() {

							@Override
							public boolean beforeExecute(IOperation operation,
									Object data) {
								return true;
							}

							@Override
							public boolean afterExecute(IOperation operation,
									Object data) {
								return true;
							}
						});
			}
		}
	}

}
