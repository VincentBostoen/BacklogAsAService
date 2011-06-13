package com.baas.server.service;

import java.util.List;

import com.baas.server.dao.BacklogDao;
import com.baas.shared.core.Backlog;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;

public class BacklogService {

	private BacklogDao backlogDao;

	@Inject
	public BacklogService(BacklogDao backlogDao) {
		this.backlogDao = backlogDao;
	}

	public Backlog retrieve(long backlogId) throws EntityNotFoundException {
		return backlogDao.get(backlogId);
	}

	public List<Backlog> list() {
		return backlogDao.list();
	}

	public void setBacklogDao(BacklogDao backlogDao) {
		this.backlogDao = backlogDao;
	}

	public Backlog put(Backlog backlog) {
		Key<Backlog> backlogKey = backlogDao.put(backlog);
		backlog.setKey(backlogKey.getId());
		return backlog;
	}
}
