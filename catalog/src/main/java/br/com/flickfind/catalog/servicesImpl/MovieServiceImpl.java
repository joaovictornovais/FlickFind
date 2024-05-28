package br.com.flickfind.catalog.servicesImpl;

import br.com.flickfind.catalog.controllers.MovieResponse;
import br.com.flickfind.catalog.services.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieServiceImpl {

    private final MovieService movieService;

    public MovieServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Value("${api.tmdb.key}")
    private String apiKey;

    public MovieResponse getMovies(Map<String, String> filters) {
        filters.put("api_key", apiKey);
        filters.put("language", "pt-BR");
        return movieService.getMovies(filters);
    }

}
