package com.revature.service;

import com.revature.models.Items;
import com.revature.repo.BankDB;

public class BankServiceImpl implements BankService {
	
	BankDB database2;
	
	public BankServiceImpl (BankDB database) {
		this.database2 = database;
	}

	public boolean registerAccount(Items addItem) {		
		return database2.insertAccount(addItem);
	}

	public Items[] getAccounts() {
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

	public Items[] getUnRegisteredAccounts() {
		// TODO Auto-generated method stub
		return database2.selectUnRegisteredAccounts();
	}

}
