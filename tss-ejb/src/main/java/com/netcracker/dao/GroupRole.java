package com.netcracker.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Stanislav Zabielin
 */
@Entity
@Table(name = "group_role")
public class GroupRole {

	public GroupRole() {
	}

	public GroupRole(String rolename, int group_id) {
		super();
		id.rolename = rolename;
		id.group_id = group_id;
	}

	@EmbeddedId
	@NotNull
	GroupRoleId id = new GroupRoleId();

	public Integer getGroup_id() {
		return id.group_id;
	}

	public void setGroup_id(Integer group_id) {
		id.group_id = group_id;
	}

	public String getRolename() {
		return id.rolename;
	}

	public void setRolename(String rolename) {
		id.rolename = rolename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupRole other = (GroupRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
