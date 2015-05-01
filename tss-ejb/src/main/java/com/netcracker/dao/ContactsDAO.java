package com.netcracker.dao;

import javax.persistence.TypedQuery;

import com.netcracker.entity.Contacts;

/**
 *
 * @author Stanislav Zabielin
 *
 */
public class ContactsDAO extends GenericDAO<Contacts> {

	public ContactsDAO() {
	}

	public Contacts getByEmail(String email) {
		TypedQuery<Contacts> query = em.createNamedQuery(
				"Contacts.findByEmail", Contacts.class);
		query.setParameter("email", email);
		return query.getResultList().get(0);
	}

}
