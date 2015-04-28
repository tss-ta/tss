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
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public List<User> getByRolename(String rolename, int pageNumber, int paginationStep) {
//        TypedQuery<User> query = em.createNamedQuery("User.findByRole", User.class);
        Query query = em.createNativeQuery("select u.id, u.email, u.username from tss_user u join tss_user_role on u.id=user_id join tss_role r on r.id=role_id where rolename = 'ADMIN'", User.class);
//        query.setParameter("rolename", rolename);
//        query.setParameter("rolename2", rolename);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public boolean isOpen() {
        return em.isOpen();
    }
}
