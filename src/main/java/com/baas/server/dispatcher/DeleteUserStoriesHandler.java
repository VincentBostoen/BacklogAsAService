package com.baas.server.dispatcher;

import com.baas.server.service.UserStoryService;
import com.baas.shared.DeleteUserStoriesAction;
import com.baas.shared.DeleteUserStoriesResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteUserStoriesHandler implements
    ActionHandler<DeleteUserStoriesAction, DeleteUserStoriesResult> {

  private final UserStoryService userStoryService;

  @Inject
  public DeleteUserStoriesHandler(UserStoryService userStoryService) {
    this.userStoryService = userStoryService;
  }

  @Override
  public DeleteUserStoriesResult execute(final DeleteUserStoriesAction action, final ExecutionContext context) throws ActionException {
//	  userStoryService.delete(action.getUserStoriesKeys());
		return new DeleteUserStoriesResult();
  }

  @Override
  public Class<DeleteUserStoriesAction> getActionType() {
    return DeleteUserStoriesAction.class;
  }

  @Override
  public void undo(final DeleteUserStoriesAction action,
      final DeleteUserStoriesResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
