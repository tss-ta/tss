package com.netcracker.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Stanislav Zabielin
 * 
*/
@Entity
@Table(name = "tss_group")
@NamedQueries({
    @NamedQuery(name = "Group.findAllOrderedByName", query = "SELECT g FROM Group g ORDER BY g.name"),
    @NamedQuery(name = "Group.findByName", query = "SELECT g FROM Group g WHERE g.name = :name"),
    @NamedQuery(name = "Group.searchByName", query = "SELECT g FROM Group g WHERE g.name like :name")})
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "bpchar")
    private String name;

    @OneToMany
    @JoinTable(
            name = "tss_group_role",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<Role>();

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }

        Group other = (Group) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Group{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
