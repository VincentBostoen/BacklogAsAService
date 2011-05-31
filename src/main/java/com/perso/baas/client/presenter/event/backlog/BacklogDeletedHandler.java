package com.perso.baas.client.presenter.event.backlog;

import com.google.gwt.event.shared.EventHandler;

public interface BacklogDeletedHandler extends EventHandler {
  void onBacklogDeleted(BacklogDeletedEvent event);
}
