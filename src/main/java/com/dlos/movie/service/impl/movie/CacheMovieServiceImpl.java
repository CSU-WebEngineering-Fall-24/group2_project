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
public class CacheMovieServiceImpl implements MovieService {
	private final Map<String, Movie> s_movies = new HashMap<String, Movie>();

	private final ArrayList<String> s_moviesCache = new ArrayList<String>();

	private final Object s_movieLockObject = new Object();

	private final Map<String, Movie[]> s_movieList = new HashMap<String, Movie[]>();

	private final ArrayList<String> s_movieListSearchCache = new ArrayList<String>();

	private final Object s_listLockObject = new Object();

	private final MovieService _movieService;

	private int _maxCacheCount = 100;

	private int _movieIdCacheHitCount = 0;

	private int _movieSearchCacheHitCount = 0;

	public CacheMovieServiceImpl(@Qualifier("MovieServiceImpl") MovieService movieService) {
		_movieService = movieService;
	}

	@Override
	public Movie getMovie(String id) {
		++_movieIdCacheHitCount;
		if (!s_movies.containsKey(id) || (s_movies.get(id) == null)) {
			synchronized (s_movieLockObject) {
				s_movies.put(id, _movieService.getMovie(id));
				s_moviesCache.add(id);
				--_movieIdCacheHitCount;
			}

			PruneCache(s_movieLockObject, s_movies, s_moviesCache);
		}

		if (!s_movies.containsKey(id)) {
			return null;
		}

		return s_movies.get(id);
	}

	@Override
	public Movie[] Search(String search, String years, String types) {
		++_movieSearchCacheHitCount;
		String key = search.replaceAll(" ", "_") + years + types;
		if (!s_movieList.containsKey(key) || (s_movieList.get(key) == null)) {
			synchronized (s_listLockObject) {
				s_movieList.put(key, _movieService.Search(search, years, types));
				s_movieListSearchCache.add(key);
				--_movieSearchCacheHitCount;
			}

			PruneCache(s_listLockObject, s_movieList, s_movieListSearchCache);
		}

		if (!s_movieList.containsKey(key)) {
			return new Movie[0];
		}

		return s_movieList.get(key);
	}

	public int getMovieIdsCacheCount() {
		return s_movies.size();
	}

	public int getMovieIdCacheHitCount() {
		return _movieIdCacheHitCount;
	}

	public int getMovieSearchCacheCount() {
		return s_movieList.size();
	}

	public int getMovieSearchCacheHitCount() {
		return _movieSearchCacheHitCount;
	}

	public int getMaxCacheCount() {
		return _maxCacheCount;
	}

	public void setMaxCacheCount(int maxCacheCount) {
		_maxCacheCount = maxCacheCount;
	}

	private void PruneCache(Object lock, Map<String, ?> map, ArrayList<String> cache) {
		if (cache.size() > _maxCacheCount) {
			int countToRemove = cache.size() - _maxCacheCount;
			synchronized (lock) {
				for (int i = 0; i < countToRemove; ++i) {
					String removeKey = cache.removeFirst();
					map.remove(removeKey);
				}
			}
		}
	}
}
