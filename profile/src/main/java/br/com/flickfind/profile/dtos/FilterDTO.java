package br.com.flickfind.profile.dtos;

import br.com.flickfind.profile.domain.filter.Filter;
import org.springframework.beans.BeanUtils;

public record FilterDTO(String certification,
                        String certification_country,
                        boolean include_adult,
                        String language,
                        Integer primary_release_year,
                        String region,
                        String sort_by,
                        String watch_region,
                        String with_cast,
                        String with_companies,
                        String with_crew,
                        String with_genres,
                        String with_keywords,
                        String with_origin_country,
                        String with_original_language,
                        String with_people,
                        String with_watch_monetization_types,
                        String with_watch_providers,
                        String without_companies,
                        String without_genres,
                        String without_keywords,
                        String without_watch_providers,
                        Integer year) {
    public FilterDTO(Filter filter) {
        this(filter.getCertification(),
                filter.getCertification_country(),
                filter.isInclude_adult(),
                filter.getLanguage(),
                filter.getPrimary_release_year(),
                filter.getRegion(),
                filter.getSort_by(),
                filter.getWatch_region(),
                filter.getWith_cast(),
                filter.getWith_companies(),
                filter.getWith_crew(),
                filter.getWith_genres(),
                filter.getWith_keywords(),
                filter.getWith_origin_country(),
                filter.getWith_original_language(),
                filter.getWith_people(),
                filter.getWith_watch_monetization_types(),
                filter.getWith_watch_providers(),
                filter.getWithout_companies(),
                filter.getWithout_genres(),
                filter.getWithout_keywords(),
                filter.getWithout_watch_providers(),
                filter.getYear());
    }
}
