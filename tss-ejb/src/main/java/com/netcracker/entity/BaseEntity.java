package com.netcracker.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Olga Gorbatiuk
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.id);
    }

    // In accordance with 'Effective Java' by Joshua Bloch, 2nd edition, pages 42—44.
    @Override
    public final boolean equals(Object obj) {
        // Performance optimization
        if (this == obj) {
            return true;
        }
        // Null check is unnecessary, because the instanceof operator is specified 
        // to return false if its first operand is null, regardless of what type 
        // appears in the second operand [JLS, 15.20.2].
        if (!(obj instanceof BaseEntity)) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        return Objects.equals(this.id, other.id);
    }

}
