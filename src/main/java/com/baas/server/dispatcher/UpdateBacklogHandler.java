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
import com.baas.shared.UpdateBacklogAction;
import com.baas.shared.UpdateBacklogResult;
import com.baas.shared.core.Backlog;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Philippe Beaudoin
 */
public class UpdateBacklogHandler implements
    ActionHandler<UpdateBacklogAction, UpdateBacklogResult> {

  private final BacklogDao backlogDao;

  @Inject
  public UpdateBacklogHandler(BacklogDao BacklogDao) {
    this.backlogDao = BacklogDao;
  }

  @Override
  public UpdateBacklogResult execute(final UpdateBacklogAction action, final ExecutionContext context) throws ActionException {
	  Backlog story = action.getBacklog();
		Key<Backlog> key = backlogDao.put(story);
		if(story.getKey() == null){
			story.setKey(key.getId());
		}
    return new UpdateBacklogResult(story);
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
