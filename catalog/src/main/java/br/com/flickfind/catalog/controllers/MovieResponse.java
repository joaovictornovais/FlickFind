package br.com.flickfind.catalog.controllers;

import br.com.flickfind.catalog.domain.movie.Movie;
import lombok.Data;

import java.util.Set;

@Data
public class MovieResponse {

    private int page;
    private Set<Movie> results;
    private int total_pages;
    private int total_results;

}
