package com.thoughtworks.mingle.mylyn.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.AbstractAttachmentHandler;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryQuery;
import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.ITaskCollector;
import org.eclipse.mylyn.tasks.core.RepositoryTaskData;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.thoughtworks.mingle.mylyn.core.exceptions.CouldNotParseTasksException;
import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;

/**
 * @author Ketan Padegaonkar
 * 
 */
public class MingleRepositoryConnector extends AbstractRepositoryConnector {

	@Override
	public boolean canCreateNewTask(TaskRepository repository) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canCreateTaskFromKey(TaskRepository repository) {
		//VERIFY
		return true;
	}

	@Override
	public AbstractTask createTask(String repositoryUrl, String id, String summary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractAttachmentHandler getAttachmentHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConnectorKind() {
		return Activator.CONNECTOR_KIND;
	}

	@Override
	public String getLabel() {
		return "Mingle Connector (supports mingle >= v1.1)";
	}

	@Override
	public String getRepositoryUrlFromTaskUrl(String taskFullUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractTaskDataHandler getTaskDataHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskIdFromTaskUrl(String taskFullUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskUrl(String repositoryUrl, String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean markStaleTasks(TaskRepository repository, Set<AbstractTask> tasks, IProgressMonitor monitor)
			throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IStatus performQuery(AbstractRepositoryQuery query, TaskRepository repository, IProgressMonitor monitor,
			ITaskCollector resultCollector) {

		String projectName = ((MingleRepositoryQuery) query).getProjectName();

		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		try {
			MingleClient client = new MingleClient(repository.getUserName(), repository.getPassword(), new URL(repository.getUrl()));
			monitor.beginTask("Running mingle query", IProgressMonitor.UNKNOWN);
			client.validate();
			
			MingleTaskList tasks = client.getAllTasks(projectName);
			for (AbstractTask abstractTask : tasks) {
				resultCollector.accept(abstractTask);
			}

		} catch (MingleAuthenticationException e) {
			return new Status(Status.ERROR, "mingle", e.getMessage(), e);
		} catch (MalformedURLException e) {
			return new Status(Status.ERROR, "mingle", "Not a valid URL: " + repository.getUrl(), e);
		} catch (IOException e) {
			return new Status(Status.ERROR, "mingle", "There was an error reading from the connection.", e);
		} catch (CouldNotParseTasksException e) {
			return new Status(Status.ERROR, "mingle", "The server returned a response that I could not understand.", e);
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	@Override
	public void updateAttributes(TaskRepository repository, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTaskFromRepository(TaskRepository repository, AbstractTask repositoryTask,
			IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTaskFromTaskData(TaskRepository repository, AbstractTask repositoryTask,
			RepositoryTaskData taskData) {
		// TODO Auto-generated method stub

	}

}
