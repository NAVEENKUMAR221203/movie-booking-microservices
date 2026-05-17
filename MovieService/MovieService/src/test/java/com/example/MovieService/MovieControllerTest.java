package com.example.MovieService;

import com.example.MovieService.config.AuthTokenFilter;
import com.example.MovieService.config.JwtUtils;
import com.example.MovieService.controller.MovieController;
import com.example.MovieService.entity.Movie;
import com.example.MovieService.service.MovieService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@Import(TestSecurityConfig.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    // ✅ ADD THESE
    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddMovie() throws Exception {

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setMovieName("Leo");
        movie.setLanguage("Tamil");
        movie.setDuration(165);

        when(movieService.addMovie(movie)).thenReturn(movie);

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());
    }
}