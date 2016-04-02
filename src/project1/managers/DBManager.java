package project1.managers;

import java.sql.*;
import java.util.*;

public class DBManager {

	private static Connection connection;

	//=======================GETTERS and SETTERS===========================
	
	public static void setConnection(String url, String username, String password) {
		try {
			//Incorporate MySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// Get a connection to database
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	//=========================RAW DATABASE QUERIES===================
	
	// Method that executes SELECT sql commands only
	public static int executeUpdateInsertDeleteSQL(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		int rowsAffected = statement.executeUpdate(sql);
		statement.close();
		return rowsAffected;
	}
	
	// Method that executes RAW SELECT sql commands only
	public static List<Map<String, Object>> executeSelectSQL(String sql) throws SQLException {
		// Prepares a create statement
		Statement selectStmt = connection.createStatement();
		
		// Executes SQL query and assigns results => list of rows 
		ResultSet results = selectStmt.executeQuery(sql);
		
		// Retrieves the results metadata
		ResultSetMetaData metadata = results.getMetaData();
		
		// Converts the results to a Java list of rows
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		while (results.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				row.put(metadata.getColumnName(i), results.getObject(i));
			}
			rows.add(row);
		}
	
		selectStmt.close();
		results.close();
		return rows;
	}
	
	//=================PRINTING TO CONSOLE METHODS========================
	
	// Prints results from Select Query, all rows
	public static void printSelectResults(List<Map<String, Object>> results) {
		for (int i = 0; i < results.size(); i++) {
			System.out.println(String.format("===============%d===============", i+1));
			printMap(results.get(i));
			System.out.println();
		}
	}
	
	// Helper function for printSelectResults | Prints 1 map/row 
	private static void printMap(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(String.format("%11s: %s", entry.getKey(), entry.getValue()));
		}
	}
	
	// Print out database metadata
	public static void printDatabaseMetadata() throws SQLException {
		
		ArrayList<String> tables = new ArrayList<String>();
		
		DatabaseMetaData metadata = connection.getMetaData();
		String[] types = {"TABLE"};
		ResultSet tableResults = metadata.getTables(null, "", "", types);
		
		while (tableResults.next()) {
			tables.add(tableResults.getString("TABLE_NAME"));
		}
		
		for (String tableName : tables) {
			System.out.println(String.format("Table: %s", tableName));
			System.out.println("========================");
			
			ResultSet columnsResults = metadata.getColumns(null, "", tableName, "");
			
			while (columnsResults.next()) {
				System.out.println(String.format("%s: %s", columnsResults.getString("COLUMN_NAME"), columnsResults.getString("TYPE_NAME")));
			}
			System.out.println();
			
			columnsResults.close();
		}
		
		tableResults.close();
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
