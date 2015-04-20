package com.netcracker.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

	public List<T> getAll() {
		Query q = em.createQuery("select u from entityClass u");
		List<T> userList = q.getResultList();
		return userList;
	}

	public void delete(T t) {
		t = em.merge(t);
		em.remove(t);
	}

	public void persist(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public void close() {
		em.close();
	}

	public EntityManager createEntityManager() {
		return Persistence.createEntityManagerFactory("entityManager")
				.createEntityManager();
	}

}
