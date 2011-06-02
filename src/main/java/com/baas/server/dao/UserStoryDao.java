package com.baas.server.dao;

import java.util.List;

import com.baas.shared.UserStory;
import com.google.inject.Singleton;

@Singleton
public class UserStoryDao extends ObjectifyGenericDao<UserStory> {

	public UserStoryDao() {
		super(UserStory.class);
	}
	
	public List<UserStory> getStories(long backlogId){
		return listByProperty("backlogId", backlogId);
	}
}
