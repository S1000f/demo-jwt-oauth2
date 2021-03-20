package my.demo.demojwtoauth2.lib;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class SecurityUtils {

  private SecurityUtils() {}

  public static Optional<String> getUsername() {
    final Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();

    if (authentication == null) {
      return Optional.empty();
    }

    String username = null;
    if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      username = userDetails.getUsername();
    } else if (authentication.getPrincipal() instanceof String) {
      username = (String) authentication.getPrincipal();
    }

    return Optional.ofNullable(username);
  }

  public static String getToken() {
    final Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();

    if (authentication == null) {
      return null;
    }

    return (String) authentication.getCredentials();
  }
}
