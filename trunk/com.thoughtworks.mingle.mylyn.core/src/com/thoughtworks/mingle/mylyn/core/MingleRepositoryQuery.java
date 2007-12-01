package com.thoughtworks.mingle.mylyn.core;

import java.net.MalformedURLException;

import org.eclipse.mylyn.tasks.core.AbstractRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;

/**
 * @author Ketan Padegaonkar
 */
public class MingleRepositoryQuery extends AbstractRepositoryQuery {

	private final String			projectName;
	private final TaskRepository	repository;

	public MingleRepositoryQuery(String description, String projectName, TaskRepository repository) {
		super(description);
		this.projectName = projectName;
		this.repository = repository;
		this.repositoryUrl = repository.getUrl();
		try {
			this.url = new MingleClient("", "", repositoryUrl).projectHomePage(projectName);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("invalid url", e);
		}
	}

	@Override
	public String getRepositoryKind() {
		return Activator.CONNECTOR_KIND;
	}

	public String getProjectName() {
		return this.projectName;
	}

}
