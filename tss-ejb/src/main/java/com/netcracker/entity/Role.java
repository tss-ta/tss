package com.netcracker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Stanislav Zabielin
 */
@Entity
@Table(name = "tss_role")
@NamedQueries({
    @NamedQuery(name = "Role.findAllOrderedByName", query = "SELECT s FROM Role s"),
    @NamedQuery(name = "Role.findByRolename", query = "SELECT s FROM Role s WHERE s.rolename = :rolename")})
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) {
            return false;
        }
        if (rolename != null ? !rolename.equals(role.rolename) : role.rolename != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rolename != null ? rolename.hashCode() : 0);
        return result;
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
