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
import com.baas.server.dao.UserStoryDao;
import com.baas.server.guice.GuiceConfig;
import com.baas.server.resources.ResourcePaths;
import com.baas.server.resources.UserStoriesResourceImpl;
import com.baas.shared.Backlog;
import com.baas.shared.UserStory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.Key;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class UserStoriesResourceImplTest extends JerseyTest {

	private UserStoryDao userStoryDaoMock;
	
	/**
	 * Redefining the guice injector helps to deal with mocking (thank to nfrancois)
	 */
	private static Injector injector =  Guice.createInjector(new ServletModule() {
		@Override
		protected void configureServlets() {
			bind(UserStoriesResourceImpl.class);
			serve(ResourcePaths.BACKLOG_BASE_URL + "*").with(GuiceContainer.class);
		}
	});
	
	public UserStoriesResourceImplTest() {
		super(new WebAppDescriptor.Builder()
				.contextListenerClass(GuiceTestConfig.class)
				.filterClass(GuiceFilter.class)
				.servletPath(ResourcePaths.BACKLOG_BASE_URL)
				.build());
	}
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		userStoryDaoMock = createMock(UserStoryDao.class);
		UserStoriesResourceImpl userStoriesResourceImpl = injector.getInstance(UserStoriesResourceImpl.class);
		userStoriesResourceImpl.setDao(userStoryDaoMock);
	};
	
	@Test
	public void testList() throws EntityNotFoundException{
		
		long backlogId = 123l;
		
		UserStory userStory1 = PojoHelper.userStory("Story 1");
		UserStory userStory2 = PojoHelper.userStory("Story 2");
		
		expect(userStoryDaoMock.getStories(backlogId)).andReturn(Arrays.asList(userStory1, userStory2));
		replay(userStoryDaoMock);

		GenericType<List<UserStory>> genericType = new GenericType<List<UserStory>>(){};
		List<UserStory> stories = resource().path(ResourcePaths.STORIES_PATH)
											.path("/frombacklog/" + backlogId)
										    .type(MediaType.APPLICATION_JSON)
										    .get(genericType);
		
		verify(userStoryDaoMock);
		
		Assertions.assertThat(stories).hasSize(2).contains(userStory1, userStory2);
	}
	
	public class GuiceTestConfig extends GuiceServletContextListener {
		@Override
		public Injector getInjector() {
			return injector;
		}
	}	
}
