package com.dlos.movie.service.impl.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.service.movie.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService
{
	@Override
	public Movie[] Search(String title, String years, String genres)
	{
		return new Movie[0];
	}
}
