package com.netcracker.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

/**
 *
 * @author Olga
 */
public class ServiceTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Service.class)
                .withPrefabValues(MeetMyGuest.class,
                        new MeetMyGuest(1, "MyGuest"),
                        new MeetMyGuest(2, "AnotherGuest"))
                .verify();
    }
}
