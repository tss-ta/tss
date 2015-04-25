package com.netcracker.dao;

import com.netcracker.entity.Group;
import java.util.List;
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
        List<Group> groupPage = query.getResultList();
        return groupPage;
    }
        
    public Group findByName(String name) {
        TypedQuery<Group> query = em.createNamedQuery("Group.findByName", Group.class);
        return query.getSingleResult();
    }
}
