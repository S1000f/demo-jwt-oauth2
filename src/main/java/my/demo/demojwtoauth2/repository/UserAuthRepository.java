package my.demo.demojwtoauth2.repository;

import java.util.Set;
import my.demo.demojwtoauth2.domain.entity.UserAuthorities;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuthorities, Long> {

  @EntityGraph(attributePaths = "authorities")
  Set<UserAuthorities> findAllByIdxIn(Set<Long> userAuthIdxes);

}
