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
	private String Director;
	private String Writer;
	private String Actors;
	private String Plot;
	private String TotalSeasons;
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

	public Movie toMovie()
	{
		final String na = "N/A";

		Movie movie = new Movie();
		movie.setTitle(Title);
		movie.setYear(Year);
		movie.setRated((Rated == null) ||  Rated.equals(na) ? null : Rated);
		movie.setReleased((Released == null ) || Released.equals(na) ? null : Released);
		movie.setRuntime(Runtime);
		movie.setGenres(Genre == null ? null :  Genre.split(","));
		movie.setDirectors(Director == null ? null :  Director.split(","));
		movie.setWriters(Writer == null ? null :  Writer.split(","));
		movie.setActors(Actors == null ? null :  Actors.split(","));
		movie.setPlot(Plot);
		movie.setTotalSeasons(TotalSeasons);
		movie.setLanguage(Language == null ? null :  Language.split(","));
		movie.setCountry(Country);
		movie.setAwards((Awards == null) || Awards.equals(na) ? null : Awards);
		movie.setPoster((Poster == null) || Poster.equals(na) ? null : Poster);
		movie.setRatings(Ratings);
		movie.setMetascore((Metascore == null) || Metascore.equals(na) ? null : Metascore);
		movie.setImdbRating((imdbRating == null) || imdbRating.equals(na) ? null : imdbRating);
		movie.setImdbID(imdbID);
		movie.setImdbVotes((imdbVotes == null) || imdbVotes.equals(na) ? null : imdbVotes);
		movie.setType(Type);
		movie.setDVD((DVD == null) || DVD.equals(na) ? null : DVD);
		movie.setBoxOffice((BoxOffice == null) || BoxOffice.equals(na) ? null : BoxOffice);
		movie.setProduction((Production == null) || Production.equals(na) ? null : Production);
		movie.setWebsite((Website == null) || Website.equals(na) ? null : Website);
		movie.setResponse(Response);

		return movie;
	}
}
