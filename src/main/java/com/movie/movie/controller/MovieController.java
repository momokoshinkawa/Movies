package com.movie.movie.controller;

import com.movie.movie.controller.request.MovieRegisterRequest;
import com.movie.movie.controller.request.MovieUpdateRequest;
import com.movie.movie.controller.response.MovieDeleteResponse;
import com.movie.movie.controller.response.MovieRegisterResponse;
import com.movie.movie.controller.response.MovieUpdateResponse;
import com.movie.movie.entity.Movie;
import com.movie.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<MovieRegisterResponse> insert(@RequestBody MovieRegisterRequest MovieRegisterRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = movieService.insert(MovieRegisterRequest.getTitle(), MovieRegisterRequest.getReleaseYear());
        URI location = uriBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri();
        MovieRegisterResponse body = new MovieRegisterResponse("movie registered successfully");
        return ResponseEntity.created(location).body(body);
    }

    @PatchMapping("/movies/{id}")
    public ResponseEntity<MovieUpdateResponse> update(@PathVariable Integer id, @RequestBody MovieUpdateRequest MovieUpdateRequest) {
        movieService.update(id, MovieUpdateRequest.getTitle(), MovieUpdateRequest.getReleaseYear());
        MovieUpdateResponse body = new MovieUpdateResponse("movie updated successfully");
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<MovieDeleteResponse> delete(@PathVariable Integer id) {
        movieService.delete(id);
        MovieDeleteResponse body = new MovieDeleteResponse("movie deleted successfully");
        return ResponseEntity.ok(body);
    }
}
