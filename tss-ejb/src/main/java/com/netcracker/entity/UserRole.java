package com.netcracker.entity;

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
@Table(name = "user_role")
public class UserRole {

	public UserRole() {
	}

	public UserRole(String username, String rolename) {
		super();
		id.username = username;
		id.rolename = rolename;
	}

	@EmbeddedId
	@NotNull
	UserRoleId id = new UserRoleId();

	public String getUsername() {
		return id.username;
	}

	public void setUsername(String username) {
		id.username = username;
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
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
