package com.revature.security;

public interface Security {

	public boolean auth(String user);
	
	public boolean validate(String user, String pass);
	
	public boolean checkStatus(String user, String pass);
	
	public boolean checkApproved(String user, String pass);
	
	public boolean checkExisting(int choice, String bankAccount);
	
	public boolean checkBankAccount(String bankAccount, String user, int choose);
	
	
	
}
