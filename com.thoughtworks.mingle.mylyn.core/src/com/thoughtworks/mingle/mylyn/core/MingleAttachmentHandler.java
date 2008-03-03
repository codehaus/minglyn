package com.thoughtworks.mingle.mylyn.core;

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.AbstractAttachmentHandler;
import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.ITaskAttachment;
import org.eclipse.mylyn.tasks.core.RepositoryAttachment;
import org.eclipse.mylyn.tasks.core.TaskRepository;

public class MingleAttachmentHandler extends AbstractAttachmentHandler {

    private final MingleRepositoryConnector mingleRepositoryConnector;

    public MingleAttachmentHandler(MingleRepositoryConnector mingleRepositoryConnector) {
        this.mingleRepositoryConnector = mingleRepositoryConnector;
    }

    @Override
    public boolean canDeprecate(TaskRepository repository, RepositoryAttachment attachment) {
        return false;
    }

    @Override
    public boolean canDownloadAttachment(TaskRepository repository, AbstractTask task) {
        return false;
    }

    @Override
    public boolean canUploadAttachment(TaskRepository repository, AbstractTask task) {
        return false;
    }

    @Override
    public InputStream getAttachmentAsStream(TaskRepository repository, RepositoryAttachment attachment, IProgressMonitor monitor)
            throws CoreException {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void updateAttachment(TaskRepository repository, RepositoryAttachment attachment) throws CoreException {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void uploadAttachment(TaskRepository repository, AbstractTask task, ITaskAttachment attachment, String comment,
                                 IProgressMonitor monitor) throws CoreException {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");

    }

}
