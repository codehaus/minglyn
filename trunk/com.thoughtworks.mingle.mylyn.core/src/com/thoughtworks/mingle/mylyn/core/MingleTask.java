package com.thoughtworks.mingle.mylyn.core;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.mylyn.tasks.core.AbstractTask;

/**
 * @author Ketan Padegaonkar
 */
public class MingleTask extends AbstractTask {

	private final String		description;
	final Map<String, String>	attributes;

	public MingleTask(String repositoryUrl, String taskId, String summary, String description,
			Map<String, String> attributes) {
		super(repositoryUrl, taskId, summary);
		setUrl(repositoryUrl + MingleConstants.CARDS_BASE_URL + taskId);
		this.description = description;
		this.attributes = new TreeMap<String, String>(attributes);
	}

	@Override
	public String getConnectorKind() {
		return Activator.CONNECTOR_KIND;
	}

	@Override
	public boolean isLocal() {
		return false;
	}

	public String getDescription() {
		return this.description;
	}

	public String getAttribute(String attribute) {
		return attributes.get(attribute);
	}
	
	@Override
	public String toString() {
		return super.toString() + "," + getRepositoryUrl() + ", " + getTaskId() + ", " + getDescription() + ", " + attributes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.attributes == null) ? 0 : this.attributes.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MingleTask other = (MingleTask) obj;
		if (this.attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!this.attributes.equals(other.attributes))
			return false;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		return true;
	}

	

}
