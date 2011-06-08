package com.baas.shared;

import com.gwtplatform.dispatch.shared.Action;

public class GetStoryAction implements Action<GetStoryResult> {

	private long storyId;

	public GetStoryAction() {
		// Possibly for serialization.
	}

	public GetStoryAction(long storyId) {
		this.storyId = storyId;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetStory";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public String toString() {
		return "GetStoryAction[" + "]";
	}

	public long getStoryId() {
		return storyId;
	}
}
