package com.baas.server.dispatcher;

import java.util.List;

import com.baas.server.service.UserStoryService;
import com.baas.shared.core.UserStory;
import com.baas.shared.dispatch.GetStoriesFromBacklogAction;
import com.baas.shared.dispatch.GetStoriesFromBacklogResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStoriesFromBacklogHandler implements
    ActionHandler<GetStoriesFromBacklogAction, GetStoriesFromBacklogResult> {

  private final UserStoryService userStoryService;

  @Inject
  public GetStoriesFromBacklogHandler(UserStoryService userStoryService) {
    this.userStoryService = userStoryService;
  }

  @Override
  public GetStoriesFromBacklogResult execute(final GetStoriesFromBacklogAction action, final ExecutionContext context) throws ActionException {
	List<UserStory> stories = userStoryService.list(action.getBacklogId());
    return new GetStoriesFromBacklogResult(stories);
  }

  @Override
  public Class<GetStoriesFromBacklogAction> getActionType() {
    return GetStoriesFromBacklogAction.class;
  }

  @Override
  public void undo(final GetStoriesFromBacklogAction action,
      final GetStoriesFromBacklogResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
