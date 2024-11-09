package com.dlos.movie.controllers;

import com.dlos.movie.domain.Member;
import com.dlos.movie.service.movie.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController
{
	private final MemberService _memberService;

	@Autowired
	public MemberController(final MemberService memberService) { _memberService = memberService; }

	@GetMapping("/{id}")
	public Member getMember(@PathVariable("id") String id) { return _memberService.getMember(id); }

	@GetMapping("/")
	public Member[] getMembers() { return _memberService.getMembers(); }
}
