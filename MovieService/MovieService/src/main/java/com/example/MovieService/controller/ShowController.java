package com.example.MovieService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieService.entity.Show;
import com.example.MovieService.service.ShowService;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService service;

    @PostMapping("/add")
    // 🔥 MAINTENANCE: Kept structural /add path and verified hasAuthority alignment
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Show addShow(@RequestBody Show show) {
        return service.addShow(show);
    }

    @GetMapping("/all")
    public List<Show> getAllShows() {
        return service.getAllShows();
    }

    @GetMapping("/movie/{movieId}")
    public List<Show> getShows(@PathVariable Long movieId) {
        return service.getShowsByMovie(movieId);
    }
}