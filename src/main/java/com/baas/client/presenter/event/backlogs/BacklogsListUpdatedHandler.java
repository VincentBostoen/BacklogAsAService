package com.baas.client.presenter.event.backlogs;

import com.baas.client.presenter.event.backlog.BacklogUpdatedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface BacklogsListUpdatedHandler extends EventHandler {
  void onBacklogsListUpdated(BacklogsListUpdatedEvent event);
}
