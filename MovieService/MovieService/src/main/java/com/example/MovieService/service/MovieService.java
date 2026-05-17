package com.example.MovieService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieService.entity.Movie;
import com.example.MovieService.repository.MovieRepository;


@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;

    public Movie addMovie(Movie movie) {
        return repo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }
    
}
