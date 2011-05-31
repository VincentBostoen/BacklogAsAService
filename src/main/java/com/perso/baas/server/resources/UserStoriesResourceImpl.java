package com.perso.baas.server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.UserStoryDao;
import com.perso.baas.shared.UserStory;

@Singleton
@Path(ResourcePaths.STORIES_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UserStoriesResourceImpl extends GenericResource<Long, UserStory, UserStoryDao>{

	@Inject
	public UserStoriesResourceImpl(UserStoryDao dao) {
		super(dao);
	}

	@GET
	@Path("/frombacklog/{backlog_id}")
	public List<UserStory> list(@PathParam("backlog_id") long backlogId) {
		return dao.getStories(backlogId);
	}
	
	@Override
	protected Long generateKey(Key<UserStory> objectifyKey) {
		return objectifyKey.getId();
	}

	@Override
	protected String getKeyClassName() {
		return null;
	}
}
