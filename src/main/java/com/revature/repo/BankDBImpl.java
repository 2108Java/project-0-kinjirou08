package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDBImpl implements BankDB {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/Bank_DB";
	String username = "postgres";
	String password = "sp5hnug2";
	
	@Override
	public boolean authenticate(String user) {
		
		String result = "";
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

}
