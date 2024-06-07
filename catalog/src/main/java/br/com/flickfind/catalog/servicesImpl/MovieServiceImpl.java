package br.com.flickfind.catalog.servicesImpl;

import br.com.flickfind.catalog.domain.movie.MovieResponse;
import br.com.flickfind.catalog.dtos.FilterDTO;
import br.com.flickfind.catalog.dtos.TokenResponseDTO;
import br.com.flickfind.catalog.services.MovieService;
import br.com.flickfind.catalog.utils.ServiceRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class MovieServiceImpl {

    private final MovieService movieService;

    public MovieServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Value("${api.tmdb.key}")
    private String apiKey;

    public MovieResponse getMovies(FilterDTO userFilters, Map<String, String> filters) {
        Field[] fields = userFilters.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(userFilters);
                if (value != null) {
                    filters.put(field.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        filters.put("api_key", apiKey);
        filters.put("language", "pt-BR");
        return movieService.getMovies(filters);
    }
}
