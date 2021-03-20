package my.demo.demojwtoauth2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class HelloController {

  @GetMapping("/hello")
  public ResponseEntity<?> hello() {
    return ResponseEntity.ok("hello, world!");
  }

}
