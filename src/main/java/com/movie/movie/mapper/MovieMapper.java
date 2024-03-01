package com.movie.movie.mapper;

import com.movie.movie.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MovieMapper {


    @Select("SELECT * FROM movies WHERE id = #{id}")
    Optional<Movie> findById(Integer id);

    @Select("SELECT * FROM movies")
    List<Movie> findAll();

    @Select("SELECT * FROM movies WHERE title LIKE CONCAT(#{titleStartsWith}, '%') OR release_year BETWEEN #{fromYear} AND #{toYear}")
    List<Movie> findMoviesByTitleOrYear(String titleStartsWith, Integer fromYear, Integer toYear);
}
