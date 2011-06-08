package com.baas.server.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.baas.server.dao.BacklogDao;
import com.baas.shared.core.Backlog;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.objectify.Key;

@Singleton
@Path(ResourcePaths.BACKLOG_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class BacklogResourceImpl extends GenericResource<Long, Backlog, BacklogDao>{
	
	@Inject
	public BacklogResourceImpl(BacklogDao dao) {
		super(dao);
	}

	@Override
	protected Long generateKey(Key<Backlog> objectifyKey) {
		return objectifyKey.getId();
	}

	@Override
	protected String getKeyClassName() {
		return Long.class.getCanonicalName();
	}
}
