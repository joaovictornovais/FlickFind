package br.com.flickfind.catalog.domain.movie;

import lombok.Data;

import java.util.Set;

@Data
public class MovieResponse {

    private int page;
    private Set<Movie> results;
    private int total_pages;
    private int total_results;

}
