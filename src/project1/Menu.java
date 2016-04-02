package project1;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String firstName = PromptManager.promptString("Enter first name: ");
			String lastName = PromptManager.promptString("Enter last name: ");
			Date dob = PromptManager.promptDate("Enter dob (yyyy-MM-dd): ");
			String photo_url = PromptManager.promptString("Enter photo URL: ");
			
			try {
				StarManager.insertStar(firstName, lastName, dob, photo_url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		
		case addCustomerCommand:
			System.out.println("Add Customer Command");
			break;
			
		case deleteCustomerCommand:
			System.out.println("Delete Customer Command");
			break;
			
		case printMetadataCommand:
			System.out.println("Print Metadata Command");
			break;
			
		case executeSQLCommand:
			System.out.println("Execute SQL Command");
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
