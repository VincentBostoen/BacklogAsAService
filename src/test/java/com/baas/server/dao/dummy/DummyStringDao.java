package com.baas.server.dao.dummy;

import com.baas.server.dao.ObjectifyGenericDao;
import com.baas.shared.DummyPojoString;

public class DummyStringDao extends ObjectifyGenericDao<DummyPojoString> {

	public DummyStringDao() {
		super(DummyPojoString.class);
	}
}
