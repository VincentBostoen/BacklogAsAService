package com.baas.client.presenter.event.backlog;

import com.baas.shared.Backlog;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogDeletedEvent extends GwtEvent<BacklogDeletedHandler> {

	private static final Type<BacklogDeletedHandler> TYPE = new Type<BacklogDeletedHandler>();

	public static Type<BacklogDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Backlog backlog) {
		source.fireEvent(new BacklogDeletedEvent(backlog));
	}

	private Backlog backlog;

	public BacklogDeletedEvent(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	protected void dispatch(BacklogDeletedHandler handler) {
		handler.onBacklogDeleted(this);
	}

	@Override
	public Type<BacklogDeletedHandler> getAssociatedType() {
		return getType();
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public Backlog getBacklog() {
		return backlog;
	}
}
