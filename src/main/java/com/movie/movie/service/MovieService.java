package com.movie.movie.service;

import com.movie.movie.entity.Movie;
import com.movie.movie.exception.InvalidYearRangeException;
import com.movie.movie.exception.MovieNotFoundException;
import com.movie.movie.mapper.MovieMapper;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {
    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    /**
     * titleStartsWith, fromYear, toYearの値がnullでない場合、validateYearRangeメソッドを呼び出す
     * それ以外の場合、findAllメソッドを呼び出す
     */
    public List<Movie> getMovies(String titleStartsWith, Integer fromYear, Integer toYear) {
        if (titleStartsWith != null || (fromYear != null && toYear != null)) {
            return this.movieMapper.validateYearRange(fromYear, toYear);
        } else {
            return findAll();
        }
    }

    //findAllメソッドで全ての映画情報を取得する
    public List<Movie> findAll() {
        return this.movieMapper.findAll();
    }

    /**
     * isYearRangeValidメソッドでfromYear <= toYearかどうかを検証する
     * fromYear > toYearの場合、InvalidYearRangeExceptionをスローする
     */
    public void validateYearRange(Integer fromYear, Integer toYear) {
        if (!isYearRangeValid(fromYear, toYear)) {
            throw new InvalidYearRangeException("Invalid year range: fromYear must be less than or equal to toYear");
        }
    }

    @AssertTrue(message = "Invalid year range: fromYear must be less than or equal to toYear")
    public boolean isYearRangeValid(Integer fromYear, Integer toYear) {
        return fromYear <= toYear;
    }

    //findByIdメソッドでidに一致する映画情報を取得する
    public Movie findMovieById(Integer id) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieMapper.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new MovieNotFoundException("movie not found");
        }
    }

    //title, releaseYearを持つ映画情報を登録する
    public Movie insert(String title, Integer releaseYear) {
        Movie movie = new Movie(null, title, releaseYear);
        movieMapper.insert(movie);
        return movie;
    }

    //idに一致する映画情報のtitle, releaseYearを更新する
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

    //idに一致する映画情報を削除する
    public void delete(Integer id) {
        Optional<Movie> movie = this.movieMapper.findById(id);
        if (movie.isEmpty()) {
            throw new MovieNotFoundException("movie not found");
        }
        this.movieMapper.delete(id);
    }
}
