package project1.managers;

import java.sql.Date;
import java.util.Scanner;

public class PromptManager {
	public static Scanner scan = new Scanner(System.in);
	
	
	// Prompts user for a string and returns a string
	public static String promptString(String question) {
		
		System.out.print(question);
		
		return scan.nextLine().trim();
	}
	
	// Prompts user for a non-empty string and returns a string
	public static String promptRequiredString(String question) {
		
		String name = "";
		while(name.equals(""))
		{
			System.out.print(question);
			name = scan.nextLine().trim();
		}
		return name;
	}
	
	// Prompts user for a int.toString() and returns an int
	public static int promptInt(String question) {
		
		System.out.print(question);
		
		int i = -1;
		
		while(i < 0)
		{
			try
			{
				i = Integer.parseInt(scan.nextLine());
			}
			catch(Exception exception)
			{
				System.out.println("This is not an integer.");
				System.out.println(question);
			}	
		}
		
		return i;
	}
	
	// Prompts user for a Date.valueOf("yyyy-MM-dd") and returns a Date
	public static Date promptDate(String question) {
		
		try {
			System.out.print(question);
			
			boolean formatCorrect = false;
			String d = "";
			
			while(!formatCorrect)
			{
				String date = scan.nextLine();
			
				if(!date.matches("[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}"))
				{
					System.out.println("Date format incorrect. Please try again.");
					System.out.println(question);
				}
				
				else
				{
					d = date;
					formatCorrect = true;
				}
			}		
			return Date.valueOf(d.replace("/", "-"));
		} catch (IllegalArgumentException e) {
			System.out.println("Date invalid.");
			return promptDate(question);
		}
		
	}
	
	public static boolean quit_program(String s) {
		return s.equals("Q") || s.equals("q");
	}
	
	public static void closeScanner()
	{
		scan.close();
	}
	
}
