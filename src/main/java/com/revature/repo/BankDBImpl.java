package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Items;

public class BankDBImpl implements BankDB {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/Bank_DB";
	String username = "postgres";
	String password = "sp5hnug2";
	
	@Override
	public boolean authenticate(String user) {
		
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
		
				String query = "SELECT username FROM log_in WHERE username = ?";
				
				PreparedStatement PS = connect.prepareStatement(query);
				
				PS.setString(1, user);
				
				ResultSet RS = PS.executeQuery();
				
				if (RS.next()) {
					//result = RS.getString(1);
					success = true;
				}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean validate(String user, String pass) {
		
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
		
				String query = "SELECT username, pass FROM log_in WHERE username = ? AND pass = ?";
				
				PreparedStatement PS = connect.prepareStatement(query);
				
				PS.setString(1, user);
				PS.setString(2, pass);
				
				ResultSet RS = PS.executeQuery();
				
				if (RS.next()) {
					//result = RS.getString(1);
					success = true;
				}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean insertLogin(Items addItem) {
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			
			String query = "with new_order as (\r\n"
					+ "  insert into customer_table (c_username, c_pass, c_is_approved) values (?,?,?)\r\n"
					+ "  returning c_username, c_pass\r\n"
					+ ")\r\n"
					+ "insert into log_in (username, pass)\r\n"
					+ "values \r\n"
					+ "((select c_username from new_order),\r\n"
					+ "(select c_pass from new_order)\r\n"
					+ ")";
			
			PreparedStatement PS = connect.prepareStatement(query);
			
			PS.setString(1, addItem.getUser());
			PS.setString(2, addItem.getPassword());
//			PS.setString(3, addItem.getCheckings());
//			PS.setString(4, addItem.getSavings());
//			PS.setString(5, addItem.getJointAcc());
			PS.setBoolean(3, addItem.isApproved());
			
			
			PS.execute();
			
			success = true;
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return success;
	}

	@Override
	public boolean selectStatus(String user, String pass) {
		
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
		
				String query = "SELECT is_customer FROM log_in WHERE username = ? and pass = ? ";
				
				PreparedStatement PS = connect.prepareStatement(query);
				
				PS.setString(1, user);
				PS.setString(2, pass);
								
				ResultSet RS = PS.executeQuery();
				
				while (RS.next()) {
				
				if (RS.getBoolean("is_customer") == false) {
					success = false;
				} else if (RS.getBoolean("is_customer") == true) {
					success = true;
				}
				}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;

	}

	@Override
	public boolean checkApproved(String user, String pass) {
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
		
				String query = "SELECT c_is_approved FROM customer_table WHERE c_username = ? and c_pass = ? ";
				
				PreparedStatement PS = connect.prepareStatement(query);
				
				PS.setString(1, user);
				PS.setString(2, pass);
								
				ResultSet RS = PS.executeQuery();
				
				while (RS.next()) {
				
				if (RS.getBoolean("c_is_approved") == false) {
					success = false;
				} else if (RS.getBoolean("c_is_approved") == true) {
					success = true;
				}
				}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;

	}

	@Override
	public Items[] selectAllAccounts() {
		
		Items[] allAccounts = new Items[10];
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			String query = "SELECT * FROM customer_table ORDER BY c_id ASC";
			
			PreparedStatement ps = connect.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			
			while (rs.next()) {
				
				allAccounts[i] = new Items(rs.getInt("c_id"),
						rs.getString("c_username"),
						rs.getBoolean("c_is_approved"));
				i++;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return allAccounts;
		
	}

	@Override
	public boolean upadteARegistration(int id) {
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			String query = "UPDATE customer_table set c_is_approved = ?  WHERE c_id = ?";
			
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setBoolean(1,true);
			ps.setInt(2, id);
		
			ps.executeUpdate();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

	@Override
	public boolean deleteARegistration(int id) {

		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			String query = "DELETE FROM customer_table WHERE c_id = ?";
			
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setInt(1, id);
		
			ps.executeUpdate();
			
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;

	}

	
	@Override
	public Items[] selectUnRegisteredAccounts() {
		
		Items[] allAccounts = new Items[10];
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			String query = "SELECT * FROM customer_table WHERE c_is_approved = ? ORDER BY c_id ASC ";
			
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setBoolean(1, false);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			
			while (rs.next()) {
				
				allAccounts[i] = new Items(rs.getInt("c_id"),
						rs.getString("c_username"),
						rs.getBoolean("c_is_approved"));
				i++;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return allAccounts;
	}

	@Override
	public Items[] selectACustomerAccount(int id) {
		
		Items[] allAccounts = new Items[10];
		
		//boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
		
				String query = "SELECT c_id, c_username, c_checkings, c_savings "
						+ "FROM customer_table WHERE c_id = ?";
				
				PreparedStatement PS = connect.prepareStatement(query);
				
				PS.setInt(1, id);
				
				ResultSet RS = PS.executeQuery();
				
				
				int i = 0;
				
				while (RS.next()) {
					
					allAccounts[i] = new Items(RS.getInt("c_id"),
							RS.getString("c_username"),
							RS.getString("c_checkings"),
							RS.getString("c_savings"));
					i++;
					
				}
				
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	

	}
		return allAccounts;

	}

	@Override
	public boolean insertNewAccount(Items savingsAccount) {
		
		boolean success = false;
//		System.out.println(savingsAccount.getUser());
//		System.out.println(savingsAccount.getSavings());
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			
			String query = "with new_order as (\r\n"
					+ "  update customer_table set c_savings = ? where c_username = ? \r\n"
					+ "  returning c_savings\r\n"
					+ ")\r\n"
					+ "insert into savings_acct (bank_account, balance)\r\n"
					+ "values \r\n"
					+ "((select c_savings from new_order),?)";
			
			PreparedStatement PS = connect.prepareStatement(query);
			
			PS.setString(1, savingsAccount.getSavings());
			PS.setString(2, savingsAccount.getUser());
			PS.setDouble(3, savingsAccount.getAmount());		
			
			PS.execute();
			
			success = true;
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return success;
	}

	@Override
	public boolean selectExisitingAccount(String checkBankAccount) {
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			
			String query = "SELECT c_savings FROM customer_table WHERE c_username = ?";
			
			PreparedStatement PS = connect.prepareStatement(query);
			
			PS.setString(1, checkBankAccount);
			
			ResultSet RS = PS.executeQuery();
			
			while (RS.next()) {
				if (RS.getString("c_savings").equals("No Account yet")) {
					success = false;
				} else {
					success = true;
				}
			}
					
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return success;
	}
	
}
