package com.baas.shared;

import com.gwtplatform.dispatch.shared.Action;

public class GetBacklogAction implements Action<GetBacklogResult> {

	private long backlogId;

	public GetBacklogAction() {
		// Possibly for serialization.
	}

	public GetBacklogAction(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "GetBacklog";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public String toString() {
		return "GetBacklogAction[" + "]";
	}

	public long getBacklogId() {
		return backlogId;
	}
}
