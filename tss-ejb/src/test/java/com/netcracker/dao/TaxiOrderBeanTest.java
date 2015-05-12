package com.netcracker.dao;

import static org.junit.Assert.*;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.netcracker.dao.exceptions.NoSuchEntity;
import com.netcracker.dto.CarDTO;
import com.netcracker.ejb.TariffBean;
import com.netcracker.ejb.TaxiOrderBeanLocal;
import com.netcracker.ejb.UserBeanLocal;
import com.netcracker.entity.Address;
import com.netcracker.entity.Route;
import com.netcracker.entity.Tariff;
import com.netcracker.entity.TaxiOrder;
import com.netcracker.entity.User;
import com.netcracker.entity.helper.DriverCar;
import com.netcracker.exceptions.InvalidEntityException;
import com.netcracker.report.Report;
import com.netcracker.util.BeansLocator;
import com.netcracker.util.GlobalVariables;
import com.netcracker.util.JndiNameBuilder;

@RunWith(Arquillian.class)
public class TaxiOrderBeanTest {

	private String entityLookup = "java:jboss/EntityManagerFactoryTest";

	private TaxiOrderBeanLocal toBean;

	@Before
	public void initialize() throws NamingException {
		JndiNameBuilder.setPrefix("java:app/"
				+ (String) new InitialContext().lookup("java:app/AppName")
				+ "/");
		toBean = BeansLocator.getInstance().getBean(TaxiOrderBeanLocal.class);
		GlobalVariables.entityLookup = entityLookup;
	}

	@After
	public void clean() {
	}

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "taxi_order_bean_test.war")
				.addPackage(TariffBean.class.getPackage())
				.addPackage(TariffDAO.class.getPackage())
				.addPackage(Tariff.class.getPackage())
				.addPackage(DriverCar.class.getPackage())
				.addPackage("com.google.common.base")
				.addPackage(NoSuchEntity.class.getPackage())
				.addPackage(BeansLocator.class.getPackage())
				.addPackage(Report.class.getPackage())
				.addPackage(CarDTO.class.getPackage())
				.addClass(JSONObject.class).addClass(JSONException.class)
				.addClass(InvalidEntityException.class)
				.addAsWebInfResource("ejb-jar.xml")
				.addAsResource("persistence.xml", "META-INF/persistence.xml");
	}

	@Test(expected = EJBException.class)
	public void testAddNullUser() {
		toBean.addTaxiOrder(null, new Route(), new Address(), new Address(),
				new TaxiOrder());
	}

	@Test(expected = EJBException.class)
	public void testAddNullRoute() {
		toBean.addTaxiOrder(new User(), null, new Address(), new Address(),
				new TaxiOrder());
	}

	@Test(expected = EJBException.class)
	public void testAddNullTaxiOrder() {
		toBean.addTaxiOrder(new User(), new Route(), new Address(),
				new Address(), null);
	}

	@Test(expected = EJBException.class)
	public void testCreateNullContact() {
		toBean.createContacts(null);
	}

}
