package br.com.flickfind.catalog.services;

import br.com.flickfind.catalog.controllers.MovieResponse;
import br.com.flickfind.catalog.domain.movie.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "movie", url = "https://api.themoviedb.org/3/discover/movie?language=pt-BR&api_key=${api.tmdb.key}")
public interface MovieService {

    @RequestMapping(method = RequestMethod.GET)
    MovieResponse getMovies();

}
