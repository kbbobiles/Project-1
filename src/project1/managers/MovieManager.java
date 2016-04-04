package project1.managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieManager {
	
	// Returns a list of movies associated with a star's ID
	public static List<Map<String, Object>> moviesWithStarID(int starID) throws SQLException {

		String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.id = %d";
		List<Map<String, Object>> movies = DBManager.executeSelectSQL(String.format(selectSQL, starID));
		
		return movies;
	}
	
	// Returns a list of movies using full name
	public static List<Map<String, Object>> moviesWithStarFullName(String firstName, String lastName) throws SQLException {
		
		String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.first_name = '%s' and s.last_name = '%s'";
		List<Map<String, Object>> movies = DBManager.executeSelectSQL(String.format(selectSQL, firstName, lastName));
		
		return movies;
	}
	
	// Returns a list of movies using first name
	public static List<Map<String, Object>> moviesWithStarFirstName(String firstName) throws SQLException {
		
		String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.first_name = '%s'";
		List<Map<String, Object>> movies = DBManager.executeSelectSQL(String.format(selectSQL, firstName));
		
		return movies;
	}
	
	// Returns a list of movies using last name
	public static List<Map<String, Object>> moviesWithStarLastName(String lastName) throws SQLException {
			
		String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.last_name = '%s'";
		List<Map<String, Object>> movies = DBManager.executeSelectSQL(String.format(selectSQL, lastName));
			
		return movies;
	}
	
	// Handles cases where first name and/or last name is given/not given
	public static List<Map<String, Object>> getMoviesWithName(String firstName, String lastName) throws SQLException {
		if (!"".equals(firstName) && !"".equals(lastName)) {	// Using both firstName and lastName
			return moviesWithStarFullName(firstName, lastName);
		}
		else if (!"".equals(firstName)) {	// Using only firstName
			return moviesWithStarFirstName(firstName);
		}
		else if (!"".equals(lastName)) {	// Using only lastName
			return moviesWithStarLastName(lastName);
		}
		else {
			return new ArrayList<Map<String, Object>>();
		}
	}
	

}
