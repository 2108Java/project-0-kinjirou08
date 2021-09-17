package com.revature.service;

import java.util.List;

import com.revature.models.Items;

public interface BankService {
	
	public boolean registerLogin(Items addItem);
	
	public List<Items> getAccounts();
	
	public List<Items> getUnRegisteredAccounts();
	
	public boolean completeARegistration(int id);
	
	public boolean rejectARegistration(int id);
	
	public List<Items> viewACustomerAccount(int id);
	
	public boolean newAcct(Items savingsAccount);

}
