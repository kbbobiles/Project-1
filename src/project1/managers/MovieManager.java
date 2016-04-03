package project1.managers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MovieManager {
	
	// Returns a list of movies associated with a star's ID
	public static List<Map<String, Object>> moviesWithStarID(int starID) throws SQLException {

		String selectSQL = "select m.id as ID, m.title as Title, m.year as Year, m.director as Director, m.banner_url as 'Banner URL', m.trailer_url as 'Trailer URL' from stars s, movies m, stars_in_movies sim where s.id = sim.star_id and m.id = sim.movie_id and s.id = %d";
		List<Map<String, Object>> movies = DBManager.executeSelectSQL(String.format(selectSQL, starID));
		
		return movies;
	}
	

}
