package com.netcracker.dao;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.ejb.RegistrationBean;
import com.netcracker.ejb.TariffBean;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.DriverCar;

@RunWith(Arquillian.class)
public class GenericDAOTest {

	private String entityLookup = "java:jboss/EntityManagerFactoryTest";

	private UserDAO userDao;
	private UserTransaction transaction;

	private User testUser = new User("TestUser", "test@me.now");
	private User testUserMarkTwo = new User("TestUserMarkTwo",
			"test@me.not.now");

	@Before
	public void initialize() {
		persist(testUser);
		persist(testUserMarkTwo);
	}

	@After
	public void clean() {
		delete(testUser);
		delete(testUserMarkTwo);
		if (userDao != null)
			userDao.close();
	}

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "generic_dao_test.war")
				.addPackage(TariffBean.class.getPackage())
				.addPackage(TariffDAO.class.getPackage())
				.addPackage(Tariff.class.getPackage())
				.addPackage(DriverCar.class.getPackage())
				.addPackage("com.google.common.base")
				.addPackage(NoSuchEntity.class.getPackage())
				.addAsResource("persistence.xml", "META-INF/persistence.xml");
	}

	@Test
	public void testGetTestUsers() throws NoSuchEntity {
		assertEquals(testUser, userDao.get(testUser.getId()));
		assertEquals(testUserMarkTwo, userDao.get(testUserMarkTwo.getId()));
	}

	@Test(expected = NoSuchEntity.class)
	public void testGetNoSuchUser() throws NoSuchEntity {
		userDao.get(98765);
	}

	@Test(expected = NoSuchEntity.class)
	public void testDelete() throws NoSuchEntity {
		delete(testUser);
		userDao.get(testUser.getId());
		persist(testUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNull() {
		userDao.delete(null);
	}

	@Test
	public void testUpdate() throws NoSuchEntity {
		testUser.setUsername("updated");
		update(testUser);
		assertEquals(testUser.getUsername(), userDao.get(testUser.getId())
				.getUsername());
		testUser.setEmail("TestUser");
		update(testUser);
	}

	private void update(User user) {
		try {
			userDao = new UserDAO();
			userDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			userDao.em.joinTransaction();
			userDao.update(user);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		} 
	}

	private void persist(User user) {
		try {
			userDao = new UserDAO();
			userDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			userDao.em.joinTransaction();
			userDao.persist(user);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	private void delete(User user) {
		try {
			userDao = new UserDAO();
			userDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			userDao.em.joinTransaction();
			userDao.delete(user);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		} 
	}

}