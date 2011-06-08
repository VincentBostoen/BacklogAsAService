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

import com.baas.server.dao.BacklogDao;
import com.baas.shared.GetBacklogAction;
import com.baas.shared.GetBacklogResult;
import com.baas.shared.core.Backlog;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Philippe Beaudoin
 */
public class GetBacklogHandler implements
    ActionHandler<GetBacklogAction, GetBacklogResult> {

  private final BacklogDao backlogDao;

  @Inject
  public GetBacklogHandler(BacklogDao backlogDao) {
    this.backlogDao = backlogDao;
  }

  @Override
  public GetBacklogResult execute(final GetBacklogAction action, final ExecutionContext context) throws ActionException {
	Backlog backlog;
	try {
		backlog = backlogDao.get(action.getBacklogId());
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
