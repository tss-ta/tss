package com.netcracker.dao;

import com.netcracker.entity.Role;
import javax.persistence.NoResultException;
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

    /**
     *
     * @param name
     * @return Role
     * @throws NoResultException - if role with this name doesn't exist
     */
    public Role findByRolename(String name) {
            TypedQuery<Role> query = em.createNamedQuery("Role.findByRolename", Role.class);
            query.setParameter("rolename", name);
            return query.getSingleResult();
    }
}
