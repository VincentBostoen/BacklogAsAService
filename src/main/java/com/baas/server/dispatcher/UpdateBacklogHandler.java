package com.baas.server.dispatcher;

import com.baas.server.service.BacklogService;
import com.baas.shared.core.Backlog;
import com.baas.shared.dispatch.UpdateBacklogAction;
import com.baas.shared.dispatch.UpdateBacklogResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateBacklogHandler implements
    ActionHandler<UpdateBacklogAction, UpdateBacklogResult> {

  private final BacklogService backlogService;

  @Inject
  public UpdateBacklogHandler(BacklogService backlogService) {
    this.backlogService = backlogService;
  }

  @Override
  public UpdateBacklogResult execute(final UpdateBacklogAction action, final ExecutionContext context) throws ActionException {
		Backlog backlog = backlogService.put(action.getBacklog());
		return new UpdateBacklogResult(backlog);
  }

  @Override
  public Class<UpdateBacklogAction> getActionType() {
    return UpdateBacklogAction.class;
  }

  @Override
  public void undo(final UpdateBacklogAction action,
      final UpdateBacklogResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
