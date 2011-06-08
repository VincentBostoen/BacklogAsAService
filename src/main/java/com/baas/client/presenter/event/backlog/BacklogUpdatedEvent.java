package com.baas.client.presenter.event.backlog;

import com.baas.shared.core.Backlog;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogUpdatedEvent extends GwtEvent<BacklogUpdatedHandler> {

	private static final Type<BacklogUpdatedHandler> TYPE = new Type<BacklogUpdatedHandler>();

	public static Type<BacklogUpdatedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Backlog backlog) {
		source.fireEvent(new BacklogUpdatedEvent(backlog));
	}

	private Backlog backlog;

	public BacklogUpdatedEvent(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	protected void dispatch(BacklogUpdatedHandler handler) {
		handler.onBacklogUpdated(this);
	}

	@Override
	public Type<BacklogUpdatedHandler> getAssociatedType() {
		return getType();
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public Backlog getBacklog() {
		return backlog;
	}
}
