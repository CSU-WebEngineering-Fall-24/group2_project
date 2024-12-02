package com.dlos.movie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie
{
	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String[] Genres;
	private String[] Directors;
	private String[] Writers;
	private String[] Actors;
	private String Plot;
	private String TotalSeasons;
	private String[] Language;
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
}
