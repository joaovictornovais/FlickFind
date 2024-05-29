package br.com.flickfind.profile.services;

import br.com.flickfind.profile.domain.filter.Filter;
import br.com.flickfind.profile.domain.profile.Profile;
import br.com.flickfind.profile.repositories.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FilterService {

    private final FilterRepository filterRepository;

    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }


    public Filter createFilter(Profile profile) {
        Filter filter = new Filter();
        filter.setId(UUID.randomUUID().toString());
        filter.setProfile(profile);
        return filter;
    }


    public Filter save(Filter filter) {
        return filterRepository.save(filter);
    }

    public Filter findById(String id) {
        return filterRepository.findById(id).orElseThrow(() -> new RuntimeException("Filter not found"));
    }

}
