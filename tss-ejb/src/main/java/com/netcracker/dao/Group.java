package com.netcracker.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*
* @author Stanislav Zabielin
*/
@Entity
@Table(name = "tss_group")
public class Group {
	
	public Group() {
	}
	
	public Group(int groupId, String name) {
		super();
		this.groupId = groupId;
		this.name = name;
	}

	@Id
	@NotNull
	@Column(name="group_id")
	private Integer groupId;
	
	@NotNull
	@Column(name="name",  columnDefinition="bpchar")
	private String name;

	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Group other = (Group) obj;
		if (groupId != other.groupId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
