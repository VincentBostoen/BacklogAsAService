package com.perso.baas;

import com.perso.baas.shared.Backlog;
import com.perso.baas.shared.Complexity;
import com.perso.baas.shared.UserStory;

public class PojoHelper {
	
	public static UserStory userStory(){
		return userStory("Le nom");
	}
	
	public static UserStory userStory(String name){
		UserStory myUserStory = new UserStory();
		myUserStory.setName(name);
		myUserStory.setSprintNumber(1);
		myUserStory.setBusinessValue(5);
		myUserStory.setComplexity(Complexity.LARGE);
		myUserStory.setDescription("La description");
		myUserStory.setModuleName("Le module");
		myUserStory.setProjectVersion("0.1-SNAPSHOT");
		
		return myUserStory;
	}
	
	public static Backlog backlog(String projectName){
		Backlog backlog = new Backlog(projectName);
		return backlog;
	}
}
