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
        if (titleStartsWith != null || (fromYear != null && toYear != null)) {
            return this.movieMapper.findMoviesByTitleOrYear(titleStartsWith, fromYear, toYear);
        } else {
            return findAll();
        }
    }

    public List<Movie> findAll() {
        return this.movieMapper.findAll();
    }

    public List<Movie> findMoviesByTitleOrYear(String titleStartsWith, Integer fromYear, Integer toYear) throws
            InvalidYearRangeException {
        if (fromYear != null && toYear != null && fromYear > toYear) {
            throw new InvalidYearRangeException("invalid year range");
        }
        return this.movieMapper.findMoviesByTitleOrYear(titleStartsWith, fromYear, toYear);
    }

    public Movie findMovieById(Integer id) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieMapper.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new MovieNotFoundException("movie not found");
        }
    }

    public Movie insert(String title, Integer releaseYear) {
        Movie movie = new Movie(null, title, releaseYear);
        movieMapper.insert(movie);
        return movie;
    }

    public void update(Integer id, String title, Integer releaseYear) {
        Optional<Movie> optionalMovie = movieMapper.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }
        Movie movie = optionalMovie.get();
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movieMapper.update(movie);
    }

    public void delete(Integer id) {
        Optional<Movie> movie = this.movieMapper.findById(id);
        if (movie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }
        this.movieMapper.delete(id);
    }
}
