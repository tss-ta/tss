package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olga Gorbatiuk
 */
public class MyEntityTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MyEntity.class).verify();
    }

}
