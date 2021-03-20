package my.demo.demojwtoauth2.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class Users {

  @Column(name = "users_idx")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long idx;

  @Column(unique = true, nullable = false, length = 50)
  private String username;

  @Column(nullable = false, length = 150)
  private String password;

  @Column(nullable = false, length = 50)
  private String nickname;

  private boolean isActive;

  @OneToMany(mappedBy = "users")
  private List<UserAuthorities> userAuthoritiesList = new ArrayList<>();

  @JsonIgnore
  public boolean isNotActive() {
    return !isActive;
  }

}
