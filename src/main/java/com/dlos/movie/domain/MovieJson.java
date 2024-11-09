package com.dlos.movie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieJson
{
	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Directors;
	private String Writers;
	private String Actors;
	private String Plot;
	private String Language;
	private String Country;
	private String Awards;
	private String Poster;
	private Rating[] Ratings;
	private String Metascore;
	private String imdbRating;
	private String imdbID;
	private String imdbVotes;
	private String Type;
	private String DVD;
	private String BoxOffice;
	private String Production;
	private String Website;
	private String Response;

	public Movie ToMovie()
	{
		Movie movie = new Movie();
		movie.setTitle(Title);
		movie.setYear(Year);
		movie.setRated(Rated);
		movie.setReleased(Released);
		movie.setRuntime(Runtime);
		movie.setGenres(Genre.split(","));
		movie.setDirectors(Directors.split(","));
		movie.setWriters(Writers.split(","));
		movie.setActors(Actors.split(","));
		movie.setPlot(Plot);
		movie.setLanguage(Language.split(","));
		movie.setCountry(Country);
		movie.setAwards(Awards);
		movie.setPoster(Poster);
		movie.setRatings(Ratings);
		movie.setMetascore(Metascore);
		movie.setImdbRating(imdbRating);
		movie.setImdbID(imdbID);
		movie.setImdbVotes(imdbVotes);
		movie.setType(Type);
		movie.setDVD(DVD);
		movie.setBoxOffice(BoxOffice);
		movie.setProduction(Production);
		movie.setWebsite(Website);
		movie.setResponse(Response);

		return movie;
	}
}
