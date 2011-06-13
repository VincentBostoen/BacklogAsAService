package com.baas.server.dispatcher;

import com.baas.server.service.UserStoryService;
import com.baas.shared.GetStoryAction;
import com.baas.shared.GetStoryResult;
import com.baas.shared.core.UserStory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStoryHandler implements
    ActionHandler<GetStoryAction, GetStoryResult> {

  private final UserStoryService userStoryService;

  @Inject
  public GetStoryHandler(UserStoryService userStoryService) {
    this.userStoryService = userStoryService;
  }

  @Override
  public GetStoryResult execute(final GetStoryAction action, final ExecutionContext context) throws ActionException {
	UserStory story;
	try {
		story = userStoryService.retrieve(action.getStoryId());
	} catch (EntityNotFoundException e) {
		throw new ActionException(e);
	}
    return new GetStoryResult(story);
  }

  @Override
  public Class<GetStoryAction> getActionType() {
    return GetStoryAction.class;
  }

  @Override
  public void undo(final GetStoryAction action,
      final GetStoryResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
