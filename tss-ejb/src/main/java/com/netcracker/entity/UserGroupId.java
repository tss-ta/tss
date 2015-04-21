package com.netcracker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserGroupId implements Serializable {

	public UserGroupId() {
	}

	public UserGroupId(String username, int group_id) {
		super();
		this.username = username;
		this.group_id = group_id;
	}

	@Column(name = "username", columnDefinition = "bpchar")
	String username;

	Integer group_id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + group_id;
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
		UserGroupId other = (UserGroupId) obj;
		if (group_id != other.group_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
