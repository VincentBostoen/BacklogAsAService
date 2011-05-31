package com.perso.baas.server.dao;

import com.google.inject.Singleton;
import com.perso.baas.shared.Backlog;

@Singleton
public class BacklogDao extends ObjectifyGenericDao<Backlog>{

	public BacklogDao() {
		super(Backlog.class);
	}
}
