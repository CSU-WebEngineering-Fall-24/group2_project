package com.dlos.movie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResults
{
	private String response;
	private String totalResults;
	private MovieJson[] search;
}
