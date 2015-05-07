package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class GroupTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Group.class).verify();
    }
}
