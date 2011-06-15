package com.baas.server.dao;

import java.util.List;

import com.baas.shared.core.UserStory;
import com.google.inject.Singleton;

@Singleton
public class UserStoryDao extends ObjectifyGenericDao<UserStory> {

	public UserStoryDao() {
		super(UserStory.class);
	}
	
	/**
	 * Get stories in datastore that are linked to the backlog corresponding to the backlogId
	 * @param backlogId
	 * @return
	 */
	public List<UserStory> getStories(long backlogId){
		return listByProperty("backlogId", backlogId);
	}
}
