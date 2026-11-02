package com.platzi.ver_pelis_plus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.platzi.ver_pelis_plus.entities.MovieEntity;
import com.platzi.ver_pelis_plus.mapper.MovieMapper;
import com.platzi.ver_pelis_plus.repository.MovieEntityRepository;
import com.platzi.ver_pelis_plus.repository.MovieRepository;
import com.platzi.ver_pelis_plus.services.MovieService;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;

class MovieRepositoryTest {
    @Test
    void serviceQueriesDatabaseOnceAndPreservesAllMappedFields() {
        MovieRepository repository = mock(MovieRepository.class);
        MovieEntity entity = new MovieEntity("Prueba", 120, "ACCION", LocalDate.of(2026, 1, 1),
                new BigDecimal("4.50"), "D");
        entity.setId(7L);
        when(repository.findAll()).thenReturn(List.of(entity));
        MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
        MovieService service = new MovieService(new MovieEntityRepository(repository, mapper));
        List<MovieDto> result = service.getAll();
        assertEquals(List.of(new MovieDto(7L, "Prueba", "ACCION", LocalDate.of(2026, 1, 1),
                120, new BigDecimal("4.50"), "D")), result);
        verify(repository, times(1)).findAll();
    }
}
