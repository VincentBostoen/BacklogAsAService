package com.baas.client.presenter.event.backlog;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogSelectedEvent extends GwtEvent<BacklogSelectedHandler> {

	private static final Type<BacklogSelectedHandler> TYPE = new Type<BacklogSelectedHandler>();

	public static Type<BacklogSelectedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, long backlogId) {
		source.fireEvent(new BacklogSelectedEvent(backlogId));
	}

	private long backlogId;

	public BacklogSelectedEvent(long backlogId) {
		this.backlogId = backlogId;
	}

	@Override
	protected void dispatch(BacklogSelectedHandler handler) {
		handler.onBacklogSelected(this);
	}

	@Override
	public Type<BacklogSelectedHandler> getAssociatedType() {
		return getType();
	}

	public void setBacklogId(long backlogId) {
		this.backlogId = backlogId;
	}

	public long getBacklogId() {
		return backlogId;
	}
}
