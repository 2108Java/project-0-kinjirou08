package com.revature.security;

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

}
