package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class AddressTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Address.class).verify();
    }
}
