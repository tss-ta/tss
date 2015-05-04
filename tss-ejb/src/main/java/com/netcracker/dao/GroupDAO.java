package com.netcracker.dao;

import com.netcracker.entity.Group;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stanislav Zabielin
 */
public class GroupDAO extends GenericDAO<Group> {

    public GroupDAO() {
        super();
    }

    public List<Group> findPage(int pageNumber, int paginationStep) {
        TypedQuery<Group> query = em.createNamedQuery("Group.findAllOrderedByName", Group.class);
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }

    /**
     *
     * @param name - name
     * @return Group
     * @throws NoResultException - if group with this name doesn't exist
     */
    public Group findByName(String name) {
        TypedQuery<Group> query = em.createNamedQuery("Group.findByName", Group.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    /**
     *
     * @param partOfName - part of group name or all name
     * @param pageNumber
     * @param paginationStep
     * @return List of Group which name contains partOfName string
     * @throws NoResultException - if group with this name doesn't exist
     */
    public List<Group> searchByName(String partOfName, int pageNumber, int paginationStep) {
        TypedQuery<Group> query = em.createNamedQuery("Group.searchByName", Group.class);
        query.setParameter("name", "%" + partOfName + "%");
        query.setFirstResult((pageNumber - 1) * paginationStep);
        query.setMaxResults(paginationStep);
        return query.getResultList();
    }
}
