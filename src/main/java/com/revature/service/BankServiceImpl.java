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

	public boolean newAcct(Items newBankAccount) {
		
		return database2.insertNewSavings(newBankAccount);
	}

	public boolean newAcct2(Items newBankAccount) {
		
		return database2.insertNewCheckings(newBankAccount);
	}

	public double getMoney(int choice, String getUser) {
		// TODO Auto-generated method stub
		return database2.selectCurrentBalance(choice, getUser);
	}

	public boolean addMoney(int choice, double newBalance, String getUser) {
		// TODO Auto-generated method stub
		return database2.updateMoney(choice, newBalance, getUser);
	}

	public boolean deductMoney(int choice, double newBalance, String getUser) {
		// TODO Auto-generated method stub
		return database2.updateBalance(choice, newBalance, getUser);
	}

	

}
