package project1.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project1.DBManager;

public class MovieManager {
	
	// Returns a list of movies associated with a star's ID
	public static List<String> moviesWithStarID(int starID) throws SQLException {
		
		String selectSQL = "select m.title from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.id = ?";
		PreparedStatement statement = DBManager.getConnection().prepareStatement(selectSQL);
		
		statement.setInt(1, starID);
		ResultSet results = statement.executeQuery();
		
		List<String> movies = new ArrayList<String>();
		while (results.next()) {
			movies.add(results.getString("title"));
		}
		
		statement.close();
		results.close();
		
		return movies;
	}
	
	// Prints the list of movies
	public static void printMovies(List<String> movies) {
		for (int i = 0; i < movies.size(); i++) {
			System.out.println(String.format("%d) %s", i+1, movies.get(i)));
		}
	}

}
