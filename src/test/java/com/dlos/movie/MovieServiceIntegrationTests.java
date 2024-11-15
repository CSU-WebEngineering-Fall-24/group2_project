package com.dlos.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.service.impl.movie.ApiKeyServiceImpl;
import com.dlos.movie.service.impl.movie.BufferedReaderProviderImpl;
import com.dlos.movie.service.impl.movie.CacheMovieServiceImpl;
import com.dlos.movie.service.impl.movie.MovieServiceImpl;
import com.dlos.movie.service.movie.ApiKeyService;
import com.dlos.movie.service.movie.BufferedReaderProvider;
import com.dlos.movie.service.movie.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootTest
public class MovieServiceIntegrationTests
{
	@Test
	public void SearchMoviesTest()
	{
		RestTemplateBuilder builder = new RestTemplateBuilder();
		BufferedReaderProvider bufferedReaderProvider = new BufferedReaderProviderImpl();
		ApiKeyService apiKeyService = new ApiKeyServiceImpl(bufferedReaderProvider);
		MovieService movieService = new MovieServiceImpl(builder, apiKeyService);
		MovieService cacheMovieService = new CacheMovieServiceImpl(movieService);

		Movie[] movies = cacheMovieService.Search("The Lion King", null, null);
		Assertions.assertNotNull(movies);
		Assertions.assertTrue(movies.length > 0);
	}

	@Test
	public void GetMovieTest()
	{
		String expectedId = "tt0119282";

		RestTemplateBuilder builder = new RestTemplateBuilder();
		BufferedReaderProvider bufferedReaderProvider = new BufferedReaderProviderImpl();
		ApiKeyService apiKeyService = new ApiKeyServiceImpl(bufferedReaderProvider);
		MovieService movieService = new MovieServiceImpl(builder, apiKeyService);
		MovieService cacheMovieService = new CacheMovieServiceImpl(movieService);

		Movie movie = cacheMovieService.getMovie(expectedId);
		Assertions.assertNotNull(movie);
		Assertions.assertEquals(expectedId, movie.getImdbID());
	}
}
