package my.demo.demojwtoauth2.enums;

public enum UserRole {

  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN"),
  SUPERADMIN("ROLE_SUPERADMIN");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return this.role;
  }

}
