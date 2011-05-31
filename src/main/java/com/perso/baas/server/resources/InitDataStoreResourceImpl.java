package com.perso.baas.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.BacklogDao;
import com.perso.baas.server.dao.UserStoryDao;
import com.perso.baas.shared.Backlog;
import com.perso.baas.shared.Complexity;
import com.perso.baas.shared.UserStory;

@Singleton
@Path("init")
@Produces(MediaType.APPLICATION_JSON)
public class InitDataStoreResourceImpl {

	private BacklogDao backlogDao;
	private UserStoryDao userStoryDao;

	@Inject
	public InitDataStoreResourceImpl(BacklogDao backlogDao, UserStoryDao userStoryDao) {
		this.backlogDao = backlogDao;
		this.userStoryDao = userStoryDao;
	}

	@GET
	public boolean init() {
		Key<Backlog> backlogKey = backlogDao.put(new Backlog("Mon backlog"));
		
		UserStory story = new UserStory();
//		story.setBusinessValue(10);
//		story.setComplexity(Complexity.EXTRA_LARGE);
//		story.setDescription("Une description");
//		story.setModuleName("Le module");
//		story.setName("Le nom");
//		story.setProjectVersion("1");
//		story.setSprintNumber(1);
		story.setBacklogId(backlogKey.getId());
		userStoryDao.put(story);
		
		return true;
	}
}
