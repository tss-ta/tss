package com.netcracker.dao;

public class UserRoleDAO extends GenericDAO<UserRole> {

	@Override
	public void update(UserRole t) {
		UserRoleId gri = new UserRoleId(t.getUsername(),t.getRolename());
		UserRole tmp = em.find(entityClass, gri);
		em.getTransaction().begin();
		tmp.setUsername(t.getUsername());
		tmp.setRolename(t.getRolename());
		em.getTransaction().commit();
	}
	
	public UserRole get(UserRoleId uri) {
		return em.find(entityClass, uri);
	}

}
