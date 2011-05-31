package com.perso.baas.client.resources;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.perso.baas.client.AbstractGWTTestCase;
import com.perso.baas.shared.Backlog;

public class BacklogResourceGwtTest extends AbstractGWTTestCase {

	private BacklogsResource service;

	public BacklogResourceGwtTest() {
	}

	@Override
	protected void gwtSetUp() throws Exception {
		super.gwtSetUp();
	}

	@Override
	protected void gwtTearDown() throws Exception {
		super.gwtTearDown();
	}

	@Test
	public void testGet() {
		Resource resource = new Resource("/baas/resources/backlogs/list");
		service = GWT.create(BacklogsResource.class);
		((RestServiceProxy) service).setResource(resource);

		service.get(new MethodCallback<List<Backlog>>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
				fail();
			}

			@Override
			public void onSuccess(Method method, List<Backlog> backlogs) {
				assertNotNull(backlogs);
				assertEquals(3, backlogs.size());
			}
		});
	}

}
