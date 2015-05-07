package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class CelebrationServiceTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(CelebrationService.class).verify();
    }
}
