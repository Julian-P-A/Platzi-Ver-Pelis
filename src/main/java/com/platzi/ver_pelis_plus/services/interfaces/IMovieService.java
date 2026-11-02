package com.platzi.ver_pelis_plus.services.interfaces;

import java.util.List;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;

public interface IMovieService {
	
		List<MovieDto> getAll();

}
