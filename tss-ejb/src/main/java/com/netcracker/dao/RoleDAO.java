package com.netcracker.dao;


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
