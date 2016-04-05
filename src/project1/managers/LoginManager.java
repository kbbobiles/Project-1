package project1.managers;

public class LoginManager {
	public static boolean logged_in = false;
	
	// Checks to see if username and password is correct
//	public static boolean isLoginAuth(String username, String password) {
//		return ("root".equals(username) && "password".equals(password));
//	}
	
	public static void login() {		
		String username = PromptManager.promptString("Enter Username: ");
		String password = PromptManager.promptString("Enter Password: ");
//		if (isLoginAuth(username, password)) {
			if (DBManager.setConnection("jdbc:mysql://localhost:3306/moviedb", username, password)) {
				System.out.println("Successfully logged in.");
				logged_in = true;
			}
			else {
				promptTryAgain();
			}
//		}
	}
	
	private static void promptTryAgain() {
		String command = PromptManager.promptRequiredString("Try again? (Y) or (N): ");
		if (command.equalsIgnoreCase("N")) {
			PromptManager.closeScanner();
			System.exit(1);
		}
		else if (command.equalsIgnoreCase("Y")) {
			login();
		}
		else {
			promptTryAgain();
		}
	}
	
}
