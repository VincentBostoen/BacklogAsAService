package com.perso.baas.client.presenter.event.backlogs;

import com.google.gwt.event.shared.EventHandler;
import com.perso.baas.client.presenter.event.backlog.BacklogUpdatedEvent;

public interface BacklogsListUpdatedHandler extends EventHandler {
  void onBacklogsListUpdated(BacklogsListUpdatedEvent event);
}
