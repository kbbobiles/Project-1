package Project1;

import java.sql.*;

public class DBContext {
	
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}
	
	// Default Constructor
	public DBContext() { }
	
	// Constructor with url, username, password
	public DBContext(String url, String username, String password) {
		try {
			//Incorporate MySQL driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// Get a connection to database
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method that executes SELECT sql commands only
	public ResultSet executeSelectSQL(String sql) throws SQLException {
		Statement select = connection.createStatement();
		return select.executeQuery(sql);
	}
	
	// Method that executes SELECT sql commands only
	public int executeUpdateSQL(String sql) throws SQLException {
		Statement select = connection.createStatement();
		return select.executeUpdate(sql);
	}
	
	/* 
	public PreparedStatement PrepareUpdateSQL(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
	*/
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
