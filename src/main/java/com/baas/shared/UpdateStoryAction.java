package com.baas.shared;

import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.shared.Action;

public class UpdateStoryAction implements Action<UpdateStoryResult> {

	private UserStory story;

	public UpdateStoryAction() {
	}
	
	public UpdateStoryAction(UserStory story) {
		this.story = story;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "UpdateStory";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	public UserStory getStory() {
		return story;
	}
	
	@Override
	public String toString() {
		return "UpdateStoryAction[" + "]";
	}
}
