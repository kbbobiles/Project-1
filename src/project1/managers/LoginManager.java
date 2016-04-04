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
			if (DBManager.setConnection("jdbc:mysql:///moviedb", username, password)) {
				System.out.println("Successfully logged in.");
				logged_in = true;
			}
			else {
				System.out.println("Invalid login.");
				login();
				
			}
//		}
	}
	
}
