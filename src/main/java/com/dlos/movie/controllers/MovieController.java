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

	@GetMapping("{id}")
	public Movie Search(@PathVariable("id") String id) { return _movieService.GetMovie(id); }

	@GetMapping("/search")
	public Movie[] Search(@RequestParam("title") String title, @RequestParam("years") String years, @RequestParam("types") String types)
	{
		return _movieService.Search(title, years, types);
	}
}
