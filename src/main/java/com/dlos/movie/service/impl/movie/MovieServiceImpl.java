package com.dlos.movie.service.impl.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.domain.MovieJson;
import com.dlos.movie.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private final String API_KEY_FILE = "api.key";

	private final String API_URL = "http://www.omdbapi.com/?apikey=";

	private String _apiUrl = "";

	private Boolean _apiKeyLoaded = false;

	public MovieServiceImpl()
	{
		_apiUrl = "";

		try (BufferedReader reader = new BufferedReader(new FileReader(API_KEY_FILE)))
		{
			String line = reader.readLine();

			if ((line == null) || line.isEmpty())
			{
				return;
			}

			_apiUrl = API_URL + line + "&s=";
			_apiKeyLoaded = true;
		} catch (FileNotFoundException e)
		{
			System.out.println("API Key File Not Found");
		} catch (IOException e)
		{
			System.out.println("An IO Exception occurred: " + e);
		} catch (Exception e)
		{
			System.out.println("An unknown Exception occurred: " + e);
		}
	}

	@Override
	public Movie[] Search(String title, String years, String genres)
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

		if ((genres != null) && !genres.isEmpty())
		{
			genreList = genres.split(",");
			genreCount = genreList.length;
		}

		String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
		String baseUrl = _apiUrl + encodedTitle;
		MovieJson[] movieJsons = null;

		int totalUrls = yearCount * genreCount;
		if (totalUrls == 0)
		{
			movieJsons = querySingleUrl(baseUrl);
		}
		else
		{
			String[] urls = createUrls(baseUrl, yearList, genreList);
			movieJsons = queryAllUrls(urls);
		}

		Movie[] movies = new Movie[movieJsons.length];
		for (int i = 0; i < movieJsons.length; ++i)
		{
			movies[i] = movieJsons[i].ToMovie();
		}
		return movies;
	}

	private MovieJson[] querySingleUrl(String url)
	{
		RestTemplate restTemplate = new RestTemplate();
		MovieJson[] movieJsons = restTemplate.getForObject(url, MovieJson[].class);
		if ((movieJsons == null) || (movieJsons.length == 0))
		{
			return new MovieJson[0];
		}

		return movieJsons;
	}

	private String[] createUrls(String baseUrl, String[] years, String[] genres)
	{
		int yearCount = years.length;
		int genreCount = genres.length;
		int totalUrls = yearCount * genreCount;

		String[] urls = new String[totalUrls == 0 ? 1 : totalUrls];
		if ((yearCount > 0) && (genreCount > 0))
		{
			int index = 0;
			for (String year : years)
			{
				String url = baseUrl + "&y=" + year;
				for (String genre : genres)
				{
					String nextUrl = url + "&type=" + genre;
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
			for (int g = 0; g < genreCount; ++g)
			{
				urls[g] = baseUrl + "&type=" + g;
			}
		}

		return urls;
	}

	private MovieJson[] queryAllUrls(String[] urls)
	{
		RestTemplate restTemplate = new RestTemplate();
		List<MovieJson> movieJsonList = new ArrayList<MovieJson>();
		for (String url : urls)
		{
			MovieJson[] json = restTemplate.getForObject(url, MovieJson[].class);
			if ((json == null) || (json.length == 0))
			{
				continue;
			}
			movieJsonList.addAll(Arrays.asList(json));
		}

		return movieJsonList.toArray(new MovieJson[0]);
	}
}