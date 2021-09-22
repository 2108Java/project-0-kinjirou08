package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import com.revature.models.Items;
import com.revature.presentation.LogIn;
import com.revature.repo.BankDB;
import com.revature.repo.BankDBImpl;
import com.revature.security.AuthValidate;

public class UnitTesting {
	
	
	public BankDB database;
	
	public LogIn menu;
	
	public AuthValidate security;
	
	public BankServiceImpl service;
	
	public String user;
	
	public String password;
	
	public Items items = new Items ();
	
	public List<Items> customerAccount = new ArrayList<>();
	
	
	@Before
	public void setupLogin () {
		
		database = new BankDBImpl();
		
		security = new AuthValidate(database);
		
		service = new BankServiceImpl(database);
		
		menu = new LogIn(security, service);	
		
	}
	
	
	@Test
	public void testUsername () {
		
		assertEquals(true, security.auth("employee"));
	}
	
	@Test
	public void testUsernameAndPassword () {
		
		assertEquals(true, security.validate("employee", "employee"));
		
	}
	
	@Test
	public void testCompleteRegistration() {
		
		assertTrue(service.completeARegistration(1));
		
		// id in database starts at 1
		assertEquals(1, service.completeARegistration(0));
		
		
	}
	
	@Test 
	public void testRejectRegistration() {
		
		assertTrue(service.completeARegistration(1));
		
		// id in database starts at 1
		assertEquals(1, service.completeARegistration(0));
		
	}
	
	@Test 
	public void testViewCustomerAccount() {
		
			
		assertEquals(customerAccount, null);
		
		assertEquals(customerAccount, service.getAccounts());
				
	}
	
//	@Test
//	public void testRegisterLogin () throws PSQLException {
//		
//		//items = new Items ("example", "example",false);
//		
//		assertTrue(service.registerLogin(items));
//		
//	}
	@Test
	public void testCheckApproved () {
		
		
		assertTrue(security.checkStatus("example", "example"));
		
		assertEquals(true, security.checkStatus("kinjirou", "password"));
	}
	
}