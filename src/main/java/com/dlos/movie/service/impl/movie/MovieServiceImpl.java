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
public class MovieServiceImpl implements MovieService {
    private final String API_URL = "http://www.omdbapi.com?apikey=";

    private String _searchApiUrl = "";

    private String _getMovieApiUrl = "";

    private Boolean _apiKeyLoaded = false;

    private RestTemplate _restTemplate = new RestTemplate();

    public MovieServiceImpl(RestTemplateBuilder restTemplateBuilder, ApiKeyService apiKeyService) {
        String apiKey = apiKeyService.getKey();
        _apiKeyLoaded = apiKey != null;
        _searchApiUrl = API_URL + apiKey + "&r=json&s=";
        _getMovieApiUrl = API_URL + apiKey + "&r=json&i=";
        _restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Movie getMovie(String id) {
        if (!_apiKeyLoaded) {
            return null;
        }

        try {
            String json = _restTemplate.getForObject(_getMovieApiUrl + id, String.class);

            ObjectMapper mapper = JsonMapper.builder()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .build();

            MovieJson movie = mapper.readValue(json, new TypeReference<MovieJson>() {
            });
            if ((movie == null) || (!movie.getResponse().equals("True"))) {
                return null;
            }

            return movie.toMovie();
        } catch (JsonProcessingException e) {
            System.out.println("An exception occurred: " + e);
        }

        return null;
    }

    @Override
    public Movie[] Search(String search, String years, String types) {
        if (!_apiKeyLoaded) {
            return new Movie[0];
        }

        // Split years and types into arrays
        String[] yearList = (years != null && !years.isEmpty()) ? years.split(",") : new String[0];
        String[] genreList = (types != null && !types.isEmpty()) ? types.split(",") : new String[0];

        String encodedTitle = URLEncoder.encode(search, StandardCharsets.UTF_8);
        String baseUrl = _searchApiUrl + encodedTitle;
        MovieJson[] movieJsons;

        if (yearList.length == 0 && genreList.length == 0) {
            movieJsons = querySingleUrl(baseUrl);
        } else {
            String[] urls = createUrls(baseUrl, yearList, genreList);
            movieJsons = queryUrls(urls);
        }

        Movie[] movies = new Movie[movieJsons.length];
        for (int i = 0; i < movieJsons.length; ++i) {
            movies[i] = movieJsons[i].toMovie();
        }
        return movies;
    }

    /**
     * Queries a single URL.
     * @param url The url to query.
     * @return The Movie JSON response.
     */
    private MovieJson[] querySingleUrl(String url) {
        return queryUrls(new String[]{url});
    }

    /**
     * Creates URLs to query based on the parameters provided.
     * @param baseUrl The base URL.
     * @param years The year(s).
     * @param types The type(s) (genres)
     * @return The created URLs.
     */
    private String[] createUrls(String baseUrl, String[] years, String[] types) {
        int yearCount = years.length;
        int typeCount = types.length;

        List<String> urls = new ArrayList<>();

        if (yearCount > 0 && typeCount > 0) {
            // Cartesian product of years and types
            for (String year : years) {
                for (String type : types) {
                    urls.add(baseUrl + "&y=" + year + "&type=" + type);
                }
            }
        } else if (yearCount > 0) {
            // Only years
            for (String year : years) {
                urls.add(baseUrl + "&y=" + year);
            }
        } else if (typeCount > 0) {
            // Only types
            for (String type : types) {
                urls.add(baseUrl + "&type=" + type);
            }
        }

        return urls.toArray(new String[0]);
    }

    /**
     * Queries all provided URLs.
     * @param urls The URLs.
     * @return The Movie JSON responses.
     */
    private MovieJson[] queryUrls(String[] urls) {
        final int maxPageQueries = 5;
        final double omdbReturnCount = 10;

        List<MovieJson> movieJsonList = new ArrayList<MovieJson>();
        for (String url : urls) {
            int currentPage = 1;
            int pageCount = 1;
            try {
                do {
                    String json = _restTemplate.getForObject(url + "&page=" + currentPage, String.class);

                    ObjectMapper mapper = JsonMapper.builder()
                            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                            .build();

                    SearchResults results = mapper.readValue(json, new TypeReference<SearchResults>() {
                    });
                    if ((results == null) || (!results.getResponse().equals("True"))) {
                        continue;
                    }

                    ++currentPage;
                    pageCount = (int) Math.ceil(Integer.parseInt(results.getTotalResults()) / omdbReturnCount);

                    movieJsonList.addAll(Arrays.asList(results.getSearch()));
                } while ((currentPage <= maxPageQueries) && (currentPage <= pageCount));
            } catch (JsonProcessingException e) {
                System.out.println("An exception occurred: " + e);
            }
        }

        return movieJsonList.toArray(new MovieJson[0]);
    }
}
