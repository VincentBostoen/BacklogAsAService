package com.baas.client.presenter.event.backlogs;

import java.util.List;

import com.baas.shared.core.Backlog;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class BacklogsListUpdatedEvent extends GwtEvent<BacklogsListUpdatedHandler> {

	private static final Type<BacklogsListUpdatedHandler> TYPE = new Type<BacklogsListUpdatedHandler>();

	public static Type<BacklogsListUpdatedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, List<Backlog> backlogs) {
		source.fireEvent(new BacklogsListUpdatedEvent(backlogs));
	}

	private List<Backlog> backlogs;

	public BacklogsListUpdatedEvent(List<Backlog> backlogs) {
		this.backlogs = backlogs;
	}

	@Override
	protected void dispatch(BacklogsListUpdatedHandler handler) {
		handler.onBacklogsListUpdated(this);
	}

	@Override
	public Type<BacklogsListUpdatedHandler> getAssociatedType() {
		return getType();
	}

	public void setBacklogs(List<Backlog> backlogs) {
		this.backlogs = backlogs;
	}

	public List<Backlog> getBacklogs() {
		return backlogs;
	}
}
