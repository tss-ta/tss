package com.netcracker.dao;

import com.netcracker.entity.Role;
import com.netcracker.entity.User;
import javax.persistence.NoResultException;

import javax.persistence.Query;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stanislav Zabielin
 * 
*/
public class UserDAO extends GenericDAO<User> {

    public UserDAO() {
        super();
    }

    /**
     *
     * @param email
     * @return
     * @throws NoResultException - if user with this email doesn't exist
     */

    public User getByEmail(String email) {
        TypedQuery <User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

//    public User getByRole(Role role) {
////        TypedQuery <User> = em.
//    }

    public boolean isOpen() {
        return em.isOpen();
    }
}
