package my.demo.demojwtoauth2.repository;

import java.util.Optional;
import my.demo.demojwtoauth2.domain.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

  @EntityGraph(attributePaths = "userAuthoritiesList")
  Optional<Users> findOneWithUserAuthoritiesByUsername(String email);

}
