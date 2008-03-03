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

    private final TaskRepository repository;
    private AbstractRepositoryQuery query;
    private Composite composite;
    private Text queryStringText;

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
        label.setText("&Query String:");

        queryStringText = new Text(composite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
        queryStringText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        queryStringText.setText(prefixRepositoryUrl());
    }

    private String prefixRepositoryUrl() {
        return repository.getUrl() + "/cards/list?" + ((MingleRepositoryQuery) query).getQueryString();
    }

    @Override
    public AbstractRepositoryQuery getQuery() {
        return new MingleRepositoryQuery(title.getText(), queryString(), repository);
    }

    private String queryString() {
        String url = queryStringText.getText();
        return stripQueryStringFromUrl(url);
    }

    private String stripQueryStringFromUrl(String result) {
        String repositoryUrl = repository.getUrl();
        if (result.startsWith(repositoryUrl)) {
            String baseUrl = repositoryUrl + "/cards/list?";
            result = result.substring(baseUrl.length());
        }
        return result;
    }
}
