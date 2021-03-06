package com.baas.shared;

import javax.persistence.Id;

import com.baas.shared.core.BaasPojo;

public class DummyPojoLong implements BaasPojo<Long>{
	
	@Id
	private Long key;
	private String name;
	private String description;
	
	public DummyPojoLong() {
	}
	
	
	public DummyPojoLong(String name) {
		this(null, name, null);
	}

	public DummyPojoLong(Long key, String name, String description) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DummyPojoLong other = (DummyPojoLong) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public Long getKey() {
		return key;
	}
	
	@Override
	public void setKey(Long key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
