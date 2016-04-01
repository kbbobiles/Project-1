package Project1;

import java.sql.*;

public class Proj1 {
	
	public static void main(String[] args) {
		
		DBContext db = new DBContext("jdbc:mysql:///moviedb", "root", "password");
		
		try {
			ResultSet result = db.executeSelectSQL("SELECT * FROM sales");
			while (result.next())
	            {
				
				ResultSetMetaData metadata = result.getMetaData();
	            
	            // Print type of each attribute
	            for (int i = 1; i <= metadata.getColumnCount(); i++) {
	            		System.out.println(String.format("%s: %s", metadata.getColumnName(i), result.getObject(i).toString()));
	            }
				System.out.println();
			}
			//System.out.println(db.executeUpdateSQL("DELETE FROM genres WHERE ID = 888888"));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		db.close();
		
	}

}
