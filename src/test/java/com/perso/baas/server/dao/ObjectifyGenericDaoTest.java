/**
 * 
 */
package com.perso.baas.server.dao;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.perso.baas.server.dao.dummy.DummyLongDao;
import com.perso.baas.server.dao.dummy.DummyStringDao;
import com.perso.baas.shared.DummyPojoLong;
import com.perso.baas.shared.DummyPojoString;

/**
 * @author user
 *
 */
public class ObjectifyGenericDaoTest {
	

	/**
	 * Register all class which aims to be serialized through this DAO
	 */
	static {
		ObjectifyService.register(DummyPojoLong.class);
		ObjectifyService.register(DummyPojoString.class);
	}
	 
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	
	private Objectify ofy;
	DummyLongDao dao;
	DummyStringDao daoString;
	
	Key<DummyPojoLong> oneKeyDummy, twoKeyDummy, threeKeyDummy;
	DummyPojoLong oneDummy, twoDumy, threeDummy;
	
	Key<DummyPojoString> oneKeyDummyString, twoKeyDummyString, threeKeyDummyString;
	DummyPojoString oneDummyString, twoDummyString, threeDummyString;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		ofy = ObjectifyService.begin();
		dao = new DummyLongDao();
		daoString = new DummyStringDao();
		//prepration test
		oneDummy = new DummyPojoLong(null, "oneDummy", "same description one and two");
		twoDumy = new DummyPojoLong(null, "twoDumy", "same description one and two");
		threeDummy = new DummyPojoLong(null, "threeDummy", "different description three");
		
		oneDummyString = new DummyPojoString("oneDummyString", "oneDummyString", "description");
		twoDummyString = new DummyPojoString("twoDummyString", "twoDummyString", "description");
		threeDummyString = new DummyPojoString("threeDummyString", "threeDummyString", "description");
		
		oneKeyDummy = ofy.put(oneDummy);
		twoKeyDummy = ofy.put(twoDumy);
		threeKeyDummy = ofy.put(threeDummy);
		
		oneKeyDummyString = ofy.put(oneDummyString);
		twoKeyDummyString = ofy.put(twoDummyString);
		threeKeyDummyString = ofy.put(threeDummyString);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#put(java.lang.Object)}.
	 */
	@Test
	public void testPut() {
		
		DummyPojoLong dummyPojo = new DummyPojoLong(null, "name","description");
		Key<DummyPojoLong> key = dao.put(dummyPojo);
		
		DummyPojoLong gettedDummyPojo = ofy.get(key);
		
		Assert.assertEquals(4, ofy.query(DummyPojoLong.class).list().size());
		Assert.assertEquals(dummyPojo, gettedDummyPojo);

	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#putAll(java.lang.Iterable)}.
	 */
	@Test
	public void testPutAll() {
		DummyPojoLong dummyPojoEins = new DummyPojoLong(null, "name1","description");
		DummyPojoLong dummyPojoZwei = new DummyPojoLong(null, "name2","description");
		
		Map<Key<DummyPojoLong>, DummyPojoLong> keys = dao.putAll(Arrays.asList(dummyPojoEins, dummyPojoZwei));
		
		for (Key<DummyPojoLong> keyDummy : keys.keySet()) {
			Assert.assertTrue(dummyPojoEins.equals(ofy.get(keyDummy)) | dummyPojoZwei.equals(ofy.get(keyDummy)));
		}
		
		Assert.assertEquals(5, ofy.query(DummyPojoLong.class).list().size());
		
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#delete(java.lang.Object)}.
	 */
	@Test
	public void testDeleteT() {
		dao.delete(oneDummy);
		
		List<DummyPojoLong> list = ofy.query(DummyPojoLong.class).list();
		Assert.assertEquals(2, list.size());
		Assert.assertEquals(Arrays.asList(twoDumy, threeDummy), list);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#deleteKey(com.googlecode.objectify.Key)}.
	 */
	@Test
	public void testDeleteKey() {
		dao.deleteKey(oneKeyDummy);
		Assert.assertEquals(2, ofy.query(DummyPojoLong.class).list().size());
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#delete(java.lang.String)}.
	 */
	@Test
	public void testDeleteString() {
		daoString.delete("twoDummyString");
		Assert.assertEquals(2, ofy.query(DummyPojoString.class).list().size());
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#deleteAll(java.lang.Iterable)}.
	 */
	@Test
	public void testDeleteAll() {
		dao.deleteAll(Arrays.asList(oneDummy, twoDumy, threeDummy));
		Assert.assertEquals(0, ofy.query(DummyPojoLong.class).list().size());
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#deleteKeys(java.lang.Iterable)}.
	 */
	@Test
	public void testDeleteKeys() {
		dao.deleteKeys(Arrays.asList(oneKeyDummy, twoKeyDummy));
		
		List<DummyPojoLong> list = ofy.query(DummyPojoLong.class).list();
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(Arrays.asList(threeDummy), list);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#get(java.lang.Long)}.
	 * @throws EntityNotFoundException 
	 */
	@Test
	public void testGetLong() throws EntityNotFoundException {
		Assert.assertEquals(oneDummy, dao.get(oneDummy.getKey()));
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#get(java.lang.String)}.
	 * @throws EntityNotFoundException 
	 */
	@Test
	public void testGetString() throws EntityNotFoundException {
		Assert.assertEquals(oneDummyString, daoString.get(oneDummyString.getKey()));
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#get(com.googlecode.objectify.Key)}.
	 * @throws EntityNotFoundException 
	 */
	@Test
	public void testGetKeyOfT() throws EntityNotFoundException {
		Assert.assertEquals(oneDummy, dao.get(oneKeyDummy));
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#getByProperty(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testGetByProperty() {
		DummyPojoLong getByProperty = dao.getByProperty("description", "different description three");
		Assert.assertEquals(threeDummy, getByProperty);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#listByProperty(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testListByProperty() {
		List<DummyPojoLong> listByProperty = dao.listByProperty("description", "different description three");
		Assert.assertEquals(1, listByProperty.size());
		Assert.assertEquals(Arrays.asList(threeDummy), listByProperty);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#listKeysByProperty(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void testListKeysByProperty() {
		List<Key<DummyPojoLong>> listByExample = dao.listKeysByProperty("description", "different description three");
		Assert.assertEquals(1, listByExample.size());
		Assert.assertEquals(Arrays.asList(threeKeyDummy), listByExample);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#getByExample(java.lang.Object)}.
	 */
	@Test
	public void testGetByExample() {
		//one result
		DummyPojoLong getByExample = dao.getByExample(new DummyPojoLong(null, "oneDummy", "same description one and two"));
		Assert.assertEquals(oneDummy, getByExample);
		
		//Exception many results
		try {
			getByExample = dao.getByExample(new DummyPojoLong(null, null, "same description one and two"));
			fail();
		} catch (Exception e) {
			Assert.assertEquals("Too many results", e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#listByExample(java.lang.Object)}.
	 */
	@Test
	public void testListByExample() {
		List<DummyPojoLong> listByExample = dao.listByExample(new DummyPojoLong(null, null, "same description one and two"));
		Assert.assertEquals(2, listByExample.size());
		Assert.assertEquals(Arrays.asList(oneDummy, twoDumy), listByExample);
	}

	/**
	 * Test method for {@link com.perso.baas.server.dao.ObjectifyGenericDao#list()}.
	 */
	@Test
	public void testList() {
		List<DummyPojoLong> list = dao.list();
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(Arrays.asList(oneDummy, twoDumy, threeDummy),list);
	}

}
