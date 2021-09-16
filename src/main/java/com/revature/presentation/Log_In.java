package com.revature.presentation;
import java.util.Scanner;
import com.revature.models.Items;
import com.revature.security.auth_validate;
import com.revature.service.BankServiceImpl;

public class Log_In implements Logging_In {
	
	Scanner sc = new Scanner(System.in);
	
	public auth_validate security;
	
	public BankServiceImpl service;	


	public Log_In(auth_validate security, BankServiceImpl service) {
		this.security = security;
		this.service = service;
	}

	public static void viewCustomerAccounts(Items[] allAccounts) {
		
		for (int i = 0; i < allAccounts.length; i ++) {
				if (allAccounts[i] != null) {
					System.out.println("ID: " + allAccounts[i].getId());
					System.out.println("Customer's username: " + allAccounts[i].getUser());
					
					
				System.out.println("");
				}
		}
		
	}
	
	public static void viewSpecificCustomerAccounts(Items[] allAccounts) {
		
		for (int i = 0; i < allAccounts.length; i ++) {
				if (allAccounts[i] != null) {
					System.out.println("ID: " + allAccounts[i].getId());
					System.out.println("Customer's username: " + allAccounts[i].getUser());
					System.out.println("Customer's Checkings Account: " + allAccounts[i].getCheckings());
					System.out.println("Customer's Savings Account: " + allAccounts[i].getSavings());				
					
				System.out.println("");
				}
		}

	}
	
	public static void displayUnapprovedRegistration (Items[] allAccounts) {
		
		for (int i = 0; i < allAccounts.length; i ++) {
			if (allAccounts[i] != null) {
				System.out.println("ID: " + allAccounts[i].getId());
				System.out.println("Username: " + allAccounts[i].getUser());
				
				if ((allAccounts[i].isApproved() == false)) {
					System.out.println("Approved? No");
			}
			System.out.println("");
			}
		}
	}
	
	public static void checkExistingAccount (String user) {
		
	}
	
	public static void optionMenu() {
		
		System.out.println("1) Register for an account");
		System.out.println("2) Log in");
	}
	
	public void employeeMenu(String user) {
		
		Items[] allAccounts;
		
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
			System.out.println("Plese input the ID number");
			System.out.println("");
			allAccounts = service.getUnRegisteredAccounts();
			displayUnapprovedRegistration(allAccounts);
			
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
			System.out.println("Plese input the ID number");
			System.out.println("");
			allAccounts = service.getUnRegisteredAccounts();
			displayUnapprovedRegistration(allAccounts);
			
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
		double amount;
		boolean correctAmount = true;
		
		option = sc.nextLine();
		
		switch (option) {
		case "1":
			String newBankAccount = security.randomBankAccount();
			String getUser = user;
			System.out.println("What kind of account would you like to apply,");
			System.out.println("1) Savings or 2) Checkings?");
			choose = Integer.parseInt(sc.nextLine());
			while (correctAmount) {
				if (choose == 1) {
					if (security.checkExisting(getUser)) {
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
					System.out.print("Enter a starting amount: ");
					newBankAccount = security.randomBankAccount();
					amount = Double.parseDouble(sc.nextLine());
					Items checkingsAccount = new Items(newBankAccount,getUser,amount);
					
				}
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
//				
//				System.out.println("Please enter an amount for the savings account");
//				String checkings = sc1.nextLine();
//				
//				System.out.println("Please enter an amount for the checkings account");
//				String savings = sc1.nextLine();
//				
		Items addItem = new Items(cUser, cPassword, false);
					
			if (service.registerLogin(addItem)) {
				System.out.println("Account created, please wait for an approval");
				System.out.println(" from one of our employee. Thank you!");
				System.out.println("");
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
									//result = false;
							} else {
								System.out.println("Invalid password");
							}			
					} else {
						System.out.println("You don't have access to our bank!");
						System.out.println("Please enter your username again!");
						System.out.println("");
					}
				}
				break;
			}		
		}
	}
		
	
	
	
}


