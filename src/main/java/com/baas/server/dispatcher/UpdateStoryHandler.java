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

import com.baas.server.dao.UserStoryDao;
import com.baas.shared.UpdateStoryAction;
import com.baas.shared.UpdateStoryResult;
import com.baas.shared.UserStory;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Philippe Beaudoin
 */
public class UpdateStoryHandler implements
    ActionHandler<UpdateStoryAction, UpdateStoryResult> {

  private final UserStoryDao userStoryDao;

  @Inject
  public UpdateStoryHandler(UserStoryDao userStoryDao) {
    this.userStoryDao = userStoryDao;
  }

  @Override
  public UpdateStoryResult execute(final UpdateStoryAction action, final ExecutionContext context) throws ActionException {
	  UserStory story = action.getStory();
		Key<UserStory> key = userStoryDao.put(story);
		if(story.getKey() == null){
			story.setKey(key.getId());
		}
    return new UpdateStoryResult(story);
  }

  @Override
  public Class<UpdateStoryAction> getActionType() {
    return UpdateStoryAction.class;
  }

  @Override
  public void undo(final UpdateStoryAction action,
      final UpdateStoryResult result, final ExecutionContext context)
      throws ActionException {
    // No undo
  }
}
