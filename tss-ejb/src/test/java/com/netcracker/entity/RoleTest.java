package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class RoleTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Role.class).verify();
    }
}
