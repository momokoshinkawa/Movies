package com.movie.movie.service;

import com.movie.movie.entity.Movie;
import com.movie.movie.exception.InvalidYearRangeException;
import com.movie.movie.exception.MovieNotFoundException;
import com.movie.movie.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {
    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }


    public List<Movie> getMovies(String titleStartsWith, Integer fromYear, Integer toYear) {
        if (titleStartsWith != null) {
            return findMoviesByTitleStartsWith(titleStartsWith);
        } else if (fromYear != null && toYear != null) {
            return findMoviesByYearRange(fromYear, toYear);
        } else {
            return findAll();
        }
    }


    public List<Movie> findAll() {
        return this.movieMapper.findAll();
    }


    public Movie findMovieById(Integer id) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieMapper.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new MovieNotFoundException("movie not found");
        }
    }


    public List<Movie> findMoviesByTitleStartsWith(String titleStartsWith) {
        return this.movieMapper.findMoviesByTitleStartsWith(titleStartsWith);
    }


    public List<Movie> findMoviesByYearRange(Integer fromYear, Integer toYear) throws InvalidYearRangeException {
        if (fromYear >= toYear || fromYear < 0 || toYear < 0) {
            throw new InvalidYearRangeException("Invalid year");
        } else {
            return this.movieMapper.findMoviesByYearRange(fromYear, toYear);
        }
    }
}