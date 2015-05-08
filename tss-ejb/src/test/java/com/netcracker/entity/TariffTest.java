package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class TariffTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Tariff.class).verify();
    }
}
