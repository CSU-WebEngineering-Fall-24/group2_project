package com.dlos.movie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController
{
	@GetMapping(value = {"/", "/about", "/search", "/details"})
	public String index()
	{
		return "index";
	}
}
