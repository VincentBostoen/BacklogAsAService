package com.baas.server.dispatcher;

import com.baas.server.service.BacklogService;
import com.baas.shared.core.Backlog;
import com.baas.shared.dispatch.GetBacklogAction;
import com.baas.shared.dispatch.GetBacklogResult;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetBacklogHandler implements
    ActionHandler<GetBacklogAction, GetBacklogResult> {

  private final BacklogService backlogService;

  @Inject
  public GetBacklogHandler(BacklogService backlogService) {
    this.backlogService = backlogService;
  }

  @Override
  public GetBacklogResult execute(final GetBacklogAction action, final ExecutionContext context) throws ActionException {
	Backlog backlog;
	try {
		backlog = backlogService.retrieve(action.getBacklogId());
	} catch (EntityNotFoundException e) {
		throw new ActionException(e);
	}
    return new GetBacklogResult(backlog);
  }

  @Override
  public Class<GetBacklogAction> getActionType() {
    return GetBacklogAction.class;
  }

  @Override
  public void undo(final GetBacklogAction action,
      final GetBacklogResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
