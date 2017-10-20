package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Destination;

public class TestDestination {

	EntityManagerFactory emf = null;
	EntityManager em = null;
	Destination des;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("Travel");
		em = emf.createEntityManager();
		des = em.find(Destination.class, 1);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
		des = null;
	}

	@Test
	public void smokeTest() {
		assertEquals(true, true);
	}

	@Test
	public void test_Destination_name_mapped() {
		assertEquals("Denver", des.getName());
	}
	
	@Test
	public void test_Destination_imgUrl_mapped() {
		assertNull(des.getImgUrl());
	}
	
}
