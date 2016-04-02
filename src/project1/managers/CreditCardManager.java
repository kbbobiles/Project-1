package project1.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardManager {
	
	// Checks if ccID exists in creditcards table
	public static boolean isCreditCardIDRegistered(String ccID) throws SQLException {
		
		String selectSQL = "select * from creditcards where id = ?";
		PreparedStatement statement = DBManager.getConnection().prepareStatement(selectSQL);
		
		statement.setString(1, ccID);
		ResultSet results = statement.executeQuery();
		
		boolean answer = results.first();
		statement.close();
		results.close();
		
		return answer;
	}

}
