package com.backend_app_hit.app_hit.models;

public class AuthenticationResponse {
  private Integer statusCode;
  private String jwt;
  private Long userId;
  private String username;
  private String role;

  public AuthenticationResponse() {
  }

  public AuthenticationResponse(Integer statusCode, String jwt, Long userId, String username, String role) {
    this.statusCode = statusCode;
    this.jwt = jwt;
    this.userId = userId;
    this.username = username;
    this.role = role;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
