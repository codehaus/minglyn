package com.thoughtworks.mingle.mylyn.core;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.AbstractAttributeFactory;
import org.eclipse.mylyn.tasks.core.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.RepositoryTaskData;
import org.eclipse.mylyn.tasks.core.TaskRepository;

public class MingleTaskDataHandler extends AbstractTaskDataHandler {

    private final MingleRepositoryConnector mingleRepositoryConnector;
    private MingleAttributeFactory mingleAttributeFactory = new MingleAttributeFactory();

    public MingleTaskDataHandler(MingleRepositoryConnector mingleRepositoryConnector) {
        this.mingleRepositoryConnector = mingleRepositoryConnector;
    }

    @Override
    public AbstractAttributeFactory getAttributeFactory(String repositoryUrl, String repositoryKind, String taskKind) {
        return mingleAttributeFactory;
        // throw new RuntimeException("Not Implemented");
    }

    @Override
    public AbstractAttributeFactory getAttributeFactory(RepositoryTaskData taskData) {
        return getAttributeFactory(taskData.getRepositoryUrl(), taskData.getRepositoryKind(), taskData.getTaskKind());
    }

    @Override
    public Set<String> getSubTaskIds(RepositoryTaskData taskData) {
        return Collections.emptySet();
    }

    @Override
    public RepositoryTaskData getTaskData(TaskRepository repository, String taskId, IProgressMonitor monitor) throws CoreException {
        // TODO Auto-generated method stub
        try {
            return new MingleClient(repository).getTaskData(taskId);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean initializeTaskData(TaskRepository repository, RepositoryTaskData data, IProgressMonitor monitor) throws CoreException {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public String postTaskData(TaskRepository repository, RepositoryTaskData taskData, IProgressMonitor monitor) throws CoreException {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");

    }

}
