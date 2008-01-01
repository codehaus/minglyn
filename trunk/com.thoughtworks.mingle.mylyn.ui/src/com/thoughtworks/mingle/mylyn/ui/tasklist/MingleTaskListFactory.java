package com.thoughtworks.mingle.mylyn.ui.tasklist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.mylyn.tasks.core.AbstractRepositoryQuery;
import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.AbstractTaskListFactory;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.w3c.dom.Element;

import com.thoughtworks.mingle.mylyn.core.Activator;
import com.thoughtworks.mingle.mylyn.core.MingleRepositoryQuery;
import com.thoughtworks.mingle.mylyn.core.MingleTask;

public class MingleTaskListFactory extends AbstractTaskListFactory {

    private static final String KEY_MINGLE = "Mingle";

    private static final String KEY_MINGLE_TASK = KEY_MINGLE + AbstractTaskListFactory.KEY_TASK;

    private static final String KEY_MINGLE_QUERY = KEY_MINGLE + AbstractTaskListFactory.KEY_QUERY;

    public String getTaskElementName() {
        return KEY_MINGLE_TASK;
    }

    public Set<String> getQueryElementNames() {
        Set<String> names = new HashSet<String>();
        names.add(KEY_MINGLE_QUERY);
        return names;
    }

    public boolean canCreate(AbstractTask task) {
        return task instanceof MingleTask;
    }

    public boolean canCreate(AbstractRepositoryQuery category) {
        return category instanceof MingleRepositoryQuery;
    }

    public String getQueryElementName(AbstractRepositoryQuery query) {
        return query instanceof MingleRepositoryQuery ? KEY_MINGLE_QUERY : "";
    }

    public AbstractTask createTask(String repositoryUrl, String taskId, String summary, Element element) {
        return new MingleTask(repositoryUrl, taskId, summary, "", new HashMap<String, String>());
    }

    public AbstractRepositoryQuery createQuery(String repositoryUrl, String queryString, String label, Element element) {
        return new MingleRepositoryQuery(label, "", new TaskRepository(Activator.CONNECTOR_KIND, repositoryUrl));
    }
}
