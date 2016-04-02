package project1.managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerManager {
	
	// Inserts a new Customer into database
	public static int insertCustomer(String firstName, String lastName, String ccID, String address, String email, String password) throws SQLException {
		
		String insertSQL = "insert into customers (first_name, last_name, cc_id, address, email, password) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = DBManager.getConnection().prepareStatement(insertSQL);
		
		statement.setString(1, firstName);
		statement.setString(2, lastName);
		statement.setString(3, ccID);
		statement.setString(4, address);
		statement.setString(5, email);
		statement.setString(6, password);
		int affectedRows = statement.executeUpdate();
		
		statement.close();
		
		return affectedRows;
	}
	
	// Deletes Customer from database with ID
	public static int deleteCustomer(int id) throws SQLException {
		
		String deleteSQL = "delete from customers where id = ?";
		PreparedStatement statement = DBManager.getConnection().prepareStatement(deleteSQL);
		
		statement.setInt(1, id);
		int affectedRows = statement.executeUpdate();
		
		statement.close();
		
		return affectedRows;
		
	}

}
