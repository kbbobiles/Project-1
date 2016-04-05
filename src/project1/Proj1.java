package project1;

import project1.managers.DBManager;
import project1.managers.PromptManager;

public class Proj1 {
	
	public static void main(String[] args) {
		
		try {
			Menu.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBManager.close();
		PromptManager.closeScanner();
	}

}
