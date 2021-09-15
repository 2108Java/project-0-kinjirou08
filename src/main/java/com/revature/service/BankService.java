package com.revature.service;

import com.revature.models.Items;

public interface BankService {
	
	public boolean registerLogin(Items addItem);
	
	public Items[] getAccounts();
	
	public Items[] getUnRegisteredAccounts();
	
	public boolean completeARegistration(int id);
	
	public boolean rejectARegistration(int id);
	
	public Items[] viewACustomerAccount(int id);
	
	public boolean newAcct(Items savingsAccount);

}
