package com.platzi.ver_pelis_plus.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.platzi.ver_pelis_plus.entities.MovieEntity;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {

	MovieDto toMovieDto(MovieEntity movieEntity);	
	
	List<MovieDto> toMovieDto(Iterable<MovieEntity> entities);
}
