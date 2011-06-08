package com.baas.shared;

import com.gwtplatform.dispatch.shared.Action;

public class GetStoryListAction implements Action<GetStoryListResult> {

	private long backlogId;

	public GetStoryListAction() {
		// Possibly for serialization.
	}

	public GetStoryListAction(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetStoryList";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public String toString() {
		return "GetStoryListAction[" + "]";
	}

	public long getBacklogId() {
		return backlogId;
	}
}
