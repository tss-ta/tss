package com.netcracker.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Stanislav Zabielin
 * @author maks
 *
 */

@Transactional
public class GenericDAO<T> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;
    
    private static String entityLookup = "java:jboss/EntityManagerFactory";

    public GenericDAO() {
        em = createEntityManager();   
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
        
    public GenericDAO(Class<T> entityClass) {
        em = createEntityManager();
        this.entityClass = entityClass;
    }
    
    public void setEntityManager(EntityManager em){
    	this.em = em;
    }

    public T get(int id) {
        return em.find(entityClass, id);
    }

    @Deprecated
    public List<T> getAll() {
        Query q = em.createQuery("select u from entityClass u");
        List<T> list = q.getResultList();
        return list;
    }

//    public Long countAll() {
//        Query query = em.createQuery("SELECT COUNT(u) FROM " + entityClass.getSimpleName() + " u");
//        return (Long) query.getSingleResult();
//    }
        
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> getPage(int pageNumber, int pageSize) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Argument 'pageNumber' <= 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Argument 'pageSize' <= 0");
        }

        Query query = em.createQuery("From " + entityClass.getSimpleName());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<T>) query.getResultList();
    }

    public void delete(T entity) {
        validateEntityOnNull(entity);
        entity = em.merge(entity);
        em.remove(entity);
    }

    public void persist(T entity) {
        validateEntityOnNull(entity);
        em.persist(entity);
    }

    public void update(T entity) {
        validateEntityOnNull(entity);
        em.merge(entity);
    }

    public void close() {
        em.close();
    }

    public EntityManager createEntityManager() {
        Context initCtx;
        try {
            initCtx = new InitialContext();
            EntityManagerFactory emf = (EntityManagerFactory) initCtx
                    .lookup(entityLookup);
            em = emf.createEntityManager();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return em;
    }

    private void validateEntityOnNull(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Argument 'entity' is null");
        }
    }

	public String getEntityLookup() {
		return entityLookup;
	}

	public void setEntityLookup(String entityLookup) {
		this.entityLookup = entityLookup;
	}

}
