package com.perso.baas.server.dao.dummy;

import com.perso.baas.server.dao.ObjectifyGenericDao;
import com.perso.baas.shared.DummyPojoLong;

public class DummyLongDao extends ObjectifyGenericDao<DummyPojoLong> {

	public DummyLongDao() {
		super(DummyPojoLong.class);
	}
}
