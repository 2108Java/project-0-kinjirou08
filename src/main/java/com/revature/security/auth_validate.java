package com.revature.security;

import com.revature.repo.BankDB;

import java.util.Random;

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

	public boolean checkStatus(String user, String pass) {
		// TODO Auto-generated method stub
		return database.selectStatus(user, pass);
	}

	public boolean checkApproved(String user, String pass) {
		// TODO Auto-generated method stub
		return database.checkApproved(user, pass);
	}

	public String randomBankAccount () {
		
		String start = "BA ";
        Random value = new Random();

    //Generate two values to append to 'BE'
	    int r1 = value.nextInt(6);
	    int r2 = value.nextInt(6);
	    start += Integer.toString(r1) + Integer.toString(r2);
	
//	    int count = 0;
	    int n = 0;
	    for(int i =0; i < 6;i++)
	    {
//	        if(count == 4)
//	        {
//	            start += " ";
//	            count =0;
//	        }
//	        else 
	            n = value.nextInt(6);
	            start += Integer.toString(n);
//	            count++;
			
		}
	    return start;
	}

	public boolean checkExisting(int choice, String bankAccount) {
		// TODO Auto-generated method stub
		return database.selectExisitingAccount(choice, bankAccount);
	}

	@Override
	public boolean checkBankAccount(String bankAccount, String user, int choose) {
		// TODO Auto-generated method stub
		return database.selectBankAccount(bankAccount, user, choose);
	}
}
