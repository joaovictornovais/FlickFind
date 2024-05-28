package br.com.flickfind.catalog.controllers;

import br.com.flickfind.catalog.domain.movie.Movie;
import br.com.flickfind.catalog.services.MovieService;
import br.com.flickfind.catalog.servicesImpl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<MovieResponse> getMovies(@RequestParam Map<String, String> filters) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovies(filters));
    }

}
