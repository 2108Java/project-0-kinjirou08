package com.revature.security;

import com.revature.models.Items;
import com.revature.repo.BankDB;

public class auth_validate implements Security {
	
	BankDB database;
	
	public auth_validate (BankDB database) {
		this.database = database;
	}

	@Override
	public boolean auth (String user) {
		return database.authenticate(user);
		
	}

	public boolean validate(String user, String pass) {
		// TODO Auto-generated method stub
		return database.validate(user, pass);
	}

	public boolean registerAccount(Items addItem) {
		// TODO Auto-generated method stub
		return database.insertAccount(addItem);
	}

}
