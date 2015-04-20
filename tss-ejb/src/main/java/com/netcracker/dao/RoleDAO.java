package com.netcracker.dao;

public class RoleDAO extends GenericDAO<Role> {

	
	public RoleDAO() {
		super();
	}
	
	public void update(Role role) {
		Role tmp = get(role.getRolename());
		em.getTransaction().begin();
		tmp.setRolename(role.getRolename());
		em.getTransaction().commit();
	}
}
