package com.netcracker.entity.helper;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class DriverCarTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(DriverCar.class).verify();
    }
}
