package project1;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import project1.managers.CreditCardManager;
import project1.managers.CustomerManager;
import project1.managers.DBManager;
import project1.managers.LoginManager;
import project1.managers.MovieManager;
import project1.managers.PromptManager;
import project1.managers.StarManager;

public class Proj1 {
	
	public static void main(String[] args) {
		
		//DBManager db = new DBManager("jdbc:mysql:///moviedb", "root", "password");
		
		String username;
		String password;
		
		// TODO: Prompt user for username and password
		//username = PromptManager.promptString("Enter usename");
		
		//if (LoginManager.isLoginAuth(username, password)) {
			DBManager.setConnection("jdbc:mysql:///moviedb", "root", "password");
		//}
				
		
		try {
			/*
			List<Map<String, Object>> selectResult = DBManager.executeSelectSQL("select * from movies limit 100");
			DBManager.printSelectResults(selectResult);
			System.out.println();
			
			MovieManager.printMovies(MovieManager.moviesWithStarID(4444444));
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
			System.out.println(StarManager.findStarIDByName("dawda", ""));
			System.out.println();
			
			//DBManager.printSelectResults(MovieManager.moviesWithStarFirstName("Kristin"));
			DBManager.printSelectResults(MovieManager.getMovies("", "Kreuk"));
			
			
			
			// example isCreditCardIDRegistered() command
			System.out.println(CreditCardManager.isCreditCardIDRegistered("0011 2233 4455 6677"));
			System.out.println(CreditCardManager.isCreditCardIDRegistered("777"));
			System.out.println();
			
			// example insertStar() command
			//StarManager.insertStar("Kenneth", "Bobiles", Date.valueOf("2000/02/10".replace("/", "-")), "");
			
			// example failed insertCustomer() command		existing id 490010
			//CustomerManager.insertCustomer("Kenneth", "Bobiles", "490010", "", "", "");
			
			// example deleteCustomer() command
			//System.out.println(CustomerManager.deleteCustomer("490010"));
			System.out.println();
			
			// example printDatabaseMetadata() command
			//DBManager.printDatabaseMetadata();

			 */
			
			Menu.printMenu();
			Menu.executeCommand("6");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBManager.close();
		PromptManager.closeScanner();
	}

}
