package com.baas.client.presenter.event.backlog;

import com.baas.shared.core.Backlog;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogDeletedEvent extends GwtEvent<BacklogDeletedHandler> {

	private static final Type<BacklogDeletedHandler> TYPE = new Type<BacklogDeletedHandler>();

	public static Type<BacklogDeletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, long backlogId) {
		source.fireEvent(new BacklogDeletedEvent(backlogId));
	}

	private long backlogId;

	public BacklogDeletedEvent(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	protected void dispatch(BacklogDeletedHandler handler) {
		handler.onBacklogDeleted(this);
	}

	@Override
	public Type<BacklogDeletedHandler> getAssociatedType() {
		return getType();
	}

	public void setBacklogId(long backlogId) {
		this.backlogId = backlogId;
	}

	public long getBacklogId() {
		return backlogId;
	}
}
