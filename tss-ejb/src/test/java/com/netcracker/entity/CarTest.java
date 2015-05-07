package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class CarTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Car.class).verify();
    }
}
