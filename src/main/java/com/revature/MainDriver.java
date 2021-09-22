package com.revature;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.revature.presentation.LogIn;
import com.revature.repo.BankDB;
import com.revature.repo.BankDBImpl;

import com.revature.security.AuthValidate;
import com.revature.service.BankServiceImpl;

public class MainDriver {
	
	private final static Logger loggy = Logger.getLogger(MainDriver.class);

	public static void main (String[] args) {
		
		//loggy.info("Starting the application");
		
		
		BankDB database = new BankDBImpl();
		
		AuthValidate security = new AuthValidate(database);
		
		BankServiceImpl service = new BankServiceImpl(database);
		
		LogIn menu = new LogIn(security, service);
		
		menu.display();
		
		
		
	}
}
	