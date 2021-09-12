package com.revature.presentation;

import java.util.Scanner;

import com.revature.models.Items;
import com.revature.security.auth_validate;
import com.revature.service.BankService;
import com.revature.service.BankServiceImpl;

public class Log_In implements Logging_In {
	
	auth_validate security;
	
	BankServiceImpl service;
	

	public Log_In(auth_validate security, BankServiceImpl service) {
		this.security = security;
		this.service = service;
	}



	public static void optionMenu() {
		
		System.out.println("1) Register for an account");
		System.out.println("2) Log in");
	}
	
	public  void registerAccount() {
		
		Scanner sc1 = new Scanner(System.in);	
		System.out.println("Please enter a username: ");
		String cUser = sc1.nextLine();
				
		System.out.println("Please enter a password: ");
		String cPassword = sc1.nextLine();
//				
//				System.out.println("Please enter an amount for the savings account");
//				String checkings = sc1.nextLine();
//				
//				System.out.println("Please enter an amount for the checkings account");
//				String savings = sc1.nextLine();
//				
		Items addItem = new Items(cUser, cPassword, false);
				
		if (service.registerAccount(addItem)) {
			System.out.println("Success");
		}
	}
	
	
	public void display() {
		
		Scanner sc = new Scanner(System.in);
		boolean result = true;
		String choose = "";
		
		
		
		
		while (result) {
			
			System.out.println("Welcome to ABC Bank, what would you like to do?");
			optionMenu();
			choose = sc.nextLine();
			
			switch (choose) {
				case "1":
					registerAccount();									
					break;
				case "2":
					System.out.print("Enter username: ");
					String user = sc.nextLine();
					
						if (security.auth(user)) {
							System.out.print("Hello, " + user + ", What is your password? ");
							String pass = sc.nextLine();
								if (security.validate(user, pass)) {
									System.out.println("Success");
									result = false;
								}else {
									System.out.println("Invalid password");
								}			
						}else {
							System.out.println("You don't have access to our bank!");
						}
					break;
			}		
		}
	}
		
	
	
	
}


