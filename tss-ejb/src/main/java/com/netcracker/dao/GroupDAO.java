package com.netcracker.dao;

/**
*
* @author Stanislav Zabielin
*/
public class GroupDAO extends GenericDAO<Group> {
	
	public GroupDAO() {
		super();
	}

	@Override
	public void update(Group group) {
		Group tmp = get(group.getGroupId());
		em.getTransaction().begin();
		tmp.setName(group.getName());
		em.getTransaction().commit();
	}

}
