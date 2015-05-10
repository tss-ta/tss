package com.netcracker.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 *
 * @author Stanislav Zabielin
 */
@Entity
@Table(name = "tss_role")
@NamedQueries({
    @NamedQuery(name = "Role.findAllOrderedByName", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRolename", query = "SELECT r FROM Role r WHERE r.rolename = :rolename")})
public class Role implements Serializable {

    @Id
    private Integer id;

    @Column(name = "rolename")
    private String rolename;

    public Role() {
    }

    public Role(String rolename) {
        super();
        this.rolename = rolename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(rolename);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Role)) {
            return false;
        }

        Role other = (Role) obj;
        return Objects.equals(this.rolename, other.rolename);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", rolename='").append(rolename).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
