package com.dlos.movie.service.impl.movie;

import com.dlos.movie.domain.Movie;
import com.dlos.movie.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
@Qualifier("CacheMovieServiceImpl")
public class CacheMovieServiceImpl implements MovieService
{
	private static final int MAX_COUNT = 100;

	private static final Map<String, Movie[]> s_movies = new HashMap<String, Movie[]>();

	private static final ArrayList<String> s_movieCacheList = new ArrayList<String>();

	private static final Object s_lockObject = new Object();

	private final MovieService _movieService;

	public CacheMovieServiceImpl(@Qualifier("MovieServiceImpl") MovieService movieService)
	{
		_movieService = movieService;
	}

	@Override
	public Movie[] Search(String title, String years, String genres)
	{
		String key = title.replaceAll(" ", "_") + years + genres;

		if (s_movieCacheList.size() > MAX_COUNT)
		{
			int countToRemove = s_movies.size() - MAX_COUNT;
			String[] searchesToRemove = new String[countToRemove];
			synchronized (s_lockObject)
			{
				for(int i = 0; i < countToRemove; ++i)
				{
					String removeKey = s_movieCacheList.removeFirst();
					s_movies.remove(removeKey);
				}
			}
		}

		if (!s_movies.containsKey(key))
		{
			synchronized (s_lockObject)
			{
				s_movies.put(key, _movieService.Search(title, years, genres));
				s_movieCacheList.add(key);
			}
		}

		return s_movies.get(key);
	}
}
