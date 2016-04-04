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
	
	
	// TODO: Execute the specific queries to satisfy each command: Still need exitMenu and exitProgram logic
	public static void executeCommand(String command) {
		
		switch (command) {
		case findMoviesByStarCommand:
			executeFindMoviesByStarCommand();
			break;
			
		case addStarCommand:
			executeAddStarCommand();
			break;
		
		case addCustomerCommand:
			executeAddCustomerCommand();
			break;
			
		case deleteCustomerCommand:
			executeDeleteCustomerCommand();
			break;
			
		case printMetadataCommand:
			executePrintMetadataCommand();
			break;
			
		case executeSQLCommand:
			executeExecuteSQLCommand();
			break;
			
		case exitMenuCommand:
			System.out.println("Exit Menu Command");
			break;
			
		case exitProgramCommand:
			System.out.println("Exit Program Command");
			break;
			
		}
		
	}
	
	// Option 1 logic
	private static void executeFindMoviesByStarCommand() {
		String queryChoice = PromptManager.promptString("Search by name (N) or id (I): ");
		if (queryChoice.equals("N")) { // Search by name
			String firstName = PromptManager.promptString("Enter first name (Click Enter to Leave Blank): ");
			String lastName = PromptManager.promptString("Enter last name (Click Enter to Leave Blank): ");
			
			try {
				DBManager.printSelectResults(MovieManager.getMoviesWithName(firstName, lastName));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (queryChoice.equals("I")) { // Search by ID
			int starID = PromptManager.promptInt("Enter Star's ID: ");
			
			try {
				DBManager.printSelectResults(MovieManager.moviesWithStarID(starID));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Option 2 logic
	private static void executeAddStarCommand() {
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
			
			Date dob = PromptManager.promptDate("Enter dob (yyyy/MM/dd): ");
			String photo_url = PromptManager.promptString("Enter photo URL: ");
		
			if (StarManager.insertStar(starFirstName, starLastName, dob, photo_url) != 0) {
				System.out.println("Insert successful.");
			}
			else {
				System.out.println("Insert failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 3 logic
	private static void executeAddCustomerCommand() {
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
	}
	
	// Option 4 logic
	private static void executeDeleteCustomerCommand() {
		try {
			String deleteCCID = PromptManager.promptString("Enter credit card ID: ");
			CustomerManager.deleteCustomer(deleteCCID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 5 logic
	private static void executePrintMetadataCommand() {
		try {
			DBManager.printDatabaseMetadata();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 6 logic
	private static void executeExecuteSQLCommand() {
		try {
			String commandSQL = PromptManager.promptString("Enter SQL command: ");
			if (commandSQL.toUpperCase().startsWith("SELECT")) {
				DBManager.printSelectResults(DBManager.executeSelectSQL(commandSQL));
			}
			else if (commandSQL.toUpperCase().startsWith("UPDATE") || commandSQL.toUpperCase().startsWith("INSERT") || commandSQL.toUpperCase().startsWith("DELETE")) {
				DBManager.printUpdateInsertDeleteResults(DBManager.executeUpdateInsertDeleteSQL(commandSQL));
			}
			else { // user input SQL doesn't start with SELECT/UPDATE/INSERT/DELETE
				System.out.println("Invalid SQL Command: Must start with SELECT/UPDATE/INSERT/DELETE.");
			}
			// TODO: Maybe add else statement to catch wrong input
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Prints the menu
	public static void printMenu() {
		System.out.println(String.format("========================================\n" + 
						  "%s: Print movies featuring a given star.\n" +
						  "%s: Insert a new star into the database.\n" +
						  "%s: Insert a customer into the database.\n" +
						  "%s: Delete a customer from the database.\n" +
						  "%s: Provide the metadata of the database.\n" +
						  "%s: Enter a SQL query.\n" +
						  "%s: Back to login.\n" +
						  "%s: Quit.\n" +
						  "========================================", findMoviesByStarCommand, addStarCommand, 
						  addCustomerCommand, deleteCustomerCommand, printMetadataCommand, executeSQLCommand, 
						  exitMenuCommand, exitProgramCommand));
	}
	
	// TODO: Create run program method with a while loop below
	

}
