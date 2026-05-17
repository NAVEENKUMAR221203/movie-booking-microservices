package com.example.MovieService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.MovieService.entity.Movie;
import com.example.MovieService.repository.MovieRepository;
import com.example.MovieService.service.MovieService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testAddMovie() {

        Movie movie = new Movie(
                1L,
                "Leo",
                "Tamil",
                165
        );

        when(movieRepository.save(movie))
                .thenReturn(movie);

        Movie saved = movieService.addMovie(movie);

        assertEquals("Leo", saved.getMovieName());

        verify(movieRepository, times(1))
                .save(movie);
    }

    @Test
    void testGetAllMovies() {

        when(movieRepository.findAll())
                .thenReturn(
                        List.of(
                                new Movie(1L,"Leo","Tamil",165)
                        )
                );

        List<Movie> movies = movieService.getAllMovies();

        assertEquals(1, movies.size());
    }
}
