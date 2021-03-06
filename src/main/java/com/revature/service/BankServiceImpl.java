package com.revature.service;

import java.util.List;

import com.revature.models.Items;
import com.revature.repo.BankDB;

public class BankServiceImpl implements BankService {
	
	BankDB database2;
	
	public BankServiceImpl (BankDB database) {
		this.database2 = database;
	}

	public boolean registerLogin(Items addItem) {		
		return database2.insertLogin(addItem);
	}

	public List<Items> getAccounts() {
		// TODO Auto-generated method stub
		return database2.selectAllAccounts();
	}

	public boolean completeARegistration(int id) {
		// TODO Auto-generated method stub
		return database2.upadteARegistration(id);
	}

	public boolean rejectARegistration(int id) {
		// TODO Auto-generated method stub
		return database2.deleteARegistration(id);
	}

	public List<Items> getUnRegisteredAccounts() {
		// TODO Auto-generated method stub
		return database2.selectUnRegisteredAccounts();
	}

	public List<Items> viewACustomerAccount(int id) {
		// TODO Auto-generated method stub
		return database2.selectACustomerAccount(id);
	}

	public boolean newAcct(int choose, Items newBankAccount) {
		
		return database2.insertNewBankAccounts(choose, newBankAccount);
	}

//	public boolean newAcct2(Items newBankAccount) {
//		
//		return database2.insertNewCheckings(newBankAccount);
//	}

	public double getMoney(int choice, String getUser) {
		// TODO Auto-generated method stub
		return database2.selectCurrentBalance(choice, getUser);
	}
	
	public double getTransferMoney (int choice, String transferUser) {
		
		return database2.selectTransferBalance(choice, transferUser);
	}
	
	public boolean addMoney(int choice, double newBalance, String getUser) {
		// TODO Auto-generated method stub
		return database2.updateMoney(choice, newBalance, getUser);
	}

	public boolean deductMoney(int choice, double newBalance, String getUser) {
		// TODO Auto-generated method stub
		return database2.updateBalance(choice, newBalance, getUser);
	}

	public boolean transferMoney(int choice, double newBalance, String transferUser) {
		
		return database2.updateTransferMoney(choice, newBalance, transferUser);
	}

	public List<Items> checkSavingsAccount(int choose, String user) {
		
		return database2.getSavingsAccount(choose,user);
	}

	public boolean setJointAccount(String bankAccount, String getUser) {
		
		return database2.updateJointAccount(bankAccount, getUser);
	}

	public boolean createJointAccount(String bankAccount, String getUser, String getUser2, double amount) {
		
		return database2.insertNewJointAccount(bankAccount, getUser, getUser2, amount);
		
	}

	
}
