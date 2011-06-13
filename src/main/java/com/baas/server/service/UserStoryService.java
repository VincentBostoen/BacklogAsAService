package com.baas.server.service;

import java.util.List;

import com.baas.server.dao.UserStoryDao;
import com.baas.shared.core.UserStory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;

public class UserStoryService {
	private UserStoryDao userStoryDao;

	@Inject
	public UserStoryService(UserStoryDao userStoryDao) {
		this.userStoryDao = userStoryDao;
	}

	public UserStory retrieve(long backlogId) throws EntityNotFoundException {
		return userStoryDao.get(backlogId);
	}

	public List<UserStory> list(long backlogId) {
		return userStoryDao.list();
	}

	public UserStory put(UserStory userStory) {
		Key<UserStory> userStoryKey = userStoryDao.put(userStory);
		userStory.setKey(userStoryKey.getId());
		return userStory;
	}
	
	public void delete(UserStory userStory) {
		userStoryDao.delete(userStory);
	}
	
	public void setUserStoryDao(UserStoryDao backlogDao) {
		this.userStoryDao = backlogDao;
	}
}
