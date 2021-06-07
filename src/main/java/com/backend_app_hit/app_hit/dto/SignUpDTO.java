package com.backend_app_hit.app_hit.dto;

public class SignUpDTO {
  private String fullName;
  private String username;
  private String password;
  private String email;
  private String phone;
  private String birthday;

  public SignUpDTO() {
  }

  public SignUpDTO(String fullName, String username, String password, String email, String phone, String birthday) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.birthday = birthday;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  

  
}
