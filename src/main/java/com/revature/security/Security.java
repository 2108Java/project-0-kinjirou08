package com.revature.security;

public interface Security {

	public boolean auth(String user);
	
	public boolean validate(String user, String pass);
	
}
