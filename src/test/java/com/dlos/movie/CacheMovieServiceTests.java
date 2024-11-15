package com.dlos.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.domain.Rating;
import com.dlos.movie.service.impl.movie.CacheMovieServiceImpl;
import com.dlos.movie.service.movie.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheMovieServiceTests
{

	private final Movie terminator = new Movie()
	{{
		setTitle("Terminator");
		setYear("1991");
		setRated("N/A");
		setReleased("");
		setRuntime("39 min");
		setGenres(new String[] { "Short", "Action", "Sci-Fi" });
		setDirectors(new String[] { "Ben Hernandez" });
		setWriters(new String[] { "James Cameron", "Ben Hernandez" });
		setActors(new String[] { "Loris Basso", "James Callahan", "Debbie Medows" });
		setPlot("A cyborg comes from the future, to kill a girl named Sarah Lee.");
		setLanguage(new String[] { "English" });
		setCountry("United States");
		setAwards("N/A");
		setPoster("N/A");
		setRatings(new Rating[]
		{
			new Rating(){{
				setSource("Internet Movie Database");
				setValue("6.1/10");
			}}
		});
		setMetascore("N/A");
		setImdbRating("6.1");
		setImdbID("tt5817168");
		setImdbVotes("42");
		setType("movie");
		setDVD("N/A");
		setBoxOffice("N/A");
		setProduction("N/A");
		setWebsite("N/A");
		setResponse("True");
	}};

	private final Movie hercules = new Movie()
	{{
		setTitle("Hercules");
		setYear("1997");
		setRated("G");
		setReleased("27 Jun 1997");
		setRuntime("93 min");
		setGenres(new String[] { "Animation", "Action", "Adventure" });
		setDirectors(new String[] {"Ron Clements", "John Musker" });
		setWriters(new String[] { "Ron Clements", "John Musker", "Don McEnery" });
		setActors(new String[] { "Tate Donovan", " Susan Egan", "James Woods" });
		setPlot("The sons of Zeus and Hera is stripped of his immortality as an infant and must become a true hero in order to reclaim it.");
		setLanguage(new String[] { "English", "Spanish", "Greek" });
		setCountry("United States");
		setAwards("Nominated for 1 Oscar. 9 wins & 16 nominations total");
		setPoster("https://m.media-amazon.com/images/M/MV5BMmUyZGY1ZjItMWYwZS00MjJjLTg1YzAtY2Q5OTA4MmFhYzFjXkEyXkFqcGc@._V1_SX300.jpg");
		setRatings(new Rating[]
		{
			new Rating(){{
				setSource("Internet Movie Database");
				setValue("7.3/10");
			}},
			new Rating(){{
				setSource("Rotten Tomatoes");
				setValue("82%");
			}},
			new Rating(){{
				setSource("Metacritic");
				setValue("74/100");
			}}
		});
		setMetascore("74");
		setImdbRating("7.3");
		setImdbID("tt0119282");
		setImdbVotes("261,230");
		setType("movie");
		setDVD("N/A");
		setBoxOffice("$99,112,101");
		setProduction("N/A");
		setWebsite("N/A");
		setResponse("True");
	}};

	private final Movie[] Movies = {terminator, hercules};

	@Test
	public void MovieSearchCacheTest()
	{
		MovieService movieService = Mockito.mock(MovieService.class);
		Mockito.when(movieService.Search("1", "1", "1")).thenReturn(Movies);
		Mockito.when(movieService.Search("2", "2", "2")).thenReturn(Movies);
		Mockito.when(movieService.Search("3", "3", "3")).thenReturn(Movies);
		Mockito.when(movieService.Search("4", "4", "4")).thenReturn(Movies);

		CacheMovieServiceImpl movieCache = new CacheMovieServiceImpl(movieService);
		movieCache.setMaxCacheCount(2);

		Movie[] movies = movieCache.Search("1", "1", "1");
		Assertions.assertEquals(1, movieCache.getMovieSearchCacheCount());
		Assertions.assertEquals(0, movieCache.getMovieSearchCacheHitCount());

		movies = movieCache.Search("2", "2", "2");
		movies = movieCache.Search("1", "1", "1");
		movies = movieCache.Search("2", "2", "2");

		Assertions.assertEquals(2, movieCache.getMovieSearchCacheCount());
		Assertions.assertEquals(2, movieCache.getMovieSearchCacheHitCount());

		movies = movieCache.Search("1", "1", "1");
		movies = movieCache.Search("2", "2", "2");

		Assertions.assertEquals(2, movieCache.getMovieSearchCacheCount());
		Assertions.assertEquals(4, movieCache.getMovieSearchCacheHitCount());

		movies = movieCache.Search("3", "3", "3");
		movies = movieCache.Search("4", "4", "4");

		Assertions.assertEquals(2, movieCache.getMovieSearchCacheCount());
		Assertions.assertEquals(4, movieCache.getMovieSearchCacheHitCount());

		movies = movieCache.Search("3", "3", "3");
		movies = movieCache.Search("4", "4", "4");

		Assertions.assertEquals(2, movieCache.getMovieSearchCacheCount());
		Assertions.assertEquals(6, movieCache.getMovieSearchCacheHitCount());
	}

	@Test
	public void MovieIdCacheTest()
	{
		MovieService movieService = Mockito.mock(MovieService.class);
		Mockito.when(movieService.getMovie(terminator.getImdbID())).thenReturn(terminator);
		Mockito.when(movieService.getMovie(terminator.getImdbID() + "_2")).thenReturn(terminator);
		Mockito.when(movieService.getMovie(hercules.getImdbID())).thenReturn(hercules);
		Mockito.when(movieService.getMovie(hercules.getImdbID() + "_2")).thenReturn(hercules);

		CacheMovieServiceImpl movieCache = new CacheMovieServiceImpl(movieService);
		movieCache.setMaxCacheCount(2);

		Movie movies = movieCache.getMovie(terminator.getImdbID());
		Assertions.assertEquals(1, movieCache.getMovieIdsCacheCount());
		Assertions.assertEquals(0, movieCache.getMovieIdCacheHitCount());

		movies = movieCache.getMovie(hercules.getImdbID());
		movies = movieCache.getMovie(terminator.getImdbID());
		movies = movieCache.getMovie(hercules.getImdbID());

		Assertions.assertEquals(2, movieCache.getMovieIdsCacheCount());
		Assertions.assertEquals(2, movieCache.getMovieIdCacheHitCount());

		movies = movieCache.getMovie(terminator.getImdbID());
		movies = movieCache.getMovie(hercules.getImdbID());

		Assertions.assertEquals(2, movieCache.getMovieIdsCacheCount());
		Assertions.assertEquals(4, movieCache.getMovieIdCacheHitCount());

		movies = movieCache.getMovie(terminator.getImdbID() + "_2");
		movies = movieCache.getMovie(hercules.getImdbID() + "_2");

		Assertions.assertEquals(2, movieCache.getMovieIdsCacheCount());
		Assertions.assertEquals(4, movieCache.getMovieIdCacheHitCount());

		movies = movieCache.getMovie(terminator.getImdbID() + "_2");
		movies = movieCache.getMovie(hercules.getImdbID() + "_2");

		Assertions.assertEquals(2, movieCache.getMovieIdsCacheCount());
		Assertions.assertEquals(6, movieCache.getMovieIdCacheHitCount());
	}
}
