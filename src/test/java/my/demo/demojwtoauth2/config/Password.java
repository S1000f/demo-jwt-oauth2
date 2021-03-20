package my.demo.demojwtoauth2.config;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import my.demo.demojwtoauth2.domain.entity.UserAuthorities;
import my.demo.demojwtoauth2.domain.entity.Users;
import my.demo.demojwtoauth2.repository.UserAuthRepository;
import my.demo.demojwtoauth2.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest
public class Password {

  @Autowired
  private UsersRepository usersRepository;
  @Autowired
  private UserAuthRepository userAuthRepository;

  @Test
  public void password() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encode = passwordEncoder.encode("1234");

    log.info(encode);
  }

  @Test
  public void findTest() {
    Optional<Users> find = usersRepository.findOneWithUserAuthoritiesByUsername("nick@nick.com");

    log.info("find :" + find);
  }

  @Test
  public void queryTest() {
    Optional<Users> findUser = usersRepository.findOneWithUserAuthoritiesByUsername("nick2");
    Users users = findUser.get();

    Set<Long> userAuthIdxes = users.getUserAuthoritiesList()
        .stream()
        .map(UserAuthorities::getIdx)
        .collect(Collectors.toSet());

    userAuthRepository.findAllByIdxIn(userAuthIdxes)
        .forEach(u -> log.info("find auth: " + u.getAuthorities().getAuthorityName()));

  }

}
