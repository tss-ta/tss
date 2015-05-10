package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class ContactsTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Contacts.class).verify();
    }
}
