package com.revature;

import com.revature.presentation.Log_In;
import com.revature.repo.BankDB;
import com.revature.repo.BankDBImpl;

import com.revature.security.auth_validate;

public class MainDriver {

	public static void main (String[] args) {
		
		BankDB database = new BankDBImpl();
		
		auth_validate security = new auth_validate(database);
		
		Log_In menu = new Log_In(security);
		
		menu.display();
		
	}
}
