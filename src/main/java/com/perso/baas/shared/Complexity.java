package com.perso.baas.shared;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Define which amount of work a story needs to be done
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Complexity {
	NOT_RATED("NOT_RATED", "Non évalué"),
	EXTRA_SMALL("EXTRA_SMALL", "XS"),
	SMALL("SMALL", "S"),
	MEDIUM("MEDIUM","M"),
	LARGE("MEDIUM","L"),
	EXTRA_LARGE("EXTRA_LARGE","XL");
	
	private final String id;
	private final String name;
	
	private Complexity(String id, String name) {
		this.id = id;
		this.name = name;
	}

	//Getters and Setters
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
