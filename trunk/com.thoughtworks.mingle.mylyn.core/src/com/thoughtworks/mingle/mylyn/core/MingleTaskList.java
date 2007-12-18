package com.thoughtworks.mingle.mylyn.core;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.mylyn.tasks.core.AbstractTask;

/**
 * @author Ketan Padegaonkar
 *
 */
public class MingleTaskList extends ArrayList <MingleTask> {

	private static final long	serialVersionUID	= -4499557121031696743L;

	public MingleTaskList() {
		super();
	}

	public MingleTaskList(Collection<? extends MingleTask> c) {
		super(c);
	}

	public MingleTaskList(int initialCapacity) {
		super(initialCapacity);
	}

	public MingleTask getTaskWithId(String taskId) {
		for (MingleTask task : this) {
			if (task.getTaskId().equals(taskId))
				return task;
		}
		return null;
	}

}
