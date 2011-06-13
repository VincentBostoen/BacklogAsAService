package com.baas.server.dispatcher;

import java.util.List;

import com.baas.server.service.UserStoryService;
import com.baas.shared.GetStoryListAction;
import com.baas.shared.GetStoryListResult;
import com.baas.shared.core.UserStory;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStoryListHandler implements
    ActionHandler<GetStoryListAction, GetStoryListResult> {

  private final UserStoryService userStoryService;

  @Inject
  public GetStoryListHandler(UserStoryService userStoryService) {
    this.userStoryService = userStoryService;
  }

  @Override
  public GetStoryListResult execute(final GetStoryListAction action, final ExecutionContext context) throws ActionException {
	List<UserStory> stories = userStoryService.list(action.getBacklogId());
    return new GetStoryListResult(stories);
  }

  @Override
  public Class<GetStoryListAction> getActionType() {
    return GetStoryListAction.class;
  }

  @Override
  public void undo(final GetStoryListAction action,
      final GetStoryListResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
