package com.netcracker.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.Test;

public class GroupRoleDAOTest {


	@Test
	public void testPersist() throws NamingException {
		GroupRoleDAO grDao = new GroupRoleDAO();
		RoleDAO roleDao = new RoleDAO();
		GroupDAO groupDao = new GroupDAO();
		GroupRole gr = new GroupRole("admin", 2);
		grDao.delete(gr);
		grDao.persist(gr);
		assertEquals(gr, grDao.get(new GroupRoleId("admin", 2)));
		roleDao.close();
		groupDao.close();
		grDao.close();
	}

}
