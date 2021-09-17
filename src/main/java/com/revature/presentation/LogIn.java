package com.revature.presentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.models.Items;
import com.revature.security.auth_validate;
import com.revature.service.BankServiceImpl;

public class LogIn implements Logging_In {
	
	Scanner sc = new Scanner(System.in);
	
	public auth_validate security;
	
	public BankServiceImpl service;	


	public LogIn(auth_validate security, BankServiceImpl service) {
		this.security = security;
		this.service = service;
	}

	public static void viewCustomerAccounts(List<Items> allAccounts) {
		
		for (int i = 0; i < allAccounts.size(); i ++) {
				if (allAccounts.get(i) != null) {
					System.out.println("ID: " + allAccounts.get(i).getId());
					System.out.println("Customer's username: " + allAccounts.get(i).getUser());
					
					
				System.out.println("");
				}
		}
		
	}
	
	public static void viewSpecificCustomerAccounts(List<Items> allAccounts) {
		
		for (int i = 0; i < allAccounts.size(); i ++) {
				if (allAccounts.get(i) != null) {
					System.out.println("ID: " + allAccounts.get(i).getId());
					System.out.println("Customer's username: " + allAccounts.get(i).getUser());
					System.out.println("Customer's Checkings Account: " + allAccounts.get(i).getCheckings());
					System.out.println("Customer's Savings Account: " + allAccounts.get(i).getSavings());				
					
				System.out.println("");
				}
		}

	}
	
	public static void displayUnapprovedRegistration (List<Items> allAccounts) {
		
		for (int i = 0; i < allAccounts.size(); i ++) {
			if (allAccounts.get(i) != null) {
				System.out.println("ID: " + allAccounts.get(i).getId());
				System.out.println("Username: " + allAccounts.get(i).getUser());
				
				if ((allAccounts.get(i).isApproved() == false)) {
					System.out.println("Approved? No");
			}
			System.out.println("");
			}
		}
	}
	
	public void customerOptionOneMenu (int choose, String user, boolean correctAmount) {
		
		List <Items> getBankAccount;
		
		String newBankAccount = security.randomBankAccount();
		String getUser = user;
		double amount;
		
		
		
		if (choose == 1) {			
			if (security.selectExisitingAccounts(getUser)) {
				System.out.println("Already have a savings account!");
			} else {
					System.out.print("Enter a starting amount: ");
					amount = Double.parseDouble(sc.nextLine());
					if (amount <= 0) {
				System.out.println("Starting amount cannot be less than 0!");
					} else {
				//Items checkBankAccount = new Items(newBankAccount);
						Items savingsAccount = new Items(newBankAccount,getUser,amount);
							if (service.newAcct(savingsAccount)) {
								System.out.println("You've successfully added a new savings account!");
								correctAmount = false;
							}
					}
			}													
		} else if (choose == 2) {
				if (security.selectExisitingAccounts(getUser)) {
				System.out.println("Already have a checkings account!");
			} else {
					System.out.print("Enter a starting amount: ");
					amount = Double.parseDouble(sc.nextLine());
					if (amount <= 0) {
						System.out.println("Starting amount cannot be less than 0!");
					} else {
				//Items checkBankAccount = new Items(newBankAccount);
						Items checkingsAccount = new Items(newBankAccount,getUser,amount);
							if (service.newAcct(checkingsAccount)) {
								System.out.println("You've successfully added a new checkings account!");
								correctAmount = false;
							}
					}
			}
				
		}
		
	}
	
	public static void optionMenu() {
		
		System.out.println("1) Register for an account");
		System.out.println("2) Log in");
	
	}
	
