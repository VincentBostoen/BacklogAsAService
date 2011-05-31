package com.perso.baas.client.presenter.event.backlog;

import com.google.gwt.event.shared.EventHandler;

public interface BacklogUpdatedHandler extends EventHandler {
  void onBacklogUpdated(BacklogUpdatedEvent event);
}
