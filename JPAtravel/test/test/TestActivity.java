package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Activity;
import entity.Destination;

public class TestActivity {

	EntityManagerFactory emf = null;
	EntityManager em = null;
	Activity act;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("Travel");
		em = emf.createEntityManager();
		act = em.find(Activity.class, 1);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
		act = null;
	}

	@Test
	public void smokeTest() {
		assertEquals(true, true);
	}

	@Test
	public void test_Activity_name_mapped() {
		assertEquals("concert", act.getName());
	}
	
	@Test
	public void test_Activity_imgUrl_mapped() {
		assertNull(act.getImgUrl());
	}
	
	@Test
	public void test_Activity_date_mapped() {
		assertEquals("2017-10-20", act.getDate().toString());
	}
	
	@Test
	public void test_Activity_cost_mapped() {
		assertEquals(35.23, act.getCost(), .002);
	}
	
	public void test_Activity_destination_mapped() {
		assertEquals("Denver", act.getDestination().getName());
	}
}
