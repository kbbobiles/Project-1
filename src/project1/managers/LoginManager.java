package project1.managers;

public class LoginManager {
	
	// Checks to see if username and password is correct
	public static boolean isLoginAuth(String username, String password) {
		return (username == "root" && password == "password");
	}

}
