package com.platzi.ver_pelis_plus.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.platzi.ver_pelis_plus.mapper.MovieMapper;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;
import com.platzi.ver_pelis_plus.services.interfaces.IMovieService;

@Repository
public class MovieEntityRepository implements IMovieService {
	private final MovieRepository movieRepository;
	private final MovieMapper movieMapper;

	public MovieEntityRepository(MovieRepository movieRepository, MovieMapper movieMapper) {
		this.movieRepository = movieRepository;
		this.movieMapper = movieMapper;
	}

	@Override
	public List<MovieDto> getAll() {
		return movieMapper.toMovieDto(this.movieRepository.findAll());
	}

}
