package com.thoughtworks.mingle.mylyn.ui;

import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.TasksUiPlugin;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.RepositoryTaskEditorInput;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import com.thoughtworks.mingle.mylyn.core.Activator;
import com.thoughtworks.mingle.mylyn.core.MingleTask;

public class MingleTaskEditorFactory extends AbstractTaskEditorFactory {

    public MingleTaskEditorFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canCreateEditorFor(AbstractTask task) {
        return task instanceof MingleTask;
    }

    @Override
    public boolean canCreateEditorFor(IEditorInput input) {
        if (input instanceof RepositoryTaskEditorInput) {
            RepositoryTaskEditorInput existingInput = (RepositoryTaskEditorInput) input;
            return existingInput.getTaskData() != null && Activator.CONNECTOR_KIND.equals(existingInput.getRepository().getConnectorKind());
        }
        return false;
    }

    @Override
    public IEditorPart createEditor(TaskEditor parentEditor, IEditorInput editorInput) {
        if (editorInput instanceof RepositoryTaskEditorInput) {
            RepositoryTaskEditorInput taskInput = (RepositoryTaskEditorInput) editorInput;
            if (taskInput.getTaskData().isNew()) {
                return new MingleTaskEditor(parentEditor);
            } else {
                return new MingleTaskEditor(parentEditor);
            }
        } else if (editorInput instanceof TaskEditorInput) {
            return new MingleTaskEditor(parentEditor);
        }
        return null;

    }

    @Override
    public IEditorInput createEditorInput(AbstractTask task) {
        if (task instanceof MingleTask) {
            MingleTask mingleTask = (MingleTask) task;
            final TaskRepository repository = TasksUiPlugin.getRepositoryManager().getRepository(Activator.CONNECTOR_KIND,
                                                                                                 mingleTask.getRepositoryUrl());

            try {
                return new RepositoryTaskEditorInput(repository, mingleTask.getTaskId(), mingleTask.getUrl());
            } catch (Exception e) {
                // FIXME
                // StatusHandler.fail(e, "Could not create Mingle editor input",
                // true);
            }

        }
        return null;
    }

    @Override
    public String getTitle() {
        return "Mingle";
    }

}
