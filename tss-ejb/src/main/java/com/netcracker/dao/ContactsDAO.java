package com.netcracker.dao;

import javax.persistence.TypedQuery;

import com.netcracker.entity.Contacts;
import com.netcracker.entity.User;

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
        
    public Contacts getByUser(User user) {
        TypedQuery<Contacts> query = em.createNamedQuery(
                "Contacts.findByUser", Contacts.class);
        query.setParameter("user", user);
        return query.getResultList().get(0);
    }

}
