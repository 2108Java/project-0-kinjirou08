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

}
