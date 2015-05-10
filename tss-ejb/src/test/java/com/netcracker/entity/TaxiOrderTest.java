package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class TaxiOrderTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(TaxiOrder.class).verify();
    }
}
