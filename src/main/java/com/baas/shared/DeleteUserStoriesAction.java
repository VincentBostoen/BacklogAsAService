package com.baas.shared;

import java.util.List;

import com.baas.shared.core.UserStory;
import com.googlecode.objectify.Key;
import com.gwtplatform.dispatch.shared.Action;

public class DeleteUserStoriesAction implements Action<DeleteUserStoriesResult> {

	private List<Key<UserStory>> userStoriesKeys;

	public DeleteUserStoriesAction() {
	}
	
	public DeleteUserStoriesAction(List<Key<UserStory>> userStoriesKeys) {
		this.userStoriesKeys = userStoriesKeys;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "DeleteUserStories";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public String toString() {
		return "DeleteUserStoriesAction[" + "]";
	}
	
	public List<Key<UserStory>> getUserStoriesKeys() {
		return userStoriesKeys;
	}
}
