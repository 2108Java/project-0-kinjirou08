package com.revature.service;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test.*;

import com.revature.models.Items;
import com.revature.presentation.Log_In;
import com.revature.security.auth_validate;

public class UnitTesting {
		
	Log_In login;
	
	auth_validate security;
	
	BankServiceImpl service;
	
	Items items;
	
	@Before
	public void sampleTesting () {
		
	login = new Log_In(security, service);
			
	}

	@Test
	public void checkValidate () {
		
		assertTrue(security.validate(items.getUser(), items.getPassword()));
		
	}
}