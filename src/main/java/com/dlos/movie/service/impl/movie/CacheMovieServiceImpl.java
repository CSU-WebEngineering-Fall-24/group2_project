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

	private static final Map<String, Movie> s_movies = new HashMap<String, Movie>();

	private static final ArrayList<String> s_moviesCache = new ArrayList<String>();

	private static final Object s_movieLockObject = new Object();

	private static final Map<String, Movie[]> s_movieList = new HashMap<String, Movie[]>();

	private static final ArrayList<String> s_movieListSearchCache = new ArrayList<String>();

	private static final Object s_listLockObject = new Object();

	private final MovieService _movieService;

	public CacheMovieServiceImpl(@Qualifier("MovieServiceImpl") MovieService movieService)
	{
		_movieService = movieService;
	}

	@Override
	public Movie GetMovie(String id)
	{
		PruneCache(s_movieLockObject, s_movies, s_moviesCache);

		if ((!s_movies.containsKey(id)) || (s_movies.get(id) == null))
		{
			synchronized (s_movieLockObject)
			{
				s_movies.put(id, _movieService.GetMovie(id));
				s_moviesCache.add(id);
			}
		}

		return s_movies.get(id);
	}

	@Override
	public Movie[] Search(String title, String years, String types)
	{
		PruneCache(s_listLockObject, s_movieList, s_movieListSearchCache);

		String key = title.replaceAll(" ", "_") + years + types;
		if (!s_movieList.containsKey(key) || (s_movieList.get(key) == null))
		{
			synchronized (s_listLockObject)
			{
				s_movieList.put(key, _movieService.Search(title, years, types));
				s_movieListSearchCache.add(key);
			}
		}

		return s_movieList.get(key);
	}

	private void PruneCache(Object lock, Map<String, ?> map, ArrayList<String> cache)
	{
		if (cache.size() > MAX_COUNT)
		{
			int countToRemove = cache.size() - MAX_COUNT;
			String[] searchesToRemove = new String[countToRemove];
			synchronized (lock)
			{
				for (int i = 0; i < countToRemove; ++i)
				{
					String removeKey = cache.removeFirst();
					map.remove(removeKey);
				}
			}
		}
	}
}
