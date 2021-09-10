package com.revature.service;

import com.revature.models.Items;
import com.revature.repo.BankDB;

public class BankServiceImpl implements BankService {
	
	BankDB database;
	
	public BankServiceImpl (BankDB database) {
		this.database = database;
	}

	public boolean registerAccount(Items addItem) {
		// TODO Auto-generated method stub
		return database.insertAccount(addItem);
	}

}
