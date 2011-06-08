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

import java.util.List;

import com.baas.server.dao.UserStoryDao;
import com.baas.shared.GetStoryListAction;
import com.baas.shared.GetStoryListResult;
import com.baas.shared.UserStory;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author Philippe Beaudoin
 */
public class GetStoryListHandler implements
    ActionHandler<GetStoryListAction, GetStoryListResult> {

  private final UserStoryDao userStoryDao;

  @Inject
  public GetStoryListHandler(UserStoryDao userStoryDao) {
    this.userStoryDao = userStoryDao;
  }

  @Override
  public GetStoryListResult execute(final GetStoryListAction action, final ExecutionContext context) throws ActionException {
	List<UserStory> stories = userStoryDao.getStories(action.getBacklogId());
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
