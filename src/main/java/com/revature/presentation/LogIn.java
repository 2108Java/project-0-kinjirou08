package com.revature.presentation;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import java.util.Scanner;

import org.apache.log4j.Logger;
//import org.apache.log4j.Level;

import com.revature.models.Items;
import com.revature.repo.BankDBImpl;
import com.revature.security.AuthValidate;
import com.revature.service.BankServiceImpl;


public class LogIn implements Logging_In {
	
	private final static Logger loggy = Logger.getLogger(LogIn.class);
	
	Scanner sc = new Scanner(System.in);
	
	public AuthValidate security;
	
	public BankServiceImpl service;	
	
	public BankDBImpl database;

	public LogIn(AuthValidate security, BankServiceImpl service) {
		
		this.security = security;
		this.service = service;
	}

	private static void viewCustomerAccounts(List<Items> allAccounts) {
		
		for (int i = 0; i < allAccounts.size(); i ++) {
				if (allAccounts.get(i) != null) {
					System.out.println("ID: " + allAccounts.get(i).getId());
					System.out.println("Customer's username: " + allAccounts.get(i).getUser());
					
					
				System.out.println("");
				}
		}
		
	}
	
	private static void viewSpecificCustomerAccounts(List<Items> allAccounts) {
		
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
	
	private static void displayUnapprovedRegistration (List<Items> allAccounts) {
		
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
	
	private static void viewBankAccounts (List<Items> checkBankAccounts) {
		
		for (int i = 0; i < checkBankAccounts.size(); i ++) {
			if (checkBankAccounts.get(i) != null) {
				System.out.println("Bank Account Number: " + checkBankAccounts.get(i).getSavings());
				System.out.println("Current Balance: " + checkBankAccounts.get(i).getAmount());				
				System.out.println("");
			}
		}
		
	}

	private boolean customerOptionOneMenu (int choose, String user, boolean correctAmount) {
		
		//List <Items> getBankAccount;
		
		String newBankAccount;
		
		double amount;
		
		
		if (choose == 1) {	
			newBankAccount = security.randomBankAccount();
			//System.out.println(newBankAccount);
			if (security.checkExisting(choose, user)) {
				System.out.println("Already have a savings account!");
				correctAmount = false;
			} else {
				//System.out.println(security.checkExisting(choice, getUser));
				System.out.print("Enter a starting amount: ");
				amount = Double.parseDouble(sc.nextLine());
					if (amount <= 0) {
						System.out.println("Starting amount cannot be less than 0!");
					} else {
						Items savingsAccount = new Items(newBankAccount,user,amount);
							if (service.newAcct(choose, savingsAccount)) {
								System.out.println("Your Bank Account: "+newBankAccount);
								System.out.println("You've successfully added a new savings account!");
								correctAmount = false;
							}
					}
			}													
		} else if (choose == 2) {
				newBankAccount = security.randomBankAccount();
				//System.out.println(newBankAccount);
				if (security.checkExisting(choose, user)) {
				System.out.println("Already have a checkings account!");
				correctAmount = false;
			} else {
					System.out.print("Enter a starting amount: ");
					amount = Double.parseDouble(sc.nextLine());
					if (amount <= 0) {
						System.out.println("Starting amount cannot be less than 0!");
					} else {				
						Items checkingsAccount = new Items (user,amount,newBankAccount);
						System.out.println("Your Bank Account: "+newBankAccount);
							if (service.newAcct(choose, checkingsAccount)) {
								System.out.println("You've successfully added a new checkings account!");
								correctAmount = false;
							}
					}
			}
				
		}
		return correctAmount;
		
	}
	
	private boolean customerOptionTwoMenu (String user, int choose, boolean correctAmount) {
	
	int choice = choose;
	String getUser = user;
	double amount = 0;
	double getMoney = 0;
	double newBalance = 0;
	
	if (choose == 1) {
		getMoney = service.getMoney(choice, getUser);
		System.out.println("Current Balance:" +getMoney);
		System.out.println("How much would you like to deposit to your savings account?");
		amount = Double.parseDouble(sc.nextLine());
		if (amount < 0) {
			System.out.println("You've inputed wrong amount!");
			//System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
		} else {
		newBalance = getMoney + amount;
		if (service.addMoney(choice,newBalance,getUser)) {
		System.out.println("Successfully deposited the money!");
		System.out.println("Current Balance: "+newBalance);
		correctAmount = false;
		}
		}
				
	} else if (choose == 2) {
		getMoney = service.getMoney(choice, getUser);
		System.out.println("Current Balance: "+getMoney);
		System.out.println("How much would you like to deposit to your checkings account?");
		amount = Double.parseDouble(sc.nextLine());
		if (amount < 0) {
			System.out.println("You've inputed wrong amount!");
			//System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
		} else {
		newBalance = getMoney + amount;
		if (service.addMoney(choice,newBalance,getUser)) {
		System.out.println("Successfully deposited the money!");
		System.out.println("Current Balance: "+newBalance);
		correctAmount = false;
	}
		}
	}
	return correctAmount;
		
	}
	
	private boolean customerOptionThreeMenu(String user, int choose, boolean correctAmount) {
		
		int choice = choose;
		String getUser = user;
		double amount = 0;
		double getMoney = 0;
		double newBalance = 0;
		
		if (choose == 1) {
			getMoney = service.getMoney(choice, getUser);
			System.out.println("Current Balance: "+getMoney);
				try {
					System.out.println("How much would you like to withdraw from your savings account?");
					amount = Double.parseDouble(sc.nextLine());
					newBalance = getMoney - amount;
					if (newBalance < 0 ) {
						System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
						System.out.println("");
					} else {
						if (amount < 0 ) {
							System.out.println("You've inputed wrong amount!");
							System.out.println("");
						} else {
							if (service.deductMoney(choice,newBalance,getUser)) {
								System.out.println("You've withdrawn: " +amount);
								System.out.println("Current Balance: "+newBalance);
								System.out.println("Successfully withdrawn money!");
								correctAmount = false;
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input! Please only input numbers");
					System.out.println("");
				}		
								
		} else if (choose == 2) {
			getMoney = service.getMoney(choice, getUser);
			System.out.println("Current Balance: "+getMoney);
				try {
					System.out.println("How much would you like to withdraw from your checkings account?");
					amount = Double.parseDouble(sc.nextLine());
					newBalance = getMoney - amount;
					if (newBalance < 0 ) {
						System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
						System.out.println("");
					} else {
						if (amount < 0 ) {
							System.out.println("You've inputed wrong amount!");
							System.out.println("");
						} else {
							if (service.deductMoney(choice,newBalance,getUser)) {
								System.out.println("You've withdrawn: " +amount);
								System.out.println("Current Balance: "+newBalance);
								System.out.println("Successfully withdrawn money!");
								correctAmount = false;
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input! Please only input numbers");
					System.out.println("");
				}	
			}
		return correctAmount;
		
		}

	private boolean customerOptionFourMenu (String user, int choose, boolean correctAmount) {	
			
		int choice = choose;
		String getUser = user;
		double amount = 0;
		double getMoney = 0;
		double newBalance = 0;
		double getTransferMoney = 0;
		String transferUser;
		
		if (choose == 1) {
			getMoney = service.getMoney(choice, getUser);
			System.out.println("Current Balance (Savings): "+getMoney);
			//System.out.println(getUser);
			
				try {
					System.out.println("How much would you like to transfer from your savings account?");
					amount = Double.parseDouble(sc.nextLine());
					newBalance = getMoney - amount;
					//System.out.println(newBalance);
					if (newBalance < 0 ) {
						System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
						System.out.println("");
					} else {
						if (amount < 0 ) {
							System.out.println("You've inputed wrong amount!");
							System.out.println("");
						} else {
							System.out.println("Please put the bank account number that you want");
							System.out.print("your money to be transfered (space included)... ");
							transferUser = sc.nextLine();
								//System.out.println(transferUser);
								getTransferMoney = service.getTransferMoney(choice, transferUser);
								//System.out.println(getTransferMoney);
								if (getTransferMoney == 0) {
									System.out.println("Cannot transfer with the same bank account!");
								} else {
									System.out.println("Are you sure you want to make the transfer? ");
									System.out.println("If yes, please type 'confirm' and 'cancel' for no.");
									String confirmation = sc.nextLine();
										if (confirmation.equals("confirm")) {
											//System.out.println(confirmation.equals("confirm"));
											if (security.checkAccount(transferUser)) {
												service.deductMoney(choice,newBalance,getUser);
												System.out.println("Current Balance in Savings: " +service.getMoney(choice,getUser));
												newBalance = getTransferMoney + amount;
												service.transferMoney(choice, newBalance, transferUser);
												System.out.println("New Balance in Checkings: " +service.getTransferMoney(choice, transferUser));
												correctAmount = false;
											}
//											
										} else if (confirmation.equals("cancel")) {
											//System.out.println(confirmation.equals("cancel"));
											System.out.println("Canceling the transfer...");
											correctAmount = false;										
										}
								//System.out.println("Current Balance (Checkings): "+getTransferMoney);
								//System.out.println(security.checkAccount(transferUser)); 
								
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input! Please only input numbers");
					System.out.println("");
				}
		} else if (choose == 2) {
			getMoney = service.getMoney(choice, getUser);
			System.out.println("Current Balance (Checkings): "+getMoney);
			//System.out.println(getUser);
			
				try {
					System.out.println("How much would you like to transfer from your checkings account?");
					amount = Double.parseDouble(sc.nextLine());
					newBalance = getMoney - amount;
					//System.out.println(newBalance);
					if (newBalance < 0 ) {
						System.out.println("Remaining balance will be less than 0, cannot accept transaction!");
						System.out.println("");
					} else {
						if (amount < 0 ) {
							System.out.println("You've inputed wrong amount!");
							System.out.println("");
						} else {
							System.out.println("Please put the bank account number that you want");
							System.out.print(" your money to be transfered (space included)... ");
							transferUser = sc.nextLine();
								//System.out.println(transferUser);
								getTransferMoney = service.getTransferMoney(choice, transferUser);
								
								if (getTransferMoney == -1) {
									//System.out.println(getTransferMoney);
									System.out.println("Cannot transfer with the same bank account!");
								} else {
									System.out.println("Are you sure you want to make the transfer? ");
									System.out.println("If yes, please type 'confirm' and 'cancel' for no.");
									String confirmation = sc.nextLine();
									if (confirmation.equals("confirm")) {
										//System.out.println(confirmation.equals("confirm"));
										if (security.checkAccount(transferUser)) {
											service.deductMoney(choice,newBalance,getUser);
											System.out.println("Current Balance in Savings: " +service.getMoney(choice,getUser));
											newBalance = getTransferMoney + amount;
											service.transferMoney(choice, newBalance, transferUser);
											System.out.println("New Balance in Checkings: " +service.getTransferMoney(choice, transferUser));
											correctAmount = false;
										}
//										
									} else if (confirmation.equals("cancel")) {
										//System.out.println(confirmation.equals("cancel"));
										System.out.println("Canceling the transfer...");
										correctAmount = false;										
									}
//								System.out.println("Current Balance (Checkings): "+getTransferMoney);
//								System.out.println(security.checkAccount(transferUser)); 
//								if (security.checkAccount(transferUser)) {  
//									service.deductMoney(choice,newBalance,getUser);
//									System.out.println("Current Balance in Checkings: "+service.getMoney(choice,getUser));
//									newBalance = getTransferMoney + amount;
//									service.transferMoney(choice, newBalance, transferUser);
//									System.out.println("New Balance in Savings: " +service.getTransferMoney(choice, transferUser));											
//									correctAmount = false;
//								}
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input! Please only input numbers");
					System.out.println("");
				}
		}
		return correctAmount;
	}

	private boolean customerOptionFiveMenu (String user, int choose, boolean correctAmount) {
		
		List<Items> checkBankAccount;
		
//		int choice = choose;
//		String getUser = user;
		if (choose == 1) {
			checkBankAccount = service.checkSavingsAccount(choose,user);
			viewBankAccounts(checkBankAccount);
			correctAmount = false;
		} else if (choose == 2) {
			checkBankAccount = service.checkSavingsAccount(choose,user);
			viewBankAccounts(checkBankAccount);
			correctAmount = false;
		}
		return correctAmount;
	}
	
	public boolean applyJointAccount (int choice, String user) {
		
		boolean success = false;	
		double amount = 0.0;		
		String getUser;
		String getUser2;
		String bankAccount;
		
		//System.out.println(user);
		System.out.println("Pick which account you like to join");
		System.out.println("1) Savings or 2) Checkings");		
		choice = Integer.parseInt(sc.nextLine());
		log4j(choice, user);
		bankAccount = security.randomJointBankAccountNumber();
		
		if (choice == 1) {
			System.out.println("Please put your username");
			getUser = sc.nextLine();
			amount = service.getMoney(choice, getUser);
			//service.setJointAccount(bankAccount, getUser);
			//System.out.println(bankAccount);
			//System.out.println(getUser);
			//System.out.println(service.setJointAccount(bankAccount, getUser));
			//System.out.println(amount);
			
			System.out.println("Please put the username of the other account ");
			getUser2 = sc.nextLine();
			amount = amount + service.getMoney(choice, getUser2);
			//service.setJointAccount(bankAccount, getUser2);
			//System.out.println(bankAccount);
			//System.out.println(getUser2);
			//System.out.println(service.setJointAccount(bankAccount, getUser));
			//System.out.println(amount);		
			System.out.println("");
			
			System.out.println("Confirm the process? (Y/N)");
			char yn = sc.next().charAt(0);
			if (yn == 'y' || yn == 'Y') {
				service.setJointAccount(bankAccount, getUser);
				service.setJointAccount(bankAccount, getUser2);
				if (service.createJointAccount(bankAccount, getUser, getUser2, amount)) {
					System.out.println("Joint account successfully created!");
					loggy.info("Successfully created a joint account!");
				} else {
					System.out.println("ERROR!");
				} 
			} else if (yn == 'n' || yn == 'N') {
				System.out.println("Cancelling the process...");
				loggy.info("Cancelled the processing of joining accounts.");
				success = true;
			}
					
		} else if (choice == 2) {
			System.out.println("Please put your username");
			getUser = sc.nextLine();
			amount = service.getMoney(choice, getUser);
			//service.setJointAccount(bankAccount, getUser);
			//System.out.println(bankAccount);
			//System.out.println(getUser);
			//System.out.println(service.setJointAccount(bankAccount, getUser));
			//System.out.println(amount);
			
			System.out.println("Please put the username of the other account ");
			getUser2 = sc.nextLine();
			amount = amount + service.getMoney(choice, getUser2);
			//service.setJointAccount(bankAccount, getUser2);
			//System.out.println(bankAccount);
			//System.out.println(getUser2);
			//System.out.println(service.setJointAccount(bankAccount, getUser));
			//System.out.println(amount);		
			System.out.println("");
			
			System.out.println("Confirm the process? (Y/N)");
			char yn = sc.next().charAt(0);
			if (yn == 'y' || yn == 'Y') {
				service.setJointAccount(bankAccount, getUser);
				service.setJointAccount(bankAccount, getUser2);
				if (service.createJointAccount(bankAccount, getUser, getUser2, amount)) {
					System.out.println("Joint account successfully created!");
					loggy.info("Successfully created a joint account!");
				} else {
					System.out.println("ERROR!");
				} 
			} else if (yn == 'n' || yn == 'N') {
				System.out.println("Cancelling the process...");
				loggy.info("Cancelled the processing of joining accounts.");
				success = true;
			}
		}
		return success;
		
	}
		
	public void employeeMenu(String user) {
		
		List<Items> allAccounts;
		
		System.out.println("1) Approve a customer registration");
		System.out.println("2) Reject a customer registration");
		System.out.println("3) View customer's bank accounts");
		System.out.println("4) View all transaction");
		System.out.println("");
		
		//Scanner sc = new Scanner(System.in);
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
		case "4":
			try{
				   FileInputStream fstream = new FileInputStream("D:\\C-Project 0\\project-0-kinjirou08\\src\\main\\java\\log4j-application.log");
				   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				   String strLine;
				   /* read log line by line */
				   while ((strLine = br.readLine()) != null)   {
				     /* parse strLine to obtain what you want */
				     System.out.println (strLine);
				   }
				   fstream.close();
				} catch (Exception e) {
				     System.err.println("Error: " + e.getMessage());
				}
			break;
		}
	
	}
	
	public void customerMenu(String user) {
		
		char yn = 'y';
		String getUser = user;
		int choose = 0;
		boolean correctAmount = true;
		boolean result = true;
		String bankAccount;
		
			
		do {
			System.out.println("1) Apply for a new bank account");
			System.out.println("2) Deposit money");
			System.out.println("3) Withdraw money ");
			System.out.println("4) Transfer money ");
			System.out.println("5) View balance");
			System.out.println("6) Apply for a joint account");
			
			System.out.println("");
			
			int option = Integer.parseInt(sc.nextLine());
			//loggy.info(getUser + " chose option " + option + " Applying for a new bank account");
			//System.out.println(option);
			log4j(getUser, option);	
			
				
			switch (option) {		
			case 1:
								
				System.out.println("What kind of account would you like to apply,");
				System.out.println("1) Savings or 2) Checkings?");
				choose = Integer.parseInt(sc.nextLine());
				//System.out.println(choose);
				log4j(choose, getUser);
				while (correctAmount) {
					result = customerOptionOneMenu(choose, getUser, correctAmount);
					if (result == false) {
						break;
					}
				}
				break;				
			case 2:
				System.out.println("Where would you like to deposit your money?");
				System.out.println("1) Savings or 2) Checkings?");
				choose = Integer.parseInt(sc.nextLine());
				//System.out.println(choose);
				log4j(choose, getUser);
				while (correctAmount) {
					result = customerOptionTwoMenu(getUser, choose, correctAmount);
					if (result == false) {
						break;
					}
				}			
			break;
			case 3:
				System.out.println("Where would you like to withdraw your money?");
				System.out.println("1) Savings or 2) Checkings?");
				choose = Integer.parseInt(sc.nextLine());
				//System.out.println(choose);
				log4j(choose, getUser);
				while (correctAmount) {
					result = customerOptionThreeMenu(getUser, choose, correctAmount);
					if (result == false) {
						break;
					}
				}
				break;
			case 4:
				System.out.println("Transfer money from: ");
				System.out.println("1) Savings or 2) Checkings?");
				choose = Integer.parseInt(sc.nextLine());
				//System.out.println(choose);
				log4j(choose, getUser);
				if (choose == 1) {
					System.out.println("Please put your Savings account number... (spaces included)");				
					bankAccount = sc.nextLine();
						if (security.checkBankAccount(bankAccount, getUser, choose)) {
							System.out.println("Success!");
						} else {
							System.out.println("No savings account number!");
							break;
						}
						
				} else if (choose == 2) {
					System.out.println("Please put your Checkings account number... (spaces included)");				
					bankAccount = sc.nextLine();
						if (security.checkBankAccount(bankAccount, getUser, choose)) {
							System.out.println("Success!");
						} else {
							System.out.println("No checkings account number!");
							break;
						}
				}
				while (correctAmount) {
					result = customerOptionFourMenu(getUser, choose, correctAmount);
					if (result == false) {
						break;
					}
				}
				break;
			case 5:
				System.out.println("Which account would you want to check?");
				System.out.println("1) Savings or 2) Checkings");
				choose = Integer.parseInt(sc.nextLine());
				//System.out.println(choose);
				log4j(choose, getUser);
				while (correctAmount) {
					result = customerOptionFiveMenu(getUser, choose, correctAmount);
					if (result == false) {
						break;
					}
				}
				break;
			case 6:
				applyJointAccount(0,getUser);
				break;
			default: 
				//System.out.println("Invalid input, try again!");
				loggy.warn("Invalid input, try again!");
				
			}	
		if (yn == 'y' || yn == 'Y') {
			System.out.print("(Y/N) Is there anything else you need to do?: ");
			yn = sc.next().charAt(0);
			System.out.println("");
			sc.nextLine();
			//System.out.println("What would you like to do?");
		} 
				
		} while (yn == 'y' || yn == 'Y'); 		
			System.out.println("Thankie!");			
		
	}
	
	public void log4j (int choose,String getUser) {
		
		if (choose == 1) {
			loggy.info(getUser + " chose Savings");
		} else if (choose == 2) {
			loggy.info(getUser + " chose Checkings");
		} 		
		
		choose = 0;
		
	}
	
	public void log4j (String getUser, int option) {
	
		if (option == 1) {
			loggy.info(getUser + " chose option " + option + " Applying for a new bank account");
		} else if (option == 2) {
			loggy.info(getUser + " chose option " + option + " Depositing money to");
		} else if (option == 3) {
			loggy.info(getUser + " chose option " + option + " Withdrawing money from");
		} else if (option == 4) {
			loggy.info(getUser + " chose option " + option + " Transfering money to");
		} else if (option == 5) {
			loggy.info(getUser + " chose option " + option + " Viewing balance from");
		} else if (option == 6) {
			loggy.info(getUser + " chose option " + option + " Applying joint accounts for");
		}
		
		option = 0;
	}
	
	public void registerAccount() {
		
		//Scanner sc1 = new Scanner(System.in);
		
		boolean checking = true;
		
		String cUser = "";
		
			while (checking) {
				System.out.println("Please enter a username: ");
				cUser = sc.nextLine();
			
				if (security.auth(cUser)) {
					System.out.println("Username already exist, try again!");
				} else {
					checking = false;
				}
			}			
		System.out.println("Please enter a password: ");
		String cPassword = sc.nextLine();
			
		Items addItem = new Items(cUser, cPassword, false);
					
			if (service.registerLogin(addItem)) {
				System.out.println("Account created, please wait for an approval");
				System.out.println(" from one of our employee. Thank you!");
				System.out.println("");
			}
		
		
	}

	public void optionMenu() {
		
		System.out.println("1) Register for an account");
		System.out.println("2) Log in");
		System.out.println("0) Exit");
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
		//applyJointAccount(1);
		
		boolean result = true;
		String choose = "";
		
		//loggy.info("Starting the application");
	
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
			case "0":
				System.out.println("Exiting the program");
				result = false;
			default:
				System.out.println("Invalid input! try again.");
				System.out.println("");
			
			}		
		}
	}
		
	
}


