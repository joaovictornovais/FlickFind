package br.com.flickfind.profile.domain.profile;

import br.com.flickfind.profile.domain.filter.Filter;
import br.com.flickfind.profile.dtos.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "filter_id")
    private Filter filter;

    public Profile(ProfileDTO profileDTO) {
        BeanUtils.copyProperties(profileDTO, this);
    }

}
