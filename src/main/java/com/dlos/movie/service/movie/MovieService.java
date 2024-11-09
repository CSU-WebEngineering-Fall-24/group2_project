package com.dlos.movie.service.movie;

import com.dlos.movie.domain.Movie;

public interface MovieService
{
	Movie[] Search(String title, String years, String genres);
}
