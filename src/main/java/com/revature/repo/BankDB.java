package com.revature.repo;

import java.util.List;

import com.revature.models.Items;

public interface BankDB {

	public boolean authenticate(String user);

	public boolean validate(String user, String pass);

	public boolean insertLogin(Items addItem);

	public boolean selectStatus(String user, String pass);

	public boolean checkApproved(String user, String pass);

	public List<Items> selectAllAccounts();

	public boolean upadteARegistration(int id);

	public boolean deleteARegistration(int id);

	public List<Items> selectUnRegisteredAccounts();

	public List<Items> selectACustomerAccount(int id);
	
	public boolean insertNewAccount(Items savingsAccount);

	public List<Items> selectExisitingAccount(String newBankAccount);

	public boolean selectExisitingAccounts(String getUser);

	
}
