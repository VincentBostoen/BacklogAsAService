package com.baas.shared.core;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.flipthebird.gwthashcodeequals.EqualsBuilder;
import com.flipthebird.gwthashcodeequals.HashCodeBuilder;
import com.google.gwt.view.client.ProvidesKey;

/**
 * A story is the the description a of user use case
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserStory implements BaasPojo<Long> {

	  /**
     * The key provider that provides the unique ID of a contact.
     */
    public static final ProvidesKey<UserStory> KEY_PROVIDER = new ProvidesKey<UserStory>() {
      public Object getKey(UserStory item) {
        return item == null ? null : item.getId();
      }
    };
    
	/** Technical identifier */
	@Id
	private Long id;
	/** Story label*/
	private String name;
	/** Sprint in which the story has been or will be done */
	private Integer sprintNumber;
	/** Project version in which the story is available */
	private String projectVersion;
	/** Module of the application which is concerned by the story */
	private String moduleName;
	/** Description or link to the description */
	private String description;
	/** Define whether the story is difficult or not*/
	private Complexity complexity;
	/** Business value */
	private Integer businessValue;
	/** Backlog to which is assigned the the story */
	private long backlogId;
	
	public UserStory() {
		complexity = Complexity.NOT_RATED;
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
		UserStory rhs = (UserStory) obj;
		return new EqualsBuilder().append(name, rhs.name).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(23, 35).append(name).append(name).toHashCode();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSprintNumber() {
		return sprintNumber;
	}
	
	public void setSprintNumber(Integer sprintNumber) {
		this.sprintNumber = sprintNumber;
	}
	
	public String getProjectVersion() {
		return projectVersion;
	}
	
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Complexity getComplexity() {
		return complexity;
	}
	
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	public Integer getBusinessValue() {
		return businessValue;
	}
	
	public void setBusinessValue(Integer businessValue) {
		this.businessValue = businessValue;
	}
	
	
	public long getBacklogId() {
		return backlogId;
	}

	public void setBacklogId(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	public String toString() {
		return "UserStory [name=" + name + ", sprintNumber=" + sprintNumber
				+ ", projectVersion=" + projectVersion + ", moduleName="
				+ moduleName + ", description=" + description + ", complexity="
				+ complexity + ", businessValue=" + businessValue + "]";
	}

	@Override
	public Long getKey() {
		return id;
	}

	@Override
	public void setKey(Long key) {
		this.id = key;
	}
}
