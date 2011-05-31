package com.perso.baas.server.resources;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.Key;
import com.perso.baas.server.dao.UserStoryDao;
import com.perso.baas.shared.UserStory;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class UserStoryResourceImplTest extends JerseyTest {

	private UserStoryDao userStoryDaoMock;

	/**
	 * Redefining the guice injector helps to deal with mocking (thank to
	 * nfrancois)
	 */
	private static Injector injector = Guice
			.createInjector(new ServletModule() {
				@Override
				protected void configureServlets() {
					bind(UserStoryResourceImpl.class);
					serve(ResourcePaths.BACKLOG_BASE_URL + "*").with(
							GuiceContainer.class);
				}
			});

	@Override
	protected AppDescriptor configure() {
		injector = Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				final Map<String, String> params = new HashMap<String, String>();
				bind(UserStoryResourceImpl.class);
				bind(JAXBContextResolver.class);
				serve("/*").with(GuiceContainer.class, params);
			}
		});
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JAXBContextResolver.class);
		return new WebAppDescriptor.Builder("com.perso.baas.server.resources")
				.contextListenerClass(GuiceTestConfig.class)
				.filterClass(GuiceFilter.class).clientConfig(clientConfig)
				.servletPath("/").build();
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		userStoryDaoMock = createMock(UserStoryDao.class);
		UserStoryResourceImpl userStoryResourceImpl = injector
				.getInstance(UserStoryResourceImpl.class);
		userStoryResourceImpl.setDao(userStoryDaoMock);
	}

	@Test
	public void testRetrieve() throws EntityNotFoundException {
		UserStory story = new UserStory();
		story.setId(1l);

		expect(userStoryDaoMock.get(story.getKey())).andReturn(story);
		replay(userStoryDaoMock);

		GenericType<UserStory> genericType = new GenericType<UserStory>() {
		};
		UserStory retrievedStory = resource().path(ResourcePaths.STORY_PATH)
				.path("/" + story.getKey()).type(MediaType.APPLICATION_JSON)
				.get(genericType);

		verify(userStoryDaoMock);

		Assertions.assertThat(retrievedStory).isEqualTo(story);
	}

	@Test
	public void testCreate() throws EntityNotFoundException {
		UserStory story = new UserStory();
		story.setId(1l);
		Key<UserStory> storyKey = new Key<UserStory>(UserStory.class,
				story.getId());

		expect(userStoryDaoMock.put(story)).andReturn(storyKey);
		replay(userStoryDaoMock);

		GenericType<UserStory> genericType = new GenericType<UserStory>() {
		};
		UserStory retrievedStory = resource().path(ResourcePaths.STORY_PATH)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).put(genericType, story);

		verify(userStoryDaoMock);

		Assertions.assertThat(retrievedStory).isEqualTo(story);
	}

	@Test
	public void testUpdate() throws EntityNotFoundException {
		UserStory story = new UserStory();
		story.setId(1l);
		Key<UserStory> backlogKey = new Key<UserStory>(UserStory.class,
				story.getId());

		expect(userStoryDaoMock.put(story)).andReturn(backlogKey);
		replay(userStoryDaoMock);

		GenericType<UserStory> genericType = new GenericType<UserStory>() {
		};
		UserStory retrievedBacklog = resource().path(ResourcePaths.STORY_PATH)
				.path("/" + story.getKey()).type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(genericType, story);

		verify(userStoryDaoMock);

		Assertions.assertThat(retrievedBacklog).isEqualTo(story);
	}

	public class GuiceTestConfig extends GuiceServletContextListener {
		@Override
		public Injector getInjector() {
			return injector;
		}
	}
}
