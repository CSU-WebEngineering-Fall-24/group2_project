package com.dlos.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.domain.MovieJson;
import com.dlos.movie.domain.Rating;
import com.dlos.movie.domain.SearchResults;
import com.dlos.movie.service.impl.movie.MovieServiceImpl;
import com.dlos.movie.service.movie.ApiKeyService;
import com.dlos.movie.service.movie.BufferedReaderProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class MovieServiceTests
{

	private final MovieJson terminatorJson = new MovieJson()
	{{
		setTitle("Terminator");
		setYear("1991");
		setRated("N/A");
		setReleased("");
		setRuntime("39 min");
		setGenre("Short, Action, Sci-Fi");
		setDirector("Ben Hernandez");
		setWriter("James Cameron, Ben Hernandez");
		setActors("Loris Basso, James Callahan, Debbie Medows");
		setPlot("A cyborg comes from the future, to kill a girl named Sarah Lee.");
		setLanguage("English");
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

	private final MovieJson herculesJson = new MovieJson()
	{{
		setTitle("Hercules");
		setYear("1997");
		setRated("G");
		setReleased("27 Jun 1997");
		setRuntime("93 min");
		setGenre("Animation, Action, Adventure");
		setDirector("Ron Clements, John Musker");
		setWriter("Ron Clements, John Musker, Don McEnery");
		setActors("Tate Donovan, Susan Egan, James Woods");
		setPlot("The sons of Zeus and Hera is stripped of his immortality as an infant and must become a true hero in order to reclaim it.");
		setLanguage("English, Spanish, Greek");
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

	private final MovieJson[] MovieJsonsReturn = { terminatorJson, herculesJson };

	private final SearchResults SearchResults = new SearchResults()
	{{
		setSearch(MovieJsonsReturn);
		setResponse("True");
		setTotalResults("2");
	}};

	@Test
	void TestSuccessfullyConvertMovies() {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try
		{
			json = mapper.writeValueAsString(SearchResults);
		} catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

		RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(json);

		RestTemplateBuilder builder = Mockito.mock(RestTemplateBuilder.class);
		Mockito.when(builder.build()).thenReturn(restTemplate);

		ApiKeyService apiKeyService = Mockito.mock(ApiKeyService.class);
		Mockito.when(apiKeyService.getKey()).thenReturn("fakeapikey");

		MovieServiceImpl service = new MovieServiceImpl(builder, apiKeyService);
		Movie[] returnedMovies = service.Search("", "", "");

		Assertions.assertEquals(2, returnedMovies.length);
	}

	@Test
	void TestFailedWithNoApiKey() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try
		{
			json = mapper.writeValueAsString(SearchResults);
		} catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

		RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(json);

		RestTemplateBuilder builder = Mockito.mock(RestTemplateBuilder.class);
		Mockito.when(builder.build()).thenReturn(restTemplate);

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenThrow(FileNotFoundException.class);

		ApiKeyService apiKeyService = Mockito.mock(ApiKeyService.class);
		Mockito.when(apiKeyService.getKey()).thenReturn(null);

		MovieServiceImpl service = new MovieServiceImpl(builder, apiKeyService);
		Movie[] returnedMovies = service.Search("", "", "");

		Assertions.assertEquals(0, returnedMovies.length);
	}

	@Test
	void TestSucceedsWithNoReturnedMovies() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try
		{
			SearchResults searchResults = new SearchResults()
			{{
				setResponse("False");
				setTotalResults("0");
			}};

			json = mapper.writeValueAsString(searchResults);
		} catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

		RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(json);

		RestTemplateBuilder builder = Mockito.mock(RestTemplateBuilder.class);
		Mockito.when(builder.build()).thenReturn(restTemplate);

		BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine()).thenReturn("fakeapikey");

		BufferedReaderProvider readerProvider = Mockito.mock(BufferedReaderProvider.class);
		Mockito.when(readerProvider.createBufferedReader(Mockito.any())).thenReturn(reader);

		ApiKeyService apiKeyService = Mockito.mock(ApiKeyService.class);
		Mockito.when(apiKeyService.getKey()).thenReturn(null);

		MovieServiceImpl service = new MovieServiceImpl(builder, apiKeyService);
		Movie[] returnedMovies = service.Search("", "", "");

		Assertions.assertEquals(0, returnedMovies.length);
	}
}
