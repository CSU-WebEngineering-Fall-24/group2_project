package com.dlos.movie.service.movie;

import com.dlos.movie.domain.Member;

public interface MemberService
{
	Member getMember(String id);

	Member[] getMembers();
}
