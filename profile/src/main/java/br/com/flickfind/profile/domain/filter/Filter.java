package br.com.flickfind.profile.domain.filter;

import br.com.flickfind.profile.domain.profile.Profile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String certification;
    private String certification_country;
    private boolean include_adult;
    private String language;
    private Integer primary_release_year;
    private String region;
    private String sort_by;
    private String watch_region;
    private String with_cast;
    private String with_companies;
    private String with_crew;
    private String with_genres;
    private String with_keywords;
    private String with_origin_country;
    private String with_original_language;
    private String with_people;
    private String with_watch_monetization_types;
    private String with_watch_providers;
    private String without_companies;
    private String without_genres;
    private String without_keywords;
    private String without_watch_providers;
    private Integer year;

    @JsonBackReference
    @OneToOne(mappedBy = "filter")
    public Profile profile;


}
