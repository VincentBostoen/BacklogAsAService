package com.perso.baas.server.dao;

import java.util.List;

import com.google.inject.Singleton;
import com.perso.baas.shared.UserStory;

@Singleton
public class UserStoryDao extends ObjectifyGenericDao<UserStory> {

	public UserStoryDao() {
		super(UserStory.class);
	}
	
	public List<UserStory> getStories(long backlogId){
		return listByProperty("backlogId", backlogId);
	}
}
