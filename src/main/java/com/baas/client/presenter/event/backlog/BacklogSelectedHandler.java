package com.baas.client.presenter.event.backlog;

import com.google.gwt.event.shared.EventHandler;

public interface BacklogSelectedHandler extends EventHandler {
  void onBacklogSelected(BacklogSelectedEvent event);
}
