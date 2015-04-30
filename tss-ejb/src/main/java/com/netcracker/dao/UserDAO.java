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
        Query query = em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.rolename = :rolename", User.class);
        query.setParameter("rolename", rolename);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public List<User> getByGroup(String groupName, int pageNumber, int paginationStep) {
        Query query = em.createQuery("SELECT u FROM User u JOIN u.groups g WHERE g.name = :name", User.class);
        query.setParameter("name", groupName);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public List<User> searchByEmail(String email, int pageNumber, int paginationStep) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email like :email", User.class);
        query.setParameter("email", "%" + email + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public List<User> searchByEmailAndRolename(String email, String rolename, int pageNumber, int paginationStep) {
        Query query = em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE (r.rolename = :rolename) and (u.email like :email)", User.class);
        query.setParameter("rolename", rolename);
        query.setParameter("email", "%" + email + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public boolean isOpen() {
        return em.isOpen();
    }
}
