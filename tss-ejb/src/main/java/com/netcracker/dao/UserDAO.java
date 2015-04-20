package com.netcracker.dao;

public class UserDAO extends GenericDAO<User> {
	
	public UserDAO() {
		super();
	}
	
	public void update(User user) {
		User tmp = get(user.getUsername());
		em.getTransaction().begin();
		tmp.setEmail(user.getEmail());
		tmp.setPassword(user.getPassword());
		em.getTransaction().commit();
	}

}
