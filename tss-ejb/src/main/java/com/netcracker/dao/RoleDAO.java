package com.netcracker.dao;

import com.netcracker.entity.Role;


/**
*
* @author Stanislav Zabielin
*/
public class RoleDAO extends GenericDAO<Role> {

	
	public RoleDAO() {
		super();
	}
	
	@Override
	public void update(Role role) {
		Role tmp = get(role.getRolename());
		em.getTransaction().begin();
		tmp.setRolename(role.getRolename());
		em.getTransaction().commit();
	}
}
