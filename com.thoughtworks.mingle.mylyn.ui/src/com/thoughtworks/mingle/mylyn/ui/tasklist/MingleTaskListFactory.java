package com.thoughtworks.mingle.mylyn.ui.tasklist;

import org.eclipse.mylyn.tasks.core.AbstractTask;
import org.eclipse.mylyn.tasks.core.AbstractTaskListFactory;
import org.w3c.dom.Element;

public class MingleTaskListFactory extends AbstractTaskListFactory {

	public MingleTaskListFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canCreate(AbstractTask task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractTask createTask(String repositoryUrl, String taskId, String label, Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskElementName() {
		// TODO Auto-generated method stub
		return null;
	}

}
