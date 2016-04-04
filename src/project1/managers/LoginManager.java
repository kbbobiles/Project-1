package project1.managers;

import java.util.Scanner;

public class LoginManager {
	public static boolean logged_in = false;
	
	// Checks to see if username and password is correct
	public static boolean isLoginAuth(String username, String password) {
		return (username == "root" && password == "password");
	}
	
	public static void login(Scanner sc) {		
		System.out.println("Enter Username (q to quit): ");
		String username = sc.nextLine();
		if (!quit_program(username)) {
			System.out.println("Enter Password (q to quit): ");
			String password = sc.nextLine();
			if (!quit_program(password)) {
				if (DBManager.setConnection("jdbc:mysql:///moviedb", username, password) == false) {
					login(sc);
				}
				else {
					logged_in = true;
				}
			}
		}
	}
	
	public static boolean quit_program(String s) {
		return s.equals("Q") || s.equals("q");
	}
}
