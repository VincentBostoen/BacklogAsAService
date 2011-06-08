package com.baas.shared;

import com.baas.shared.core.Backlog;
import com.baas.shared.core.UserStory;
import com.gwtplatform.dispatch.shared.Action;

public class UpdateBacklogAction implements Action<UpdateBacklogResult> {

	private Backlog backlog;

	public UpdateBacklogAction() {
	}
	
	public UpdateBacklogAction(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	public String getServiceName() {
		return Action.DEFAULT_SERVICE_NAME + "UpdateBacklog";
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	public Backlog getBacklog() {
		return backlog;
	}
	
	@Override
	public String toString() {
		return "UpdateBacklogAction[" + "]";
	}
}
