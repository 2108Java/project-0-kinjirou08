package com.revature;

import com.revature.presentation.LogIn;
import com.revature.repo.BankDB;
import com.revature.repo.BankDBImpl;

import com.revature.security.auth_validate;
import com.revature.service.BankServiceImpl;

public class MainDriver {

	public static void main (String[] args) {
		
		BankDB database = new BankDBImpl();
		
		auth_validate security = new auth_validate(database);
		
		BankServiceImpl service = new BankServiceImpl(database);
		
		LogIn menu = new LogIn(security, service);
		
		menu.display();
		
	}
}
	