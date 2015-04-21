package com.netcracker.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 *
 * @author Stanislav Zabielin
 */
@Embeddable
public class GroupRoleId implements Serializable {
	
	String rolename;

	Integer group_id;

	
	public GroupRoleId() {
	}

	public GroupRoleId(String rolename, int group_id) {
		super();
		this.rolename = rolename;
		this.group_id = group_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + group_id;
		result = prime * result
				+ ((rolename == null) ? 0 : rolename.hashCode());
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
		GroupRoleId other = (GroupRoleId) obj;
		if (group_id != other.group_id)
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}

}
