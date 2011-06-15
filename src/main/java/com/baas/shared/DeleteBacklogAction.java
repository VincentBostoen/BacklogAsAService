package com.baas.shared;

import com.gwtplatform.dispatch.shared.Action;

public class DeleteBacklogAction implements Action<DeleteBacklogResult> {

	private long backlogId;

	public DeleteBacklogAction() {
	}
	
	public DeleteBacklogAction(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "DeleteBacklog";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	public long getBacklogId() {
		return backlogId;
	}
	
	@Override
	public String toString() {
		return "DeleteBacklogAction[" + "]";
	}
}
