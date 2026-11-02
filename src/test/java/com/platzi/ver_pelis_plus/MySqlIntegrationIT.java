package com.platzi.ver_pelis_plus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.platzi.ver_pelis_plus.entities.MovieEntity;
import com.platzi.ver_pelis_plus.repository.MovieRepository;

// Se ejecuta de forma explicita con -Dtest=MySqlIntegrationIT y una base de pruebas.
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MySqlIntegrationIT {
    @Autowired
    private MovieRepository repository;
    @Autowired
    private MockMvc mvc;

    @Test
    void schemaSeedsAndMovieEndpointWorkTogether() throws Exception {
        assertTrue(repository.count() >= 10);
        mvc.perform(get("/movies")).andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Shrek")));
    }

    @Test
    void moviesCanBeSavedReadAndDeleted() {
        long before = repository.count();
        MovieEntity movie = repository.save(new MovieEntity("Prueba de integracion", 90, "ACCION",
                LocalDate.of(2026, 1, 1), null, "D"));
        assertEquals("Prueba de integracion", repository.findById(movie.getId()).orElseThrow().getTitle());
        repository.deleteById(movie.getId());
        assertEquals(before, repository.count());
    }

    @Test
    void helloWorksWithoutAnExternalAiProvider() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Ver Pelis plus")));
    }
}
