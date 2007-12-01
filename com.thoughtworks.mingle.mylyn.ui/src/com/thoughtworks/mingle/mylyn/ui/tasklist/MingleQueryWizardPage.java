package com.thoughtworks.mingle.mylyn.ui.tasklist;

import org.eclipse.mylyn.tasks.core.AbstractRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.search.AbstractRepositoryQueryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.thoughtworks.mingle.mylyn.core.MingleRepositoryQuery;

/**
 * @author Ketan Padegaonkar
 */
public class MingleQueryWizardPage extends AbstractRepositoryQueryPage {

	private final TaskRepository	repository;
	private AbstractRepositoryQuery	query;
	private Composite				composite;
	private Text					projectNameText;

	public MingleQueryWizardPage(TaskRepository repository, MingleRepositoryQuery query) {
		super("Mingle Query", query.getSummary());
		this.repository = repository;
		this.query = query;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		super.createControl(composite);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		label.setText("&Project Name:");

		projectNameText = new Text(composite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		projectNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		projectNameText.setText(((MingleRepositoryQuery) query).getProjectName());
	}

	@Override
	public AbstractRepositoryQuery getQuery() {
		return new MingleRepositoryQuery(title.getText(), projectNameText.getText(), repository);
	}

	private String projectName() {
		return projectNameText.getText();
	}
}