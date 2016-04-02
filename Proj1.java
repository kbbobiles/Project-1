package project1;

import java.sql.*;
import java.util.*;

public class Proj1 {
	
	public static void main(String[] args) {
		
		DBManager db = new DBManager("jdbc:mysql:///moviedb", "root", "password");
		
		try {
			
			List<Map<String, Object>> selectResult = db.executeSelectSQL("SELECT * FROM movies");
			db.printSelectResults(selectResult);
			
			// Kristen Kreuk's ID in movieDB is 911
			db.printMovies(db.moviesWithStarID(911));
			System.out.println();
			db.printMovies(db.moviesWithStarID(db.selectStar("Kristin", "Kreuk")));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		db.close();
		
	}

}
