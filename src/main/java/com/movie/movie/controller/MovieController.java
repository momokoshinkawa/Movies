package com.movie.movie.controller;

import com.movie.movie.entity.Movie;
import com.movie.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.findMovieById(id);
    }

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam(required = false) String titleStartsWith,
                                 @RequestParam(required = false) Integer fromYear,
                                 @RequestParam(required = false) Integer toYear) {
        return movieService.getMovies(titleStartsWith, fromYear, toYear);
    }

    @PostMapping("/movies")
    public ResponseEntity<MoveRegisterResponse> insert(@RequestBody MoveRegisterRequest MoveRegisterRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = movieService.insert(MoveRegisterRequest.getTitle(), MoveRegisterRequest.getReleaseYear());
        URI location = uriBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri();
        MoveRegisterResponse body = new MoveRegisterResponse("movie registered successfully");
        return ResponseEntity.created(location).body(body);
    }
}
