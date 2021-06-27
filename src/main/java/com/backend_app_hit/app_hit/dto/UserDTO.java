package com.backend_app_hit.app_hit.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.backend_app_hit.app_hit.dao.User;

public class UserDTO {
  @NotEmpty(message = "Họ tên không được trống")
  private String fullName;

  private String password;
  
  @NotNull
  @Pattern(regexp = "^[0123456789]{10}$", message = "Sai định dạng username")
  private String username;

  @NotNull
  @Pattern(regexp = "^(84|0[3|5|7|8|9])+([0-9]{8})$", message = "Sai định dạng số điện thoại")
  private String phone;

  private String birthday;

  @Email(message = "Định dạng email sai")
  private String email;

  private Long point;

  public UserDTO() {
  }

  

  public UserDTO(@NotEmpty(message = "Họ tên không được trống") String fullName, String password,
      @NotNull @Pattern(regexp = "^[0123456789]{10}$", message = "Sai định dạng username") String username,
      @NotNull @Pattern(regexp = "^(84|0[3|5|7|8|9])+([0-9]{8})$", message = "Sai định dạng số điện thoại") String phone,
      String birthday, @Email(message = "Định dạng email sai") String email, Long point) {
    this.fullName = fullName;
    this.password = password;
    this.username = username;
    this.phone = phone;
    this.birthday = birthday;
    this.email = email;
    this.point = point;
  }

  public UserDTO(User user) {
    this.fullName = user.getFullName();
    this.username = user.getUsername();
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
