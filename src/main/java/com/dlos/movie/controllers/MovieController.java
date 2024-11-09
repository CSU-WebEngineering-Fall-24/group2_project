package com.dlos.movie.controllers;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController
{
	private final MovieService _movieService;

	@Autowired
	public MovieController(@Qualifier("CacheMovieServiceImpl") final MovieService movieService) { _movieService = movieService; }

	@GetMapping("/search")
	public Movie[] Search(@RequestParam("title") String title, @RequestParam("years") String years, @RequestParam("genres") String genres)
	{
		return _movieService.Search(title, years, genres);
	}
}
