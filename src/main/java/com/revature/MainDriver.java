package com.revature;

import com.revature.presentation.Log_In;
import com.revature.repo.BankDB;
import com.revature.repo.BankDBImpl;

import com.revature.security.auth_validate;
import com.revature.service.BankServiceImpl;

public class MainDriver {

	public static void main (String[] args) {
		
		BankDB database = new BankDBImpl();
		
		auth_validate security = new auth_validate(database);
		
		BankServiceImpl service = new BankServiceImpl(database);
		
		Log_In menu = new Log_In(security, service);
		
		menu.display();
		
	}
}
	