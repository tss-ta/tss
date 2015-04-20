package com.netcracker.dao;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 *
 * @author Stanislav Zabielin
 */
@Embeddable
public class UserRoleId implements Serializable {

	public UserRoleId() {
	}

	public UserRoleId(String username, String rolename) {
		super();
		this.username = username;
		this.rolename = rolename;
	}

	String username;

	String rolename;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rolename == null) ? 0 : rolename.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		UserRoleId other = (UserRoleId) obj;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
