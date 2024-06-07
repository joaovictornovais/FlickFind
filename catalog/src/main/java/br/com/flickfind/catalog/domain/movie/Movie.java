package br.com.flickfind.catalog.domain.movie;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Movie {

    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private Integer id;
    private String title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private LocalDate release_date;
    private Double vote_average;
    private Integer vote_count;

}
