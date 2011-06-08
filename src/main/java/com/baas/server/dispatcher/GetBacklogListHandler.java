/**
 * Copyright 2010 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.baas.server.dispatcher;

import java.util.ArrayList;
import java.util.List;

import com.baas.server.dao.BacklogDao;
import com.baas.shared.Backlog;
import com.baas.shared.GetBacklogListAction;
import com.baas.shared.GetBacklogListResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Philippe Beaudoin
 */
public class GetBacklogListHandler implements
    ActionHandler<GetBacklogListAction, GetBacklogListResult> {

  private final BacklogDao backlogDao;

  @Inject
  public GetBacklogListHandler(BacklogDao backlogDao) {
    this.backlogDao = backlogDao;
  }

  @Override
  public GetBacklogListResult execute(final GetBacklogListAction action, final ExecutionContext context) throws ActionException {
    List<Backlog> backlogs = backlogDao.list();
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
