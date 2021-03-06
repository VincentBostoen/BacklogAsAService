package com.baas.server.service;

import java.util.List;

import com.baas.server.dao.BacklogDao;
import com.baas.shared.core.Backlog;
import com.baas.shared.core.BacklogAlreadyExistException;
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

	public Backlog put(Backlog backlog) {
		Backlog exitingBacklog = backlogDao.getByProperty("projectName", backlog.getProjectName());
		
		if(exitingBacklog != null && !exitingBacklog.getId().equals(backlog.getId())){
			throw new BacklogAlreadyExistException("A backlog with the same project name (" + backlog.getProjectName() + ") already exist.");
		}
		
		Key<Backlog> backlogKey = backlogDao.put(backlog);
		backlog.setKey(backlogKey.getId());
		return backlog;
	}

	public void delete(long backlogId) {
		backlogDao.delete(backlogId);
	}

	public void setBacklogDao(BacklogDao backlogDao) {
		this.backlogDao = backlogDao;
	}
}
