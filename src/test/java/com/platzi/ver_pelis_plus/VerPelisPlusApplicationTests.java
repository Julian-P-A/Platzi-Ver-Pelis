package com.platzi.ver_pelis_plus;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.platzi.ver_pelis_plus.controller.HelloController;
import com.platzi.ver_pelis_plus.controller.MovieController;
import com.platzi.ver_pelis_plus.services.MovieService;
import com.platzi.ver_pelis_plus.services.dto.MovieDto;
import com.platzi.ver_pelis_plus.services.interfaces.VerPelisAiService;

@WebMvcTest({MovieController.class, HelloController.class})
class VerPelisPlusApplicationTests {
    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private MovieService movieService;
    @MockitoBean
    private VerPelisAiService aiService;

    @Test
    void moviesReturnsCatalogAsJson() throws Exception {
        MovieDto movie = new MovieDto(1L, "Shrek", "ANIMADA", LocalDate.of(2001, 5, 18),
                90, new BigDecimal("4.50"), "D");
        when(movieService.getAll()).thenReturn(List.of(movie));
        mvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Shrek"))
                .andExpect(jsonPath("$[0].duration").value(90))
                .andExpect(jsonPath("$[0].date").value("2001-05-18"))
                .andExpect(jsonPath("$[0].classification").value(4.5));
        verify(movieService).getAll();
    }

    @Test
    void emptyCatalogIsAnEmptyArray() throws Exception {
        when(movieService.getAll()).thenReturn(List.of());
        mvc.perform(get("/movies")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void helloUsesGreetingService() throws Exception {
        when(aiService.generateGreeting("Ver Pelis plus")).thenReturn("Bienvenido a Ver Pelis plus");
        mvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("Bienvenido a Ver Pelis plus"));
    }
}
