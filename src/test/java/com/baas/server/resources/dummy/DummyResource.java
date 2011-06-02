package com.baas.server.resources.dummy;

import com.baas.server.dao.dummy.DummyStringDao;
import com.baas.server.resources.GenericResource;
import com.baas.shared.DummyPojoString;
import com.googlecode.objectify.Key;

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
