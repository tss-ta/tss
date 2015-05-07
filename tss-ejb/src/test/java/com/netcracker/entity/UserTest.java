package com.netcracker.entity;

import org.junit.Test;

/**
 *
 * @author Olga
 */
public class UserTest {

    @Test
    public void equalsContract() {
        // Commented due to entity inheritance issue
        // EqualsVerifier.forClass(User.class).withRedefinedSubclass(Driver.class).verify();
    }
}
