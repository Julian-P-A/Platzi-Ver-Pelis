package com.platzi.ver_pelis_plus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.ver_pelis_plus.services.MovieService;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;


@RestController
public class MovieController {
	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movies")
	public List<MovieDto> getAll() {
		return this.movieService.getAll();
	}
}
