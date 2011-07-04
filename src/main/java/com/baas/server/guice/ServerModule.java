package com.baas.server.guice;

import com.baas.server.dispatcher.DeleteBacklogHandler;
import com.baas.server.dispatcher.DeleteUserStoriesHandler;
import com.baas.server.dispatcher.GetBacklogHandler;
import com.baas.server.dispatcher.GetBacklogListHandler;
import com.baas.server.dispatcher.GetStoriesFromBacklogHandler;
import com.baas.server.dispatcher.GetStoryHandler;
import com.baas.server.dispatcher.UpdateBacklogHandler;
import com.baas.server.dispatcher.UpdateStoryHandler;
import com.baas.shared.dispatch.DeleteBacklogAction;
import com.baas.shared.dispatch.DeleteUserStoriesAction;
import com.baas.shared.dispatch.GetBacklogAction;
import com.baas.shared.dispatch.GetBacklogListAction;
import com.baas.shared.dispatch.GetStoriesFromBacklogAction;
import com.baas.shared.dispatch.GetStoryAction;
import com.baas.shared.dispatch.UpdateBacklogAction;
import com.baas.shared.dispatch.UpdateStoryAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

  @Override
  protected void configureHandlers() {
    bindHandler(GetBacklogListAction.class, GetBacklogListHandler.class);
    bindHandler(GetStoriesFromBacklogAction.class, GetStoriesFromBacklogHandler.class);
    bindHandler(UpdateStoryAction.class, UpdateStoryHandler.class);
    bindHandler(UpdateBacklogAction.class, UpdateBacklogHandler.class);
    bindHandler(GetStoryAction.class, GetStoryHandler.class);
    bindHandler(GetBacklogAction.class, GetBacklogHandler.class);
    bindHandler(DeleteBacklogAction.class, DeleteBacklogHandler.class);
    bindHandler(DeleteUserStoriesAction.class, DeleteUserStoriesHandler.class);
  }
}
