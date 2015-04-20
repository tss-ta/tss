package com.netcracker.dao;

/**
*
* @author Stanislav Zabielin
*/
public class GroupRoleDAO extends GenericDAO<GroupRole> {
	
	public GroupRoleDAO() {
		super();
	}

	@Override
	public void update(GroupRole t) {
		GroupRoleId gri = new GroupRoleId(t.getRolename(),t.getGroup_id());
		GroupRole tmp = em.find(entityClass, gri);
		em.getTransaction().begin();
		tmp.setGroup_id(t.getGroup_id());
		tmp.setRolename(t.getRolename());
		em.getTransaction().commit();
	}
	
	public GroupRole get(GroupRoleId gri) {
		return em.find(entityClass, gri);
	}

}
