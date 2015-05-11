package com.netcracker.dao.exceptions;

public class NoSuchEntity extends Exception {
	
	@Override
	public String getMessage() {
		return "No Such User Finded";
	}

}
