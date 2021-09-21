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
	
	public boolean insertNewSavings(Items newBankAccount);
	
	public boolean insertNewCheckings(Items newBankAccount);

	public boolean selectExisitingAccount(int choice, String bankAccount);

	boolean updateMoney(int choice, double newBalance, String getUser); // adding a new balance

	public double selectCurrentBalance(int choice, String getUser);

	public boolean updateBalance(int choice, double newBalance, String getUser); // deducting to new balance

	public boolean selectBankAccount(String bankAccount, String user, int choose);

	public boolean selectAccount(String transferUser);

	public boolean updateTransferMoney(int choice, double newBalance, String transferUser);

	public double selectTransferBalance(int choice, String transferUser);

	public List<Items> getSavingsAccount(int choose, String user);

	
}
