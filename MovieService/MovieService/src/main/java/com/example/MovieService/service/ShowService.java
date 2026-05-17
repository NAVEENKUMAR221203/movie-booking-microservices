package com.example.MovieService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieService.entity.Show;
import com.example.MovieService.repository.ShowRepository;

@Service
public class ShowService {

    @Autowired
    private ShowRepository repo;

    public Show addShow(Show show) {
        return repo.save(show);
    }

    public List<Show> getAllShows() {
        return repo.findAll();
    }

    public List<Show> getShowsByMovie(Long movieId) {
        return repo.findByMovieId(movieId);
    }
}