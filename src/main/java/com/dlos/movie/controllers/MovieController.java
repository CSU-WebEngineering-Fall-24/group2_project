package com.dlos.movie.controllers;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController
{
	private final MovieService _movieService;

	@Autowired
	public MovieController(@Qualifier("CacheMovieServiceImpl") final MovieService movieService) { _movieService = movieService; }

	/**
	 * Searches for a specific Movie based on the ID.
	 * @param id The Movie ID.
	 * @return The Movie.
	 */
	@GetMapping("{id}")
	public Movie Search(@PathVariable("id") String id) { return _movieService.getMovie(id); }

	/**
	 * Searches for a list of movies based on the parameters provided in the request.
	 * @param search The search string.
	 * @param years The year(s) the movie .
	 * @param types The type(s) or genres.
	 * @return The list of movies that meet the specified criteria.
	 */
	@GetMapping("/search")
	public Movie[] Search(@RequestParam(name = "search") String search, @RequestParam(name = "years", required = false) String years,
						  @RequestParam(name = "types", required = false) String types)
	{
		return _movieService.Search(search, years, types);
	}
}
