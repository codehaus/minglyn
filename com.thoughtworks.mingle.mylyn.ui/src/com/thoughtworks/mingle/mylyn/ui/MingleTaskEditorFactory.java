package com.thoughtworks.mingle.mylyn.ui;

import org.eclipse.mylyn.internal.web.tasks.BrowserEditorInput;
import org.eclipse.mylyn.internal.web.tasks.BrowserFormPage;
import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.TasksUiPlugin;
import org.eclipse.mylyn.tasks.ui.editors.AbstractRepositoryTaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorFactory;
import org.eclipse.mylyn.tasks.ui.editors.RepositoryTaskEditorInput;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorPart;

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
        return input instanceof BrowserEditorInput;
    }

    @Override
    public IEditorPart createEditor(TaskEditor parentEditor, IEditorInput editorInput) {
        AbstractRepositoryTaskEditor editor = null;
        if (editorInput instanceof TaskEditorInput) {
            TaskEditorInput taskInput = (TaskEditorInput) editorInput;
            return createBrowser(parentEditor, taskInput.getTask().getUrl());
        }
        return editor;
    }

    @Override
    public IEditorInput createEditorInput(AbstractTask task) {
        if (task instanceof MingleTask) {
            final TaskRepository repository = TasksUiPlugin.getRepositoryManager().getRepository(Activator.CONNECTOR_KIND,
                                                                                                 task.getRepositoryUrl());
            return new RepositoryTaskEditorInput(repository, task.getUrl(), task.getTaskId());
        } else {
            return null;
        }
        
    }

    private EditorPart createBrowser(TaskEditor parentEditor, String url) {
        return new BrowserFormPage(parentEditor, "Browser");
    }
    
    @Override
    public String getTitle() {
        return "Mingle";
    }

}
