package com.example.MovieService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieService.entity.Show;


@Repository
public interface ShowRepository  extends JpaRepository<Show,Long>{
    List<Show> findByMovieId(Long movieId);
    
}
