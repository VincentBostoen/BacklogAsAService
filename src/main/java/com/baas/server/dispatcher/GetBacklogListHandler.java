package com.baas.server.dispatcher;

import java.util.List;

import com.baas.server.service.BacklogService;
import com.baas.shared.core.Backlog;
import com.baas.shared.dispatch.GetBacklogListAction;
import com.baas.shared.dispatch.GetBacklogListResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetBacklogListHandler implements
    ActionHandler<GetBacklogListAction, GetBacklogListResult> {

  private final BacklogService backlogService;

  @Inject
  public GetBacklogListHandler(BacklogService backlogService) {
    this.backlogService = backlogService;
  }

  @Override
  public GetBacklogListResult execute(final GetBacklogListAction action, final ExecutionContext context) throws ActionException {
    List<Backlog> backlogs = backlogService.list();
    return new GetBacklogListResult(backlogs);
  }

  @Override
  public Class<GetBacklogListAction> getActionType() {
    return GetBacklogListAction.class;
  }

  @Override
  public void undo(final GetBacklogListAction action,
      final GetBacklogListResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
