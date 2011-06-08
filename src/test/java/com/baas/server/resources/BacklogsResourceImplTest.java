package com.baas.server.resources;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.fest.assertions.AssertExtension;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.baas.PojoHelper;
import com.baas.server.dao.BacklogDao;
import com.baas.server.guice.GuiceConfig;
import com.baas.server.resources.BacklogsResourceImpl;
import com.baas.server.resources.ResourcePaths;
import com.baas.shared.core.Backlog;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class BacklogsResourceImplTest extends JerseyTest {

	private BacklogDao backlogDaoMock;
	
	/**
	 * Redefining the guice injector helps to deal with mocking (thank to nfrancois)
	 */
	private static Injector injector =  Guice.createInjector(new ServletModule() {
		@Override
		protected void configureServlets() {
			bind(BacklogsResourceImpl.class);
			serve(ResourcePaths.BACKLOG_BASE_URL + "*").with(GuiceContainer.class);
		}
	});
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		BacklogsResourceImpl backlogsResourceImpl = injector.getInstance(BacklogsResourceImpl.class);
		backlogDaoMock = createMock(BacklogDao.class);
		backlogsResourceImpl.setDao(backlogDaoMock);
	};
	
	public BacklogsResourceImplTest() {
		super(new WebAppDescriptor.Builder()
				.contextListenerClass(GuiceTestConfig.class)
				.filterClass(GuiceFilter.class)
				.servletPath(ResourcePaths.BACKLOG_BASE_URL)
				.build());
	}
	
	@Test
	public void testList() {
		expect(backlogDaoMock.list()).andReturn(Arrays.asList(PojoHelper.backlog("A project"), PojoHelper.backlog("Another project")));
		replay(backlogDaoMock);

		GenericType<List<Backlog>> genericType = new GenericType<List<Backlog>>(){};
		List<Backlog> backlogs = resource().path(ResourcePaths.BACKLOGS_PATH)
										   .path("list")
										   .type(MediaType.APPLICATION_JSON)
										   .get(genericType);
		verify(backlogDaoMock);
		Assertions.assertThat(backlogs).hasSize(2)
									   .contains(PojoHelper.backlog("A project"), PojoHelper.backlog("Another project"));
	}
	
	public class GuiceTestConfig extends GuiceServletContextListener {
		@Override
		public Injector getInjector() {
			return injector;
		}
	}	
}
