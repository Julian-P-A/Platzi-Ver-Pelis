package com.platzi.ver_pelis_plus.repository;

import org.springframework.data.repository.CrudRepository;

import com.platzi.ver_pelis_plus.entities.MovieEntity;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

}
