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
	
	// Returns a list of movies using name
	public static List<Map<String, Object>> moviesWithStarName(String firstName, String lastName) throws SQLException {
		
		List<Map<String, Object>> movies = new ArrayList<Map<String, Object>>();
		if (!"".equals(firstName) && !"".equals(lastName)) {	// Using both firstName and lastName
			String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.first_name = '%s' and s.last_name = '%s'";
			movies = DBManager.executeSelectSQL(String.format(selectSQL, firstName, lastName));
		}
		else if (!"".equals(firstName)) {	// Using only firstName
			String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.first_name = '%s'";
			movies = DBManager.executeSelectSQL(String.format(selectSQL, firstName));
		}
		else if (!"".equals(lastName)) {	// Using only lastName
			String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.last_name = '%s'";
			movies = DBManager.executeSelectSQL(String.format(selectSQL, lastName));
		}
		
		
		return movies;
	}

	
	// Queries all possible combinations for the name input
	public static List<Map<String, Object>> getMoviesWithName(String name) throws SQLException {
		String[] splitNames = name.split(" ");
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		if (splitNames.length == 0) {
			return new ArrayList<Map<String, Object>>();
		}
		else if (splitNames.length == 1) {
			rows.addAll(moviesWithStarName(splitNames[0], ""));
			rows.addAll(moviesWithStarName("", splitNames[0]));
			return rows;
		}
		else {
			for (int i = 0; i <= splitNames.length; i++) {
				//System.out.println("FIRST NAME");
				//System.out.println(join(splitNames, 0, i, " "));
				//System.out.println("LAST NAME");
				//System.out.println(join(splitNames, i, splitNames.length, " "));
				rows.addAll(moviesWithStarName(join(splitNames, 0, i, " "), join(splitNames, i, splitNames.length, " ")));
			}
		}	
		return rows;
	}
	
	public static String join(String[] stringArray, int startIndex, int endIndex, String delimiter) {
		String result = "";
		for (int i = startIndex; i < endIndex; i++) {
			result += stringArray[i] + delimiter;
		}
		if (result == "") {
			return result;
		}
		return result.substring(0, result.length()-1);
	}
	

}
