package com.baas.shared;

import java.util.List;
import java.util.Set;

import com.baas.shared.core.UserStory;
import com.googlecode.objectify.Key;
import com.gwtplatform.dispatch.shared.Action;

public class DeleteUserStoriesAction implements Action<DeleteUserStoriesResult> {

	private Set<Long> userStoriesKeys;

	public DeleteUserStoriesAction() {
	}
	
	public DeleteUserStoriesAction(Set<Long> userStoriesKeys) {
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
	
	public Set<Long> getUserStoriesKeys() {
		return userStoriesKeys;
	}
}
