package project1.managers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StarManager {
	
	//  Returns the first ID found for given name
	public static int findStarIDByName(String firstName, String lastName) throws SQLException {
		
		int id = -1;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		if (!"".equals(firstName) && !"".equals(lastName)) {	// Using both firstName and lastName
			String fullNameSQL = "select id from stars where first_name = ? and last_name = ?";
			statement = DBManager.getConnection().prepareStatement(fullNameSQL);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			results = statement.executeQuery();
		}
		else if (!"".equals(firstName)) {	// Using only firstName
			String firstNameSQL = "select id from stars where first_name = ?";
			statement = DBManager.getConnection().prepareStatement(firstNameSQL);
			statement.setString(1, firstName);
			results = statement.executeQuery();
		}
		else if (!"".equals(lastName)) {	// Using only lastName
			String lastNameSQL = "select id from stars where last_name = ?";
			statement = DBManager.getConnection().prepareStatement(lastNameSQL);
			statement.setString(1, lastName);
			results = statement.executeQuery();
		}
		else { // firstName and lastName left blank
			return id;
		}
		
		if (results.first()) {
			id = results.getInt("id");
		}
		
		statement.close();
		results.close();
		
		return id;
	}
	
	// Jnserts a new Star into database
	public static int insertStar(String firstName, String lastName, Date dob, String photo_url) throws SQLException {
		
		int affectedRows = 0;
		
		String insertSQL = "insert into stars (first_name, last_name, dob, photo_url) values (?, ?, ?, ?)";
		PreparedStatement statement = DBManager.getConnection().prepareStatement(insertSQL);
		
		statement.setString(1, firstName);
		statement.setString(2, lastName);
		statement.setDate(3, dob);
		statement.setString(4, photo_url);
		affectedRows = statement.executeUpdate();
		
		statement.close();
		
		return affectedRows;
		
	}
	
}
