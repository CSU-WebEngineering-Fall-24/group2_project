package com.dlos.movie.service.movie;

import com.dlos.movie.domain.Movie;

public interface MovieService
{
	Movie getMovie(String id);

	Movie[] Search(String search, String years, String types);
}
