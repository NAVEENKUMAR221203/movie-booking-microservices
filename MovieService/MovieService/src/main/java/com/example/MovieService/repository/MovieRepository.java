package com.example.MovieService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieService.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    
}
