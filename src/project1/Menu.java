package project1;

import java.sql.Date;
import java.sql.SQLException;

import project1.managers.CreditCardManager;
import project1.managers.CustomerManager;
import project1.managers.DBManager;
import project1.managers.LoginManager;
import project1.managers.MovieManager;
import project1.managers.PromptManager;
import project1.managers.StarManager;

public class Menu {
	
	private static final String findMoviesByStarCommand = "1" ;
	private static final String addStarCommand = "2";
	private static final String addCustomerCommand = "3";
	private static final String deleteCustomerCommand = "4";
	private static final String printMetadataCommand = "5";
	private static final String executeSQLCommand = "6";
	private static final String exitMenuCommand = "7";
	private static final String exitProgramCommand = "8";
	
	public static void run() {
		LoginManager.login();
		printMenu();
		String command = PromptManager.promptString("Enter a command: ");
		while (!command.equals(exitMenuCommand) && !command.equals(exitProgramCommand)) {
			System.out.println("========================================");
			executeCommand(command);
			printMenu();
			command = PromptManager.promptString("Enter a command: ");
		}
		executeCommand(command);
		
	}
	
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
			executeExitMenuCommand();
			break;
			
		case exitProgramCommand:
			executeExitProgramCommand();
			break;
		}	
	}
	
	// Option 1 logic
	private static void executeFindMoviesByStarCommand() {
		System.out.println("Print movies featuring a given star.");
		String queryChoice = PromptManager.promptString("Search by name (N) or id (I): ");
		if (queryChoice.equalsIgnoreCase("N")) { // Search by name
			String name = PromptManager.promptString("Enter name: ");
			
			try {
				DBManager.printSelectResults(MovieManager.getMoviesWithName(name));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (queryChoice.equalsIgnoreCase("I")) { // Search by ID
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
		System.out.println("Insert a new star into the database.");
		try {
			String firstName = PromptManager.promptString("Enter first name (Click Enter to Leave Blank): ");
			String lastName = PromptManager.promptRequiredString("Enter last name (Required): ");

			Date dob = PromptManager.promptDate("Enter dob (yyyy/MM/dd): ");
			String photo_url = PromptManager.promptString("Enter photo URL: ");
			
			int rowsAffected = StarManager.insertStar(firstName, lastName, dob, photo_url);
			DBManager.printUpdateInsertDeleteResults(rowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 3 logic
	private static void executeAddCustomerCommand() {
		System.out.println("Insert a customer into the database.");
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
				
				int rowsAffected = CustomerManager.insertCustomer(customerFirstName, customerLastName, addCCID, address, email, password);
				DBManager.printUpdateInsertDeleteResults(rowsAffected);
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
		System.out.println("Delete a customer from the database.");
		try {
			String deleteCCID = PromptManager.promptString("Enter credit card ID: ");
			int rowsAffected = CustomerManager.deleteCustomer(deleteCCID);
			DBManager.printUpdateInsertDeleteResults(rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 5 logic
	private static void executePrintMetadataCommand() {
		System.out.println("Provide the metadata of the database.");
		try {
			DBManager.printDatabaseMetadata();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Option 6 logic
	private static void executeExecuteSQLCommand() {
		System.out.println("Enter a SQL query.");
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
	
	// Option 7 logic
	private static void executeExitMenuCommand() {
		LoginManager.logged_in = false;
		System.out.println("Exiting Menu...");
		System.out.println("Logged out.");
		run();
	}
	
	// Option 8 logic
	private static void executeExitProgramCommand() {
		System.out.println("Exiting Program");
		System.out.println("Logged out.");
		System.out.println("Goodbye!");
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
}
