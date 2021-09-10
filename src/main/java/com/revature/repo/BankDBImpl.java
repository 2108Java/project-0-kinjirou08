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
	public boolean insertAccount(Items addItem) {
		
		boolean success = false;
		
		try (Connection connect = DriverManager.getConnection(url, username, password)) {
			
			
			String query = "insert into customer_table (c_username, c_pass) \r\n"
					+ "values\r\n"
					+ "((select username from log_in where username = '?'),\r\n"
					+ "(select pass from log_in where username = '?'));";
			
			PreparedStatement PS = connect.prepareStatement(query);
			
			PS.setString(1, addItem.getUser());
			PS.setString(2, addItem.getPassword());
			
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


}
