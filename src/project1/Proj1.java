package project1;

import java.sql.*;
import java.util.*;

import project1.managers.CreditCardManager;
import project1.managers.CustomerManager;
import project1.managers.MovieManager;
import project1.managers.StarManager;

public class Proj1 {
	
	public static void main(String[] args) {
		
		//DBManager db = new DBManager("jdbc:mysql:///moviedb", "root", "password");
		DBManager.setConnection("jdbc:mysql:///moviedb", "root", "password");
		
		try {
			
			List<Map<String, Object>> selectResult = DBManager.executeSelectSQL("select * from sales limit 10");
			DBManager.printSelectResults(selectResult);
			System.out.println();
			
			// Kristen Kreuk's ID in movieDB is 911
			MovieManager.printMovies(MovieManager.moviesWithStarID(911));
			System.out.println();
			MovieManager.printMovies(MovieManager.moviesWithStarID(StarManager.findStarIDByName("Kristin", "Kreuk")));
			System.out.println();
			
			System.out.println(StarManager.findStarIDByName("Kristin", "Kreuk"));
			System.out.println(StarManager.findStarIDByName("Kristin", ""));
			System.out.println(StarManager.findStarIDByName("", "Kreuk"));
			System.out.println(StarManager.findStarIDByName("", ""));
			System.out.println();
			
			// example isCreditCardIDRegistered() command
			System.out.println(CreditCardManager.isCreditCardIDRegistered("0011 2233 4455 6677"));
			System.out.println(CreditCardManager.isCreditCardIDRegistered("777"));
			System.out.println();
			
			// example insertStar() command
			//StarManager.insertStar("Kenneth", "Bobiles", "2000-10-31", "");
			
			// example insertCustomer() command
			//CustomerManager.insertCustomer("Kenneth", "Bobiles", "490010", "", "", "");
			
			// example deleteCustomer() command
			System.out.println(CustomerManager.deleteCustomer(9999999));
			System.out.println();
			
			DBManager.printDatabaseMetadata();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		DBManager.close();
		
	}

}
