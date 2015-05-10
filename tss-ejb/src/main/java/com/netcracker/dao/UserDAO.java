package com.netcracker.dao;

import com.netcracker.entity.User;
import javax.persistence.NoResultException;

import javax.persistence.Query;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stanislav Zabielin
 * @author maks
 * 
*/
public class UserDAO extends GenericDAO<User> {

//    public UserDAO() {
//        super();
//    }

    /**
     *
     * @param email
     * @return
     * @throws NoResultException - if user with this email doesn't exist
     */
    public User getByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email.toLowerCase());
        return query.getSingleResult();
    }

    public List<User> getByRolename(String rolename, int pageNumber, int paginationStep) {
        Query query = em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.rolename = :rolename ORDER BY u.email", User.class);
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
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email like :email ORDER BY u.email", User.class);
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public List<User> searchByEmailAndRolename(String email, String rolename, int pageNumber, int paginationStep) {
        Query query = em.createQuery(
                "SELECT u FROM User u JOIN u.roles r WHERE (r.rolename = :rolename) and (u.email like :email) ORDER BY u.email", User.class);
        query.setParameter("rolename", rolename);
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    public int countByRolename(String rolename) {
        Query query = em.createQuery("SELECT COUNT(u) FROM User u JOIN u.roles ur JOIN u.groups g JOIN g.roles gr WHERE (ur.rolename = :userRolename) OR (gr.rolename = :groupRolename)");
//        Query query = em.createQuery("SELECT COUNT(u.id) FROM User u");
        query.setParameter("userRolename", rolename);
        query.setParameter("groupRolename", rolename);
//        query.setParameter("rolename", rolename);
        return ((Long) query.getSingleResult()).intValue();

    }

    public int countByUserRoleName(String rolename) {
        Query query = em.createQuery("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE (r.rolename = :rolename)");
        query.setParameter("rolename", rolename);
        return ((Long) query.getSingleResult()).intValue();

    }
    public int countByEmailAndRolename(String email, String rolename) {
        Query query = em.createQuery("SELECT count(u) FROM User u JOIN u.roles r WHERE (r.rolename = :rolename) and (u.email like :email)");
        query.setParameter("rolename", rolename);
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        return ((Long) query.getSingleResult()).intValue();
    }

    public int countByEmail(String email) {
        Query query = em.createQuery("SELECT count(u) FROM User u WHERE (u.email like :email)");
        query.setParameter("email", "%" + email.toLowerCase() + "%");
        return ((Long) query.getSingleResult()).intValue();
    }

    public boolean isOpen() {
        return em.isOpen();
    }
}
