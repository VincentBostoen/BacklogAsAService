package com.baas.server.guice;

import com.baas.server.dispatcher.DeleteBacklogHandler;
import com.baas.server.dispatcher.GetBacklogHandler;
import com.baas.server.dispatcher.GetBacklogListHandler;
import com.baas.server.dispatcher.GetStoryHandler;
import com.baas.server.dispatcher.GetStoryListHandler;
import com.baas.server.dispatcher.UpdateBacklogHandler;
import com.baas.server.dispatcher.UpdateStoryHandler;
import com.baas.shared.DeleteBacklogAction;
import com.baas.shared.GetBacklogAction;
import com.baas.shared.GetBacklogListAction;
import com.baas.shared.GetStoryAction;
import com.baas.shared.GetStoryListAction;
import com.baas.shared.UpdateBacklogAction;
import com.baas.shared.UpdateStoryAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

  @Override
  protected void configureHandlers() {
    bindHandler(GetBacklogListAction.class, GetBacklogListHandler.class);
    bindHandler(GetStoryListAction.class, GetStoryListHandler.class);
    bindHandler(UpdateStoryAction.class, UpdateStoryHandler.class);
    bindHandler(UpdateBacklogAction.class, UpdateBacklogHandler.class);
    bindHandler(GetStoryAction.class, GetStoryHandler.class);
    bindHandler(GetBacklogAction.class, GetBacklogHandler.class);
    bindHandler(DeleteBacklogAction.class, DeleteBacklogHandler.class);
  }
}
