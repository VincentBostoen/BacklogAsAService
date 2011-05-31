package com.perso.baas.server.resources.dummy;

import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.dummy.DummyStringDao;
import com.perso.baas.server.resources.GenericResource;
import com.perso.baas.shared.DummyPojoString;

public class DummyResource extends GenericResource<String, DummyPojoString, DummyStringDao>{

	public DummyResource(DummyStringDao dao) {
		super(dao);
	}

	@Override
	protected String generateKey(Key<DummyPojoString> objectifyKey) {
		return objectifyKey.getName();
	}

	@Override
	protected String getKeyClassName() {
		return "Long";
	}
}
