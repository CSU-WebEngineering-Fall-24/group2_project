package com.dlos.movie.service.impl.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.domain.MovieJson;
import com.dlos.movie.domain.SearchResults;
import com.dlos.movie.service.movie.ApiKeyService;
import com.dlos.movie.service.movie.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Component
@Qualifier("MovieServiceImpl")
public class MovieServiceImpl implements MovieService
{
	private final String API_URL = "http://www.omdbapi.com?apikey=";

	private String _searchApiUrl = "";

	private String _getMovieApiUrl = "";

	private Boolean _apiKeyLoaded = false;

	private RestTemplate _restTemplate = new RestTemplate();

	public MovieServiceImpl(RestTemplateBuilder restTemplateBuilder, ApiKeyService apiKeyService)
	{
		String apiKey = apiKeyService.getKey();
		_apiKeyLoaded = apiKey != null;
		_searchApiUrl = API_URL + apiKey + "&r=json&s=";
		_getMovieApiUrl = API_URL + apiKey + "&r=json&i=";
		_restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Movie getMovie(String id)
	{
		if (!_apiKeyLoaded)
		{
			return null;
		}

		try
		{
			String json = _restTemplate.getForObject(_getMovieApiUrl + id, String.class);

			ObjectMapper mapper = JsonMapper.builder()
					.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
					.build();

			MovieJson movie = mapper.readValue(json, new TypeReference<MovieJson>()
			{
			});
			if ((movie == null) || (!movie.getResponse().equals("True")))
			{
				return null;
			}

			return movie.toMovie();
		} catch (JsonProcessingException e)
		{
			System.out.println("An exception occurred: " + e);
		}

		return null;
	}

	@Override
	public Movie[] Search(String search, String years, String types)
	{
		if (!_apiKeyLoaded)
		{
			return new Movie[0];
		}

		String[] yearList = new String[0];
		int yearCount = 0;
		String[] genreList = new String[0];
		int genreCount = 0;

		if ((years != null) && !years.isEmpty())
		{
			yearList = years.split(",");
			yearCount = yearList.length;
		}

		if ((types != null) && !types.isEmpty())
		{
			genreList = types.split(",");
			genreCount = genreList.length;
		}

		String encodedTitle = URLEncoder.encode(search, StandardCharsets.UTF_8);
		String baseUrl = _searchApiUrl + encodedTitle;
		MovieJson[] movieJsons = null;

		int totalUrls = yearCount * genreCount;
		if (totalUrls == 0)
		{
			movieJsons = querySingleUrl(baseUrl);
		} else
		{
			String[] urls = createUrls(baseUrl, yearList, genreList);
			movieJsons = queryAllUrls(urls);
		}

		Movie[] movies = new Movie[movieJsons.length];
		for (int i = 0; i < movieJsons.length; ++i)
		{
			movies[i] = movieJsons[i].toMovie();
		}
		return movies;
	}

	private MovieJson[] querySingleUrl(String url)
	{
		return queryAllUrls(new String[]{url});
	}

	private String[] createUrls(String baseUrl, String[] years, String[] types)
	{
		int yearCount = years.length;
		int typeCount = types.length;
		int totalUrls = yearCount * typeCount;

		String[] urls = new String[totalUrls == 0 ? 1 : totalUrls];
		if ((yearCount > 0) && (typeCount > 0))
		{
			int index = 0;
			for (String year : years)
			{
				String url = baseUrl + "&y=" + year;
				for (String type : types)
				{
					String nextUrl = url + "&type=" + type;
					urls[index] = nextUrl;
					++index;
				}
			}
		} else if (yearCount > 0)
		{
			for (int y = 0; y < yearCount; ++y)
			{
				urls[y] = baseUrl + "&y=" + y;
			}
		} else
		{
			for (int g = 0; g < typeCount; ++g)
			{
				urls[g] = baseUrl + "&type=" + g;
			}
		}

		return urls;
	}

	private MovieJson[] queryAllUrls(String[] urls)
	{
		final int maxPageQueries = 5;
		final double omdbReturnCount = 10;

		List<MovieJson> movieJsonList = new ArrayList<MovieJson>();
		for (String url : urls)
		{
			int currentPage = 1;
			int pageCount = 1;
			try
			{
				do
				{
					String json = _restTemplate.getForObject(url + "&page=" + currentPage, String.class);

					ObjectMapper mapper = JsonMapper.builder()
							.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
							.build();

					SearchResults results = mapper.readValue(json, new TypeReference<SearchResults>() { });
					if ((results == null) || (!results.getResponse().equals("True")))
					{
						continue;
					}

					++currentPage;
					pageCount =  (int)Math.ceil(Integer.parseInt(results.getTotalResults()) / omdbReturnCount);

					movieJsonList.addAll(Arrays.asList(results.getSearch()));
				} while ((currentPage <= maxPageQueries) && (currentPage <= pageCount));
			} catch (JsonProcessingException e)
			{
				System.out.println("An exception occurred: " + e);
			}
		}

		return movieJsonList.toArray(new MovieJson[0]);
	}
}
