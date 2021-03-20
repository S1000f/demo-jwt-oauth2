package my.demo.demojwtoauth2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.demo.demojwtoauth2.config.jwt.TokenProvider;
import my.demo.demojwtoauth2.domain.entity.Users;
import my.demo.demojwtoauth2.dto.LoginDto;
import my.demo.demojwtoauth2.dto.TokenDto;
import my.demo.demojwtoauth2.dto.UsersDto;
import my.demo.demojwtoauth2.lib.SecurityUtils;
import my.demo.demojwtoauth2.service.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class JwtAuthController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final UsersService usersService;

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

    Authentication authentication = authenticationManagerBuilder.getObject()
        .authenticate(authenticationToken);

    String jwt = tokenProvider.createToken(authentication);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Authorization", "Bearer " + jwt);

    return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }

  @PostMapping("/join")
  public ResponseEntity<?> join(@RequestBody UsersDto usersDto) {
    Users findUser = usersService.join(usersDto);

    return findUser.getIdx() != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
  }

  @GetMapping("/token")
  @PreAuthorize("hasAnyRole('USER')")
  public ResponseEntity<?> getToken() {
    String token = SecurityUtils.getToken();

    return ResponseEntity.ok(token);
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> adminOnly() {
    return ResponseEntity.ok("admin");
  }

}
