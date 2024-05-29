package br.com.flickfind.profile.repositories;

import br.com.flickfind.profile.domain.filter.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, String> {
}
