package com.netcracker.dao;

import com.netcracker.entity.User;
import javax.persistence.NoResultException;

import javax.persistence.Query;
import java.util.List;

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
     * @throws - if user with this email doesn't exist
     */

    public User getByEmail(String email) {
        Query query = em.createQuery("Select u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        User user = null;
            user = (User) query.getSingleResult();
        return user;
    }
}
