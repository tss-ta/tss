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
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.netcracker.ejb.RegistrationBean;
import com.netcracker.ejb.TariffBean;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.DriverCar;

@RunWith(Arquillian.class)
public class GreeterTest {

	private String entityLookup = "java:jboss/EntityManagerFactoryTest";

	@Before
	public void initialize() {
		new UserDAO().setEntityLookup(entityLookup);
	}

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(TariffBean.class.getPackage())
				.addPackage(TariffDAO.class.getPackage())
				.addPackage(Tariff.class.getPackage())
				.addPackage(DriverCar.class.getPackage())
				.addPackage("com.google.common.base")
				.addAsResource("persistence.xml", "META-INF/persistence.xml");
	}

	@Test
	public void should_create_greeting() {
		Assert.assertEquals("Hello, Earthling!", "Hello, Earthling!");
	}

	@Test
	public void testPersist() {
		UserDAO userDao = new UserDAO();
		User user = new User("Tom", "tom@aol.com", "tompass");
		UserTransaction transaction;
		try {
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			userDao.em.joinTransaction();
			userDao.persist(user);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user.getEmail(), userDao.getByEmail("tom@aol.com")
				.getEmail());
		userDao.delete(userDao.getByEmail("tom@aol.com"));
		userDao.close();
	}
}