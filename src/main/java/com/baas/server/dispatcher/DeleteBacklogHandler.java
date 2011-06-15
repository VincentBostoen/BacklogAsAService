package com.baas.server.dispatcher;

import com.baas.server.service.BacklogService;
import com.baas.shared.DeleteBacklogAction;
import com.baas.shared.DeleteBacklogResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DeleteBacklogHandler implements
    ActionHandler<DeleteBacklogAction, DeleteBacklogResult> {

  private final BacklogService backlogService;

  @Inject
  public DeleteBacklogHandler(BacklogService backlogService) {
    this.backlogService = backlogService;
  }

  @Override
  public DeleteBacklogResult execute(final DeleteBacklogAction action, final ExecutionContext context) throws ActionException {
		backlogService.delete(action.getBacklogId());
		return new DeleteBacklogResult();
  }

  @Override
  public Class<DeleteBacklogAction> getActionType() {
    return DeleteBacklogAction.class;
  }

  @Override
  public void undo(final DeleteBacklogAction action,
      final DeleteBacklogResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
