package com.dlos.movie.service.movie;

import com.dlos.movie.domain.Movie;

public interface MovieService
{
	/**
	 * Geta a specific Movie based on the ID.
	 * @param id The Movie ID.
	 * @return The Movie.
	 */
	Movie getMovie(String id);

	/**
	 * Searches for a list of movies based on the parameters provided in the request.
	 * @param search The search string.
	 * @param years The year(s) the movie .
	 * @param types The type(s) or genres.
	 * @return The list of movies that meet the specified criteria.
	 */
	Movie[] Search(String search, String years, String types);
}
