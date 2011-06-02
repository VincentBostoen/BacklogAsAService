package com.baas.server.dao;

import com.baas.shared.Backlog;
import com.google.inject.Singleton;

@Singleton
public class BacklogDao extends ObjectifyGenericDao<Backlog>{

	public BacklogDao() {
		super(Backlog.class);
	}
}
