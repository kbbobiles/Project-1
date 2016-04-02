package project1;

import java.sql.Date;
import java.sql.SQLException;

import project1.managers.CreditCardManager;
import project1.managers.CustomerManager;
import project1.managers.DBManager;
import project1.managers.MovieManager;
import project1.managers.PromptManager;
import project1.managers.StarManager;

public class Menu {
	
	private static final String findMoviesByStarCommand = "1";
	private static final String addStarCommand = "2";
	private static final String addCustomerCommand = "3";
	private static final String deleteCustomerCommand = "4";
	private static final String printMetadataCommand = "5";
	private static final String executeSQLCommand = "6";
	private static final String exitMenuCommand = "7";
	private static final String exitProgramCommand = "8";
	
	
	// TODO: Execute the specific queries to satisfy each command
	public static void executeCommand(String command) {
		
		switch (command) {
		case findMoviesByStarCommand:
			String queryChoice = PromptManager.promptString("Search by name (N) or id (I): ");
			if (queryChoice.equals("N")) { // Search by name
				String firstName = PromptManager.promptString("Enter first name (Click Enter to Leave Blank): ");
				String lastName = PromptManager.promptString("Enter last name (Click Enter to Leave Blank): ");
				
				try {
					MovieManager.printMovies(MovieManager.moviesWithStarID(StarManager.findStarIDByName(firstName, lastName)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if (queryChoice.equals("I")) { // Search by ID
				int starID = PromptManager.promptInt("Enter Star's ID: ");
				
				try {
					MovieManager.printMovies(MovieManager.moviesWithStarID(starID));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
			
		case addStarCommand:
			// TODO: Need to implement PromptManager methods
			try {
				String starFirstName = "";
				String starLastName = "";
				String starFullName = PromptManager.promptString("Enter name: ");
				String[] splitStarFullName = starFullName.split(" ");
				if (splitStarFullName.length == 1) {
					starLastName = splitStarFullName[0];
				}
				else {
					starFirstName = splitStarFullName[0];
					starLastName = splitStarFullName[1];
				}
				
				Date dob = PromptManager.promptDate("Enter dob (yyyy-MM-dd): ");
				String photo_url = PromptManager.promptString("Enter photo URL: ");
			
				StarManager.insertStar(starFirstName, starLastName, dob, photo_url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case addCustomerCommand:
			try {
				String addCCID = PromptManager.promptString("Enter a registered credit card ID: ");
				if (CreditCardManager.isCreditCardIDRegistered(addCCID)) {
					String customerFirstName = "";
					String customerLastName = "";
					String customerFullName = PromptManager.promptString("Enter name: ");
					String[] splitCustomerFullName = customerFullName.split(" ");
					if (splitCustomerFullName.length == 1) {
						customerLastName = splitCustomerFullName[0];
					}
					else {
						customerFirstName = splitCustomerFullName[0];
						customerLastName = splitCustomerFullName[1];
					}
					
					String address = PromptManager.promptString("Enter address: ");
					String email = PromptManager.promptString("Enter email: ");
					String password = PromptManager.promptString("Enter password: ");
					
					CustomerManager.insertCustomer(customerFirstName, customerLastName, addCCID, address, email, password);
				}
				else {
					System.out.println(String.format("Invalid credit card '%s'", addCCID));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case deleteCustomerCommand:
			try {
				String deleteCCID = PromptManager.promptString("Enter credit card ID: ");
				CustomerManager.deleteCustomer(deleteCCID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case printMetadataCommand:
			try {
				DBManager.printDatabaseMetadata();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case executeSQLCommand:
			try {
				String commandSQL = PromptManager.promptString("Enter SQL command: ");
				if (commandSQL.startsWith("SELECT")) {
					DBManager.printSelectResults(DBManager.executeSelectSQL(commandSQL));
				}
				else if (commandSQL.startsWith("UPDATE") || commandSQL.startsWith("INSERT") || commandSQL.startsWith("DELETE")) {
					DBManager.printUpdateInsertDeleteResults(DBManager.executeUpdateInsertDeleteSQL(commandSQL));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case exitMenuCommand:
			System.out.println("Exit Menu Command");
			break;
			
		case exitProgramCommand:
			System.out.println("Exit Program Command");
			break;
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
