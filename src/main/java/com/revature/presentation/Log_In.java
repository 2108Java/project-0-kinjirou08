package com.revature.presentation;

import java.util.Scanner;

import com.revature.models.Items;
import com.revature.security.auth_validate;


public class Log_In implements Logging_In {
	
	auth_validate validate;
	
	public Log_In (auth_validate validate) {
		this.validate = validate;
	}
	
	public void display() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to our ABC Bank, please log in...");
		
		String user = sc.nextLine();
		
		//Items authenticate = new Items(user);
		
		validate.auth(user);
	
	
	
}

}