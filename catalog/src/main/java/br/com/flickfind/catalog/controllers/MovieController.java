package br.com.flickfind.catalog.controllers;

import br.com.flickfind.catalog.domain.movie.MovieResponse;
import br.com.flickfind.catalog.dtos.FilterDTO;
import br.com.flickfind.catalog.servicesImpl.MovieServiceImpl;
import br.com.flickfind.catalog.utils.ServiceRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieServiceImpl movieService;
    private final ServiceRequest serviceRequest;

    public MovieController(MovieServiceImpl movieService, ServiceRequest serviceRequest) {
        this.movieService = movieService;
        this.serviceRequest = serviceRequest;
    }

    @GetMapping
    public ResponseEntity<MovieResponse> getMovies(@RequestParam Map<String, String> filters, HttpServletRequest request) {
        FilterDTO userFilter = serviceRequest.getUserFilter(request);
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovies(userFilter, filters));
    }

}
