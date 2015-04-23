package com.netcracker.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Stanislav Zabielin
*
*/

@Entity
@Table(name = "tss_group")
public class Group {

	@Id
	private Integer id;

	@Column(name="name",  columnDefinition="bpchar")
	private String name;

    @OneToMany
    @JoinTable(
            name="tss_group_role",
            joinColumns = @JoinColumn(name="group_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id"))
    private List<Role> roles = new ArrayList<Role>();

    public Group() {}

    public Group(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer groupId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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
