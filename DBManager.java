package project1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {

	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}
	
	// Default Constructor
	public DBManager() { }
	
	// Constructor that opens DB Connection with url, username, password
	public DBManager(String url, String username, String password) {
		try {
			//Incorporate MySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// Get a connection to database
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//========================DATABASE QUERIES==============================
	
	// Method that executes SELECT sql commands only
	public int executeUpdateInsertDeleteSQL(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		int rowsAffected = statement.executeUpdate(sql);
		return rowsAffected;
	}
	
	// Method that executes RAW SELECT sql commands only
	public List<Map<String, Object>> executeSelectSQL(String sql) throws SQLException {
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
	
	// Returns a list of movies associated with a star's ID
	public List<String> moviesWithStarID(int star_id) throws SQLException {
		List<Map<String, Object>> results = executeSelectSQL("select m.title from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.id = " + star_id);
		List<String> movies = new ArrayList<String>();
		for (int i = 0; i < results.size(); i++) {
			movies.add(results.get(i).get("title").toString());
		}
		return movies;
	}
	
	//  Returns the first ID found for given name - TODO USE PREPARED STATEMENTS
	public int selectStar(String firstName, String lastName) throws SQLException {
		Statement selectStmt = connection.createStatement();
		// TODO: Add filtering of querying by firstName, lastName, or both
		ResultSet results = selectStmt.executeQuery(String.format("select id from stars where first_name = '%s' and last_name = '%s'", firstName, lastName));
		int id = -1;
		if (results.first()) {
			id = results.getInt("id");
		}
		
		selectStmt.close();
		results.close();
		
		return id;
	}

	
	
	//=================PRINTING TO CONSOLE METHODS========================
	
	// Prints results from Select Query, all rows
	public void printSelectResults(List<Map<String, Object>> results) {
		for (int i = 0; i < results.size(); i++) {
			printMap(results.get(i));
			System.out.println();
		}
	}
	
	// Helper function for printSelectResults | Prints 1 map/row 
	private void printMap(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(String.format("%s = %s", entry.getKey(), entry.getValue()));
		}
	}
	
	public void printMovies(List<String> movies) {
		for (int i = 0; i < movies.size(); i++) {
			System.out.println(String.format("%d) %s", i+1, movies.get(i)));
		}
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
