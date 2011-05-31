package com.perso.baas.server.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.UserStoryDao;
import com.perso.baas.shared.UserStory;

@Singleton
@Path(ResourcePaths.STORY_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UserStoryResourceImpl extends GenericResource<Long, UserStory, UserStoryDao>{

	@Inject
	public UserStoryResourceImpl(UserStoryDao dao) {
		super(dao);
	}

	@Override
	protected Long generateKey(Key<UserStory> objectifyKey) {
		return objectifyKey.getId();
	}

	@Override
	protected String getKeyClassName() {
		return Long.class.getCanonicalName();
	}
}
