package com.netcracker.dao;

import com.netcracker.entity.UserGroup;
import com.netcracker.entity.UserGroupId;

/**
*
* @author Stanislav Zabielin
*/
public class UserGroupDAO extends GenericDAO<UserGroup> {

	@Override
	public void update(UserGroup t) {
		UserGroupId ugi = new UserGroupId(t.getUsername(),t.getGroup_id());
		UserGroup tmp = em.find(entityClass, ugi);
		em.getTransaction().begin();
		tmp.setGroup_id(t.getGroup_id());
		tmp.setUsername(t.getUsername());
		em.getTransaction().commit();
	}
	
	public UserGroup get(UserGroupId ugi) {
		return em.find(entityClass, ugi);
	}

}
