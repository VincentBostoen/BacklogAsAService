package com.baas.server.service;

import static junit.framework.Assert.fail;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.AssertExtension;
import org.junit.Before;
import org.junit.Test;

import com.baas.PojoHelper;
import com.baas.server.dao.BacklogDao;
import com.baas.server.guice.DispatchServletModule;
import com.baas.shared.core.Backlog;
import com.baas.shared.core.BacklogAlreadyExistException;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.objectify.Key;

public class BacklogServiceTest{

	private static Injector injector = Guice.createInjector(new DispatchServletModule());
	private BacklogService backlogService;
	private BacklogDao backlogDaoMock;
	
	@Before
	public void setUp() throws Exception {
		backlogDaoMock = createMock(BacklogDao.class);
		backlogService = injector.getInstance(BacklogService.class);
		backlogService.setBacklogDao(backlogDaoMock);
	}
	
	@Test
	public void testGetByIdOk() throws EntityNotFoundException{
		long backlogId = 1l;
		Backlog backlogMock = PojoHelper.backlog("A backlog");
		backlogMock.setId(backlogId);
		
		expect(backlogDaoMock.get(backlogMock.getKey())).andReturn(backlogMock);
		replay(backlogDaoMock);
		
		Backlog result = backlogService.retrieve(backlogId);

		verify(backlogDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(backlogMock);
	}
	
	@Test
	public void testGetByIdKoEntityNotFoundException() throws EntityNotFoundException{
		long backlogId = 1l;
		Backlog backlogMock = PojoHelper.backlog("A backlog");
		backlogMock.setId(backlogId);
		
		expect(backlogDaoMock.get(backlogMock.getKey())).andThrow(new EntityNotFoundException(null));
		replay(backlogDaoMock);

		try{
			backlogService.retrieve(backlogId);
			fail();
		}catch (Exception e) {
			assertThat(e).isInstanceOf(EntityNotFoundException.class);
		}
		
		verify(backlogDaoMock);
	}
	
	@Test
	public void testList(){
		List<Backlog> backlogsMock = Arrays.asList(PojoHelper.backlog("A backlog"), PojoHelper.backlog("Another one"), PojoHelper.backlog("A third"));
		
		expect(backlogDaoMock.list()).andReturn(backlogsMock);
		replay(backlogDaoMock);
		
		List<Backlog> result = backlogService.list();

		verify(backlogDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(backlogsMock);
	}
	
	@Test
	public void testPut(){
		Backlog backlog = new Backlog("New backlog");
		Key<Backlog> backlogKey = new Key<Backlog>(Backlog.class, backlog.getProjectName());
		
		expect(backlogDaoMock.getByProperty("projectName", backlog.getProjectName())).andReturn(null);
		expect(backlogDaoMock.put(backlog)).andReturn(backlogKey);
		replay(backlogDaoMock);
		
		Backlog result = backlogService.put(backlog);

		verify(backlogDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(backlog);
		assertThat(result.getKey()).isEqualTo(backlogKey.getId());
	}
	
	@Test
	public void testPutWithAlreadyExistingName(){
		Backlog existingBacklog = new Backlog("An existing backlog");
		existingBacklog.setId(1l);
		
		Backlog backlog = new Backlog("New backlog");
		Key<Backlog> backlogKey = new Key<Backlog>(Backlog.class, backlog.getProjectName());
		
		expect(backlogDaoMock.getByProperty("projectName", backlog.getProjectName())).andReturn(existingBacklog);
		replay(backlogDaoMock);
		
		try {
			backlogService.put(backlog);
			fail();
		} catch (Exception e) {
			assertThat(e).isInstanceOf(BacklogAlreadyExistException.class);
		}

		verify(backlogDaoMock);
	}
	
	@Test
	public void testPutAnExistingBacklog(){
		Backlog backlog = new Backlog("New backlog");
		backlog.setId(1l);
		Key<Backlog> backlogKey = new Key<Backlog>(Backlog.class, backlog.getProjectName());
		
		expect(backlogDaoMock.getByProperty("projectName", backlog.getProjectName())).andReturn(backlog);
		expect(backlogDaoMock.put(backlog)).andReturn(backlogKey);
		replay(backlogDaoMock);
		
		Backlog result = backlogService.put(backlog);

		verify(backlogDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(backlog);
		assertThat(result.getKey()).isEqualTo(backlogKey.getId());
	}
	
	@Test
	public void testDelete(){
		long backlogId = 1l;
		
		expect(backlogDaoMock.delete(backlogId)).andReturn(true);
		replay(backlogDaoMock);
		
		backlogService.delete(backlogId);

		verify(backlogDaoMock);
	}
}
