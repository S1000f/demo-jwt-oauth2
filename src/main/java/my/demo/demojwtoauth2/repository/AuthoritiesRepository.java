package my.demo.demojwtoauth2.repository;

import java.util.Optional;
import my.demo.demojwtoauth2.domain.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

  Optional<Authorities> findByAuthorityName(String name);

}
