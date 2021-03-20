package my.demo.demojwtoauth2.config.jwt;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.demo.demojwtoauth2.domain.entity.UserAuthorities;
import my.demo.demojwtoauth2.domain.entity.Users;
import my.demo.demojwtoauth2.repository.UserAuthRepository;
import my.demo.demojwtoauth2.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

  private final UsersRepository usersRepository;
  private final UserAuthRepository userAuthRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return usersRepository.findOneWithUserAuthoritiesByUsername(s)
        .map(this::createUser)
        .orElseThrow(() -> new UsernameNotFoundException("not found users"));
  }

  private User createUser(Users users) {
    if (users.isNotActive()) {
      throw new RuntimeException("not active users");
    }

    Set<Long> userAuthIdxes = users.getUserAuthoritiesList()
        .stream()
        .map(UserAuthorities::getIdx)
        .collect(Collectors.toSet());

    List<SimpleGrantedAuthority> grantedAuthorities = userAuthRepository.findAllByIdxIn(userAuthIdxes)
        .stream()
        .map(u -> new SimpleGrantedAuthority(u.getAuthorities().getAuthorityName()))
        .collect(Collectors.toList());

    return new User(users.getUsername(), users.getPassword(), grantedAuthorities);
  }
}
