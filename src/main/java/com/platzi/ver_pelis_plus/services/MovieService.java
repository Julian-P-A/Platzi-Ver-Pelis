package com.platzi.ver_pelis_plus.services;

import org.springframework.stereotype.Service;
import java.util.List;


import com.platzi.ver_pelis_plus.services.dto.MovieDto;
import com.platzi.ver_pelis_plus.services.interfaces.IMovieService;

@Service
public class MovieService{
	
	private final IMovieService iMovieService;
	
	public MovieService(IMovieService movieService) {
		this.iMovieService = movieService;
	}

	public List<MovieDto> getAll() {
		return iMovieService.getAll();
	}
	
}
