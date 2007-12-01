package com.thoughtworks.mingle.mylyn.ui.tasklist;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.RepositoryTemplate;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;

import com.thoughtworks.mingle.mylyn.core.MingleClient;
import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;

/**
 * @author Ketan Padegaonkar
 * 
 */
public class MingleRepositorySettingsPage extends AbstractRepositorySettingsPage {

	/**
	 * @param repositoryUi
	 */
	public MingleRepositorySettingsPage(AbstractRepositoryConnectorUi repositoryUi) {
		super("Mingle Repository Settings", "Example: https://mingle.mycompany.com", repositoryUi);
	}

	@Override
	protected void createAdditionalControls(Composite parent) {
		for (RepositoryTemplate template : connector.getTemplates()) {
			serverUrlCombo.add(template.label);
		}
	}

	@Override
	protected Validator getValidator(TaskRepository repository) {
		return new MingleConnectionValidator(repository, getVersion());
	}

	@Override
	protected boolean isValidUrl(String name) {
		if (name.startsWith(URL_PREFIX_HTTPS) || name.startsWith(URL_PREFIX_HTTP)) {
			try {
				new URL(name);
				return true;
			} catch (MalformedURLException e) {
			}
		}
		return false;
	}

	/**
	 * @author Ketan Padegaonkar
	 */
	public class MingleConnectionValidator extends Validator {

		private String					userName;
		private String					password;
		private String					serverUrl;
		private String					httpAuthUserId;
		private String					httpAuthPassword;
		private final TaskRepository	repository;
		private final String			version;

		public MingleConnectionValidator(TaskRepository repository, String version) {
			this.repository = repository;
			this.version = version;
			userName = getUserName();
			password = getPassword();
			serverUrl = getServerUrl();
			httpAuthUserId = getHttpAuthUserId();
			httpAuthPassword = getHttpAuthPassword();
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			try {
				validateConnection(monitor);
			} catch (Exception e) {
				displayError(e);
			}
		}

		private void displayError(Exception e) {

		}

		private void validateConnection(IProgressMonitor monitor) throws CoreException {
			if (monitor == null) {
				monitor = new NullProgressMonitor();
			}
			try {
				MingleClient client = new MingleClient(userName, password, new URL(serverUrl));
				if (client.validate()) {
					setStatus(Status.OK_STATUS);
				}
				monitor.beginTask("Validating server settings", IProgressMonitor.UNKNOWN);
			} catch (MingleAuthenticationException e) {
				setStatus(new Status(Status.ERROR, "mingle", "Could not authenticate user. Check username and password."));
			} catch (IOException e) {
				setStatus(new Status(Status.ERROR, "mingle", "Could not connect to server: " + serverUrl));
			} finally {
				monitor.done();
			}
		}

	}
}
