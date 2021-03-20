package my.demo.demojwtoauth2.config.jwt;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) throws IOException {
    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
  }
}
