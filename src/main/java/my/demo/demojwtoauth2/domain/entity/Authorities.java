package my.demo.demojwtoauth2.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
@Entity
public class Authorities {

  @Column(name = "authorities_idx")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long idx;

  @Column(nullable = false)
  private String authorityName;



}
