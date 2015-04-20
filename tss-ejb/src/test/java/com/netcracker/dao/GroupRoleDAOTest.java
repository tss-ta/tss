package com.netcracker.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GroupRoleDAOTest {

	@Test
	public void testPersist() {
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
