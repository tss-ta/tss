package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class RouteTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Route.class).verify();
    }
}
