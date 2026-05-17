package com.example.MovieService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieService.entity.Movie;
import com.example.MovieService.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping("/add")
    // 🔥 FIXED: Aligned to use hasAuthority('ROLE_ADMIN') to match ShowController perfectly
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.addMovie(movie);
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }
}