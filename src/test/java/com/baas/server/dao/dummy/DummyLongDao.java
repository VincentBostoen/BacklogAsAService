package com.baas.server.dao.dummy;

import com.baas.server.dao.ObjectifyGenericDao;
import com.baas.shared.DummyPojoLong;

public class DummyLongDao extends ObjectifyGenericDao<DummyPojoLong> {

	public DummyLongDao() {
		super(DummyPojoLong.class);
	}
}
