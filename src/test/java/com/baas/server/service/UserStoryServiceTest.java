package com.baas.server.service;


import static junit.framework.Assert.fail;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.baas.PojoHelper;
import com.baas.server.dao.UserStoryDao;
import com.baas.server.guice.DispatchServletModule;
import com.baas.shared.core.UserStory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.objectify.Key;

public class UserStoryServiceTest {

	private static Injector injector = Guice.createInjector(new DispatchServletModule());
	private UserStoryService userStoryService;
	private UserStoryDao userStoryDaoMock;
	
	@Before
	public void setUp() throws Exception {
		userStoryDaoMock = createMock(UserStoryDao.class);
		userStoryService = injector.getInstance(UserStoryService.class);
		userStoryService.setUserStoryDao(userStoryDaoMock);
	}
	
	@Test
	public void testGetByIdOk() throws EntityNotFoundException{
		long userStoryId = 1l;
		UserStory userStoryMock = PojoHelper.userStory("A story");
		userStoryMock.setId(userStoryId);
		
		expect(userStoryDaoMock.get(userStoryMock.getKey())).andReturn(userStoryMock);
		replay(userStoryDaoMock);
		
		UserStory result = userStoryService.retrieve(userStoryId);

		verify(userStoryDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(userStoryMock);
	}
	
	@Test
	public void testGetByIdKoEntityNotFoundException() throws EntityNotFoundException{
		long userStoryId = 1l;
		UserStory userStoryMock = PojoHelper.userStory("A story");
		userStoryMock.setId(userStoryId);
		
		expect(userStoryDaoMock.get(userStoryMock.getKey())).andThrow(new EntityNotFoundException(null));
		replay(userStoryDaoMock);

		try{
			userStoryService.retrieve(userStoryId);
			fail();
		}catch (Exception e) {
			assertThat(e).isInstanceOf(EntityNotFoundException.class);
		}
		
		verify(userStoryDaoMock);
	}
	
	@Test
	public void testList(){
		long backlogId = 1l;
		List<UserStory> userStoriesMock = Arrays.asList(PojoHelper.userStory("A story"), PojoHelper.userStory("Another one"), PojoHelper.userStory("A third"));
		
		expect(userStoryDaoMock.getStories(backlogId)).andReturn(userStoriesMock);
		replay(userStoryDaoMock);
		
		List<UserStory> result = userStoryService.list(backlogId);

		verify(userStoryDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(userStoriesMock);
	}
	
	@Test
	public void testPut(){
		UserStory userStory = new PojoHelper().userStory("A new story");
		Key<UserStory> userStoryKey = new Key<UserStory>(UserStory.class, 1l);
		
		expect(userStoryDaoMock.put(userStory)).andReturn(userStoryKey);
		replay(userStoryDaoMock);
		
		UserStory result = userStoryService.put(userStory);

		verify(userStoryDaoMock);
		
		assertThat(result).isNotNull().isEqualTo(userStory);
		assertThat(result.getKey()).isEqualTo(userStoryKey.getId());
	}
	
	@Test
	public void testDelete(){
		long userStoryId = 1l;
		
		expect(userStoryDaoMock.delete(userStoryId)).andReturn(true);
		replay(userStoryDaoMock);
		
		userStoryService.delete(userStoryId);

		verify(userStoryDaoMock);
	}
	
	@Test
	public void testDeleteSeveral(){
		Set<Key<UserStory>> storiesKeys = new HashSet<Key<UserStory>>();
		storiesKeys.add(new Key<UserStory>(UserStory.class, 1l));
		storiesKeys.add(new Key<UserStory>(UserStory.class, 2l));
		storiesKeys.add(new Key<UserStory>(UserStory.class, 3l));
		
		Set<Long> storiesIds = new HashSet<Long>();
		storiesIds.add(1l);
		storiesIds.add(2l);
		storiesIds.add(3l);
		
		userStoryDaoMock.deleteKeys(storiesKeys);
		replay(userStoryDaoMock);
		
		userStoryService.delete(storiesIds);
		
		verify(userStoryDaoMock);
		
	}
	
	
	
	
}