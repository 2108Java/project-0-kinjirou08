package com.revature.models;

public class Items {
	
	private int id;
	private String user;
	private String password;
	private String checkings;
	private String savings;
	private String jointAcc;
	private boolean isApproved;
	private double amount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Items(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public Items(String user) {
		super();
		this.user = user;
	}

	public Items(String user, String password, boolean isApproved) {
		super();
		this.user = user;
		this.password = password;
		this.isApproved = isApproved;
		
	}
	
	public Items(int id, String user, boolean isComplete) {
		
		super();
		this.id = id;
		this.user = user;
		this.isApproved = isComplete;
		
	}
	public Items(int id, String user, String checkings, String savings) {
		
		setId(id);
		setUser(user);
		setCheckings(checkings);
		setSavings(savings);
		
	}
	public Items(String newBankAccount, String getUser, double amount) {

		setSavings(newBankAccount);
		setUser(getUser);
		setAmount(amount);
		
	}
	
public Items(String getUser, double amount, String newBankAccount) {
		
		setCheckings(newBankAccount);
		setUser(getUser);
		setAmount(amount);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckings() {
		return checkings;
	}
	public void setCheckings(String checkings) {
		this.checkings = checkings;
	}
	public String getSavings() {
		return savings;
	}
	public void setSavings(String savings) {
		this.savings = savings;
	}
	public String getJointAcc() {
		return jointAcc;
	}
	public void setJointAcc(String jointAcc) {
		this.jointAcc = jointAcc;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
