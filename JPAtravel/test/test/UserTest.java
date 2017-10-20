package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Users;

public class UserTest {


	EntityManagerFactory emf = null;
	EntityManager em = null;
	Users u;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("Travel");
		em = emf.createEntityManager();
		u = em.find(Users.class, 3);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
		u = null;
	}

	@Test
	public void smokeTest() {
		assertEquals(true, true);
	}

	@Test
	public void test_User_UserName_mapped() {
		assertEquals("mustafa", u.getUserName());
	}
	
	@Test
	public void test_User_password_mapped() {
		assertEquals("password3", u.getPassword());
	}
}
