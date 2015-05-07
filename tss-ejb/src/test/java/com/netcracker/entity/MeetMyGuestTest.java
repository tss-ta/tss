package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class MeetMyGuestTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MeetMyGuest.class)
                .withPrefabValues(Service.class,
                        new Service(1, "MyService"),
                        new Service(2, "AnotherService"))
                .verify();
    }
}
