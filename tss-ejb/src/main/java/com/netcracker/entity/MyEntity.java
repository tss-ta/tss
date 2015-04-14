package com.netcracker.entity;

import java.io.Serializable;

/**
 *
 * @author Olga Gorbatiuk
 */
public class MyEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;

    public MyEntity(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
