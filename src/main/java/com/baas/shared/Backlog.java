package com.baas.shared;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.flipthebird.gwthashcodeequals.EqualsBuilder;
import com.flipthebird.gwthashcodeequals.HashCodeBuilder;

/**
 * Backlog is the place where user stories will be pushed
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Backlog implements BaasPojo<Long> {

	/** Technical identifier */
	@Id
	private Long id;
	/** Project name to which the backlog is linked to */
	private String projectName;

	public Backlog() {
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Backlog rhs = (Backlog) obj;
		return new EqualsBuilder().append(projectName, rhs.projectName).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(projectName).toHashCode();
	}

	public Backlog(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	@Override
	public Long getKey() {
		return id;
	}

	@Override
	public void setKey(Long key) {
		this.id = key;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
