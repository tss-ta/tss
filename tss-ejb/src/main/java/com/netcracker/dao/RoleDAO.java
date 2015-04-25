package com.netcracker.dao;

import com.netcracker.entity.Role;
import javax.persistence.TypedQuery;


/**
*
* @author Stanislav Zabielin
*
*/

public class RoleDAO extends GenericDAO<Role> {

	
	public RoleDAO() {
		super();
	}
        
        public Role findByRolename(String name) {
        TypedQuery<Role> query = em.createNamedQuery("findByRolename", Role.class);
        return query.getSingleResult();
    }
}