	public void employeeMenu(String user) {
		
		List<Items> allAccounts;
		
		System.out.println("1) Approve a customer registration");
		System.out.println("2) Reject a customer registration");
		System.out.println("3) View customer's bank accounts");
		System.out.println("4) View all transaction");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		String option = "";
		
		option = sc.nextLine();
		
		switch (option) {
		case "1":
			System.out.println("Which customer do you want to approve their registration?");
			allAccounts = service.getUnRegisteredAccounts();
			if (allAccounts.size() == 0) {
				System.out.println("Nothing to see here");
				break;
			} else {
			System.out.println("Plese input the ID number: ");
			displayUnapprovedRegistration(allAccounts);			
			}			
			int id = Integer.parseInt(sc.nextLine());
			if (service.completeARegistration(id)) {
				System.out.println("Registration complete, customer can use his/her account now");
			} else {
				System.out.println("Not completed, try again! ");
				System.out.println("");
			}
			break;
		case "2":
			System.out.println("Which customer do you want to reject their registration?");
			allAccounts = service.getUnRegisteredAccounts();
			if (allAccounts.size() == 0) {
				System.out.println("Nothing to see here");
				break;
			} else {
			System.out.println("Plese input the ID number: ");
			displayUnapprovedRegistration(allAccounts);			
			}		
			id = Integer.parseInt(sc.nextLine());
			if (service.rejectARegistration(id)) {
				System.out.println("Rejection complete, customer has been denied of access.");				
			} else {
				System.out.println("Not completed, try again! ");
				System.out.println("");
			}
			break;
		case "3":
			System.out.println("Which customer do you want to view their accounts?");
			System.out.println("Plese input the ID number");
			System.out.println("");
			allAccounts = service.getAccounts();
			viewCustomerAccounts(allAccounts);
			
			id = Integer.parseInt(sc.nextLine());
			viewSpecificCustomerAccounts(service.viewACustomerAccount(id)); 		
			break;
		}
	
	}
	
	public void customerMenu(String user) {
		
		System.out.println("1) Apply for a new bank account");
		System.out.println("2) Deposit money to (checkings/savings/joint account)");
		System.out.println("3) Withdraw money from (checkings/savings/joint account) ");
		System.out.println("4) Transfer money to (checkings/savings/joint account)");
		System.out.println("5) View balance from (checkings/savings/joint account)");
		System.out.println("");
		
		//Scanner sc = new Scanner(System.in);
		String option = "";
		int choose;
		
		boolean correctAmount = true;
		
		option = sc.nextLine();
		
		switch (option) {
		case "1":
			String getUser = user;
			
			System.out.println("What kind of account would you like to apply,");
			System.out.println("1) Savings or 2) Checkings?");
			choose = Integer.parseInt(sc.nextLine());
			while (correctAmount) {
				customerOptionOneMenu(choose, getUser, correctAmount);					
			break;
			}
		}
		
		
	}
	
	public void registerAccount() {
		
		Scanner sc1 = new Scanner(System.in);
		
		boolean checking = true;
		
		String cUser = "";
		
			while (checking) {
				System.out.println("Please enter a username: ");
				cUser = sc1.nextLine();
			
				if (security.auth(cUser)) {
					System.out.println("Username already exist, try again!");
				} else {
					checking = false;
				}
			}			
		System.out.println("Please enter a password: ");
		String cPassword = sc1.nextLine();
			
		Items addItem = new Items(cUser, cPassword, false);
					
			if (service.registerLogin(addItem)) {
				System.out.println("Account created, please wait for an approval");
				System.out.println(" from one of our employee. Thank you!");
				System.out.println("");
			}
		
		
	}

	public void optionLogin () {
		
		boolean loopBack = true;
		
		while (loopBack) {
			System.out.print("Enter username: ");
			String user = sc.nextLine();
				
				if (security.auth(user)) {
					System.out.print("Hello, " + user + ", What is your password? ");							
					String pass = sc.nextLine();
					
						
						if (security.validate(user, pass)) {
								if (security.checkStatus(user, pass) == false) {
									System.out.println("Welcome," + user + " what would you like to do today?");
									System.out.println("");
									employeeMenu(user);										
									loopBack = false;
								}
								else if (security.checkStatus(user, pass) == true
										&& security.checkApproved(user, pass) == true) {
											System.out.println("Welcome," + user + " what would you like to do today?");
											System.out.println("");
											customerMenu(user);
											
											loopBack = false;
											
								} else {
									System.out.println("Please wait for an employee to approve your registration...");
									loopBack = false;
								}
						} else {
							System.out.println("Invalid password");
						}			
				} else {
					System.out.println("You don't have access to our bank!");
					System.out.println("Please enter your username again!");
					System.out.println("");
				}
		}
		
	}
	
	public void display() {
		
		//System.out.println(security.randomBankAccount());	
		
		
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
				optionLogin();
				break;
			}		
		}
	}
		
	
	
	
}


