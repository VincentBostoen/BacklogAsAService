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
import com.perso.baas.server.dao.BacklogDao;
import com.perso.baas.shared.Backlog;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class BacklogResourceImplTest extends JerseyTest {

	private BacklogDao backlogDaoMock;
	
	/**
	 * Redefining the guice injector helps to deal with mocking (thank to nfrancois)
	 */
	private static Injector injector =  Guice.createInjector(new ServletModule() {
		@Override
		protected void configureServlets() {
			bind(BacklogResourceImpl.class);
			serve(ResourcePaths.BACKLOG_BASE_URL + "*").with(GuiceContainer.class);
		}
	});
	
	@Override
	protected AppDescriptor configure() {
		injector =  Guice.createInjector(new ServletModule() {
				
				@Override
				protected void configureServlets() {
					final Map<String, String> params = new HashMap<String, String>();
					bind(BacklogResourceImpl.class);
					bind(JAXBContextResolver.class);
					serve("/*").with(GuiceContainer.class, params);
				}
			});			
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JAXBContextResolver.class);
		return new WebAppDescriptor.Builder("com.perso.baas.server.resources")
			        .contextListenerClass(GuiceTestConfig.class)
			        .filterClass(GuiceFilter.class)
			        .clientConfig(clientConfig)
			        .servletPath("/")
			        .build();
	}
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		backlogDaoMock = createMock(BacklogDao.class);
		BacklogResourceImpl backlogResourceImpl = injector.getInstance(BacklogResourceImpl.class);
		backlogResourceImpl.setDao(backlogDaoMock);
	}
	
	@Test
	public void testRetrieve() throws EntityNotFoundException{
		Backlog backlog = new Backlog("Nouveau backlog");
		backlog.setKey(111l);
		
		expect(backlogDaoMock.get(backlog.getKey())).andReturn(backlog);
		replay(backlogDaoMock);

		GenericType<Backlog> genericType = new GenericType<Backlog>(){};
		Backlog retrievedBacklog = resource().path(ResourcePaths.BACKLOG_PATH)
											 .path("/" + backlog.getKey())
										     .type(MediaType.APPLICATION_JSON)
										     .get(genericType);
		
		verify(backlogDaoMock);
		
		Assertions.assertThat(retrievedBacklog).isEqualTo(backlog);
	}
	
	@Test
	public void testCreate() throws EntityNotFoundException{
		Backlog backlog = new Backlog("Nouveau backlog");
		Key<Backlog> backlogKey = new Key<Backlog>(Backlog.class, backlog.getProjectName());
		
		expect(backlogDaoMock.put(backlog)).andReturn(backlogKey);
		replay(backlogDaoMock);

		GenericType<Backlog> genericType = new GenericType<Backlog>(){};
		Backlog retrievedBacklog = resource().path(ResourcePaths.BACKLOG_PATH)
										     .type(MediaType.APPLICATION_JSON)
										     .accept(MediaType.APPLICATION_JSON)
										     .put(genericType, backlog);
		
		verify(backlogDaoMock);
		
		Assertions.assertThat(retrievedBacklog).isEqualTo(backlog);
	}
	
	@Test
	public void testUpdate() throws EntityNotFoundException{
		Backlog backlog = new Backlog("Nouveau backlog");
		Key<Backlog> backlogKey = new Key<Backlog>(Backlog.class, backlog.getProjectName());
		
		expect(backlogDaoMock.put(backlog)).andReturn(backlogKey);
		replay(backlogDaoMock);

		GenericType<Backlog> genericType = new GenericType<Backlog>(){};
		Backlog retrievedBacklog = resource().path(ResourcePaths.BACKLOG_PATH)
											 .path("/" + backlog.getKey())
										     .type(MediaType.APPLICATION_JSON)
										     .accept(MediaType.APPLICATION_JSON)
										     .post(genericType, backlog);
		
		verify(backlogDaoMock);
		
		Assertions.assertThat(retrievedBacklog).isEqualTo(backlog);
	}
	
	public class GuiceTestConfig extends GuiceServletContextListener {
		@Override
		public Injector getInjector() {
			return injector;
		}
	}	
}
