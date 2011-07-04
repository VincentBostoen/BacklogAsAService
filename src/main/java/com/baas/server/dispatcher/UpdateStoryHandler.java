package com.baas.server.dispatcher;

import com.baas.server.service.UserStoryService;
import com.baas.shared.core.UserStory;
import com.baas.shared.dispatch.UpdateStoryAction;
import com.baas.shared.dispatch.UpdateStoryResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateStoryHandler implements
    ActionHandler<UpdateStoryAction, UpdateStoryResult> {

  private final UserStoryService userStoryService;

  @Inject
  public UpdateStoryHandler(UserStoryService userStoryService) {
    this.userStoryService = userStoryService;
  }

  @Override
  public UpdateStoryResult execute(final UpdateStoryAction action, final ExecutionContext context) throws ActionException {
		UserStory story = userStoryService.put(action.getStory());
		return new UpdateStoryResult(story);
  }

  @Override
  public Class<UpdateStoryAction> getActionType() {
    return UpdateStoryAction.class;
  }

  @Override
  public void undo(final UpdateStoryAction action,
      final UpdateStoryResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
