package com.perso.baas.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.perso.baas.PojoHelper;
import com.perso.baas.shared.Backlog;
import com.perso.baas.shared.UserStory;

public class UserStoryDaoTest extends TestCase{

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private UserStoryDao userStoryDao;
	private Objectify ofy;

	public UserStoryDaoTest() {
		userStoryDao = new UserStoryDao();
		ofy = ObjectifyService.begin();
	}

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	public void testCreate(){

		Key<UserStory> key = userStoryDao.put(PojoHelper.userStory());
		
		UserStory gettedStory = ofy.get(key);
		
		assertEquals(1, ofy.query(UserStory.class).list().size());
		assertEquals(PojoHelper.userStory(), gettedStory);
	}
	
	public void testUpdate(){
		Key<UserStory> key = userStoryDao.put(PojoHelper.userStory());
		
		UserStory gettedStory = ofy.get(key);
		
		key = userStoryDao.put(gettedStory);
		
		assertEquals(1, ofy.query(UserStory.class).list().size());
	}
	
	public void testGetStories(){
		
		Backlog backlog = new Backlog("A backlog");
		Key<Backlog> backlogKey = ofy.put(backlog);
		
		UserStory userStory = PojoHelper.userStory();
		userStory.setBacklogId(backlogKey.getId());
		ofy.put(userStory);
		
		UserStory anotherUserStory = PojoHelper.userStory();
		anotherUserStory.setName("An other story");
		anotherUserStory.setBacklogId(backlogKey.getId());
		ofy.put(anotherUserStory);
		
		List<UserStory> stories = userStoryDao.getStories(backlogKey.getId());
		
		assertNotNull(stories);
		assertEquals(2, stories.size());
		assertTrue(stories.contains(userStory));
		assertTrue(stories.contains(anotherUserStory));
	}
	
	public void testGetStoriesNoBacklog(){
		UserStory userStory = PojoHelper.userStory();
		ofy.put(userStory);
		
		List<UserStory> stories = userStoryDao.getStories(12);
		
		assertNotNull(stories);
		assertEquals(0, stories.size());
	}
	
	public void testGetStoriesNoStories(){
		Backlog backlog = new Backlog("A backlog");
		Key<Backlog> backlogKey = ofy.put(backlog);
		
		List<UserStory> stories = userStoryDao.getStories(backlogKey.getId());
		
		assertNotNull(stories);
		assertEquals(0, stories.size());
	}
}
