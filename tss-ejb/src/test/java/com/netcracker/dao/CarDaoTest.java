package com.netcracker.dao;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.ejb.TariffBean;
import com.netcracker.entity.Car;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.helper.DriverCar;

@RunWith(Arquillian.class)
public class CarDaoTest {

	private String entityLookup = "java:jboss/EntityManagerFactoryTest";

	private CarDao carDao;
	private UserTransaction transaction;

	private Car testCar = new Car("AAA-111-AAA", true, 1, true, true, true);
	private Car testCarMarkTwo = new Car("BBB-222-BBB", true, 2, false, false,
			false);

	@Before
	public void initialize() {
		persist(testCar);
		persist(testCarMarkTwo);
	}

	@After
	public void clean() {
		delete(testCar);
		delete(testCarMarkTwo);
		if (carDao != null)
			carDao.close();
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
	public void testSearchByLicPlate() {
		assertEquals(testCar, carDao.findByLicPlate(testCar.getLicPlate()));
		assertEquals(testCarMarkTwo, carDao.findByLicPlate(testCarMarkTwo.getLicPlate()));
	}
	
	@Test
	public void testCountCars(){
		assertEquals((Long) 0L, carDao.countSearchedByLicPlateResults(testCar.getLicPlate()+testCarMarkTwo.getLicPlate()));
		assertEquals((Long) 1L, carDao.countSearchedByLicPlateResults(testCarMarkTwo.getLicPlate()));
	}

	private void update(Car car) {
		try {
			carDao = new CarDao();
			carDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			carDao.em.joinTransaction();
			carDao.update(car);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	private void persist(Car car) {
		try {
			carDao = new CarDao();
			carDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			carDao.em.joinTransaction();
			carDao.persist(car);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	private void delete(Car car) {
		try {
			carDao = new CarDao();
			carDao.updateEntityManager(entityLookup);
			transaction = (UserTransaction) new InitialContext()
					.lookup("java:comp/UserTransaction");
			transaction.begin();
			carDao.em.joinTransaction();
			carDao.delete(car);
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException
				| SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

}