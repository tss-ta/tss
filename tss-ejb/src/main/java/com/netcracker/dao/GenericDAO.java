package com.netcracker.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Stanislav Zabielin
 */
public abstract class GenericDAO<T> {

	protected Class<T> entityClass;

	protected EntityManager em;

	public GenericDAO() {
		em = createEntityManager();
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T get(String key) {
		return em.find(entityClass, key);
	}

	public T get(int key) {
		return em.find(entityClass, key);
	}

	public List<T> getAll() {
		Query q = em.createQuery("select u from entityClass u");
		List<T> list = q.getResultList();
		return list;
	}

	public void delete(T t) {
		em.getTransaction().begin();
		t = em.merge(t);
		em.remove(t);
		em.getTransaction().commit();
	}

	public void persist(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public abstract void update(T t);

	public void close() {
		em.close();
	}

	public EntityManager createEntityManager() {
		if (em != null && em.isOpen())
			return em;
		else
			return Persistence.createEntityManagerFactory("entityManager")
					.createEntityManager();
	}

}
