package com.dlos.movie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member
{
	private String id;
	private String name;
	private String shortBio;
	private String longBio;
	private String shortBioImageUrl;
	private String longBioImageUrl;
}
