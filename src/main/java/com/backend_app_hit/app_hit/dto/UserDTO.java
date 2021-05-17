package com.backend_app_hit.app_hit.dto;

import com.backend_app_hit.app_hit.dao.User;

public class UserDTO {
  private String fullName;
  private String username;
  private String password;
  private String phone;
  private String birthday;
  private String email;
  private Long point;

  public UserDTO() {
  }

  public UserDTO(String fullName, String username, String password, String phone, String birthday, String email,
      Long point) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.phone = phone;
    this.birthday = birthday;
    this.email = email;
    this.point = point;
  }

  public UserDTO(User user) {
    this.fullName = user.getFullName();
    this.username = user.getUserName();
    this.phone = user.getPhone();
    this.birthday = user.getBirthday();
    this.email = user.getBirthday();
    this.point = user.getPoint();
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getPoint() {
    return point;
  }

  public void setPoint(Long point) {
    this.point = point;
  }

}
