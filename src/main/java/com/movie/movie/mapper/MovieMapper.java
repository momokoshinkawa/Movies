package com.movie.movie.mapper;

import com.movie.movie.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MovieMapper {
    @Select("SELECT * FROM movies")
    List<Movie> findAll();

    @Select("SELECT * FROM movies WHERE id = #{id}")
    Optional<Movie> findById(Integer id);

    @Select("SELECT * FROM movies WHERE title LIKE CONCAT(#{titleStartsWith}, '%')")
    List<Movie> findMoviesByTitleStartsWith(String titleStartsWith);

    @Select("SELECT * FROM movies WHERE release_year BETWEEN #{fromYear} AND #{toYear}")
    List<Movie> findMoviesByYearRange(Integer fromYear, Integer toYear);
}
