package my.demo.demojwtoauth2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.demo.demojwtoauth2.domain.entity.Authorities;
import my.demo.demojwtoauth2.domain.entity.UserAuthorities;
import my.demo.demojwtoauth2.domain.entity.Users;
import my.demo.demojwtoauth2.dto.UsersDto;
import my.demo.demojwtoauth2.enums.UserRole;
import my.demo.demojwtoauth2.repository.AuthoritiesRepository;
import my.demo.demojwtoauth2.repository.UserAuthRepository;
import my.demo.demojwtoauth2.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersService {

  private final UsersRepository usersRepository;
  private final AuthoritiesRepository authoritiesRepository;
  private final UserAuthRepository userAuthRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public Users join(UsersDto usersDto) {
    Users users = Users.builder()
        .username(usersDto.getUsername())
        .password(passwordEncoder.encode(usersDto.getPassword()))
        .nickname(usersDto.getNickname())
        .isActive(true)
        .build();

    Users savedUser = usersRepository.save(users);

    Authorities findAuth = authoritiesRepository.findByAuthorityName(UserRole.USER.getRole())
        .orElseThrow(() -> new RuntimeException("not found user role"));

    UserAuthorities userAuthorities = UserAuthorities.builder()
        .users(savedUser)
        .authorities(findAuth)
        .build();

    userAuthRepository.save(userAuthorities);

    return savedUser;
  }

}
