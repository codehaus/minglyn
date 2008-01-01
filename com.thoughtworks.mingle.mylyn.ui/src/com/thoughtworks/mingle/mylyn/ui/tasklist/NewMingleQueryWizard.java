package com.thoughtworks.mingle.mylyn.ui.tasklist;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractEditQueryWizard;

import com.thoughtworks.mingle.mylyn.core.MingleRepositoryQuery;

/**
 * @author Ketan Padegaonkar
 */
public class NewMingleQueryWizard extends AbstractEditQueryWizard {

	/**
	 * @param repository
	 */
	public NewMingleQueryWizard(TaskRepository repository) {
		this(repository, new MingleRepositoryQuery("", "", repository));
	}

	public NewMingleQueryWizard(TaskRepository repository, MingleRepositoryQuery query) {
		super(repository, query);
	}

	@Override
	public void addPages() {
		page = new MingleQueryWizardPage(repository, (MingleRepositoryQuery) query);
		addPage(page);
	}

	@Override
	public boolean canFinish() {
		if (page.getNextPage() == null) {
			return page.isPageComplete();
		}
		return page.getNextPage().isPageComplete();
	}

}
