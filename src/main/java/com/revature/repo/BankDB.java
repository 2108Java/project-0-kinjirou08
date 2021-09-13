package com.revature.repo;

import com.revature.models.Items;

public interface BankDB {

	public boolean authenticate(String user);

	public boolean validate(String user, String pass);

	public boolean insertAccount(Items addItem);

	public boolean selectStatus(String user, String pass);

	public boolean checkApproved(String user, String pass);


}
