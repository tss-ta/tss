package com.netcracker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Stanislav Zabielin
 * 
*/
@Entity
@Table(name = "tss_user")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "User.findAllOrderedByUsername", query = "SELECT u FROM User u ORDER BY u.username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Size(min = 1, max = 60)
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    @Size(min = 1, max = 60)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Incorrect email address")
    private String email;

    @Size(min = 1, max = 60)
    @Column(name = "pass_hash")
    private String passwordHash;

    @OneToMany
    @JoinTable(
            name = "tss_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<Role>();

    @OneToMany
    @JoinTable(
            name = "tss_user_group",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private List<Group> groups = new ArrayList<Group>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "personal_address",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "addr_id", referencedColumnName = "addr_id"))
    private List<Address> addresses = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String passwordHash) {
        super();
        this.username = username;
        this.email = email.toLowerCase();
        this.passwordHash = passwordHash;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email.toLowerCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, passwordHash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.passwordHash, other.passwordHash);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", passwordHash='").append(passwordHash).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", groups=").append(groups);
        sb.append('}');
        return sb.toString();
    }
}
