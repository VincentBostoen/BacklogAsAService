package com.baas.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import com.baas.server.dao.BacklogDao;
import com.baas.shared.core.Backlog;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class BacklogDaoTest extends TestCase {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	private BacklogDao backlogDao;

	public BacklogDaoTest() {
		backlogDao = new BacklogDao();
	}

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	public void testGet() throws EntityNotFoundException {
		Backlog backlog = new Backlog("Test");

		Objectify ofy = ObjectifyService.begin();
		Key<Backlog> insertedBacklog = ofy.put(backlog);

		Backlog gettedBacklog = backlogDao.get(insertedBacklog.getId());

		assertEquals(backlog, gettedBacklog);
	}

	public void testSaveOrUpdate() {
		Backlog backlog = new Backlog("Nouveau backlog");

		Key<Backlog> backlogKey = backlogDao.put(backlog);

		Objectify ofy = ObjectifyService.begin();
		Backlog gettedBacklog = ofy.get(backlogKey);

		assertEquals(backlog, gettedBacklog);
	}

	public void testList() {
		Backlog backlog1 = new Backlog("Nouveau backlog 1");
		Backlog backlog2 = new Backlog("Nouveau backlog 2");
		Backlog backlog3 = new Backlog("Nouveau backlog 3");
		Backlog backlog4 = new Backlog("Nouveau backlog 4");
		backlogDao.put(backlog1);
		backlogDao.put(backlog2);
		backlogDao.put(backlog3);
		backlogDao.put(backlog4);
		
		List<Backlog> list = backlogDao.list();

		assertEquals(4, list.size());
		assertTrue(list.contains(backlog1));
		assertTrue(list.contains(backlog2));
		assertTrue(list.contains(backlog3));
		assertTrue(list.contains(backlog4));
	}
}
