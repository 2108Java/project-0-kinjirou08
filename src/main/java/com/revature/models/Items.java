package com.revature.models;

public class Items {

	private String user;
	private String password;
	
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Items(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public Items(String user) {
		super();
		this.user = user;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
