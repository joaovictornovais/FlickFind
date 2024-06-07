package br.com.flickfind.catalog.services;

import br.com.flickfind.catalog.domain.movie.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "movies", url = "https://api.themoviedb.org/3/discover/movie")
public interface MovieService {

    @RequestMapping(method = RequestMethod.GET)
    MovieResponse getMovies(@RequestParam Map<String, String> filters);


}
