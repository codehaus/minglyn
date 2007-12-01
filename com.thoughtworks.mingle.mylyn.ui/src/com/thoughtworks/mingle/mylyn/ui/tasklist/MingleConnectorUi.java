package com.thoughtworks.mingle.mylyn.ui.tasklist;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;

import com.thoughtworks.mingle.mylyn.core.Activator;
import com.thoughtworks.mingle.mylyn.core.MingleRepositoryQuery;

/**
 * @author Ketan Padegaonkar
 * 
 */
public class MingleConnectorUi extends AbstractRepositoryConnectorUi {

	@Override
	public String getConnectorKind() {
		return Activator.CONNECTOR_KIND;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository taskRepository) {
		return null;
	}

	@Override
	public IWizard getQueryWizard(TaskRepository repository, AbstractRepositoryQuery queryToEdit) {
		if (queryToEdit instanceof MingleRepositoryQuery)
			return new NewMingleQueryWizard(repository, (MingleRepositoryQuery)queryToEdit); // FIXME: implement a NewMingleQueryWizard
		return new NewMingleQueryWizard(repository);

	}

	@Override
	public AbstractRepositorySettingsPage getSettingsPage() {
		return new MingleRepositorySettingsPage(this);
	}

	@Override
	public boolean hasSearchPage() {
		// TODO Auto-generated method stub
		return false;
	}

}
