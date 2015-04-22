package com.netcracker.dao;

import com.netcracker.entity.User;

import javax.persistence.Query;


/**
*
* @author Stanislav Zabielin
*
*/

public class UserDAO extends GenericDAO<User> {
	
	public UserDAO() {
		super();
	}

    public User getByEmail(String email) {
        Query query = em.createQuery("Select u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        return  (User)query.getSingleResult();
    }
}
