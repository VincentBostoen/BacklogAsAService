package com.perso.baas.server.dao.dummy;

import com.perso.baas.server.dao.ObjectifyGenericDao;
import com.perso.baas.shared.DummyPojoString;

public class DummyStringDao extends ObjectifyGenericDao<DummyPojoString> {

	public DummyStringDao() {
		super(DummyPojoString.class);
	}
}
