package com.dlos.movie.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member
{
	private String Id;
	private String Name;
	private String ShortBio;
	private String LongBio;
	private String ShortBioImageUrl;
	private String LongBioImageUrl;
}
