package com.revature.repo;

import com.revature.models.Items;

public interface BankDB {

	public boolean authenticate(String user);

	public boolean validate(String user, String pass);

	public boolean insertLogin(Items addItem);

	public boolean selectStatus(String user, String pass);

	public boolean checkApproved(String user, String pass);

	public Items[] selectAllAccounts();

	public boolean upadteARegistration(int id);

	public boolean deleteARegistration(int id);

	public Items[] selectUnRegisteredAccounts();

	public Items[] selectACustomerAccount(int id);
	
	public boolean insertNewAccount(Items savingsAccount);

	public boolean selectExisitingAccount(String newBankAccount);
	
}
