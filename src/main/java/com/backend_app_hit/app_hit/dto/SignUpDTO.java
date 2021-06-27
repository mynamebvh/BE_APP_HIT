package com.backend_app_hit.app_hit.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SignUpDTO {
  @NotNull
  @NotEmpty(message = "Họ tên không được trống")
  private String fullName;

  @NotNull
  @NotEmpty(message = "username không được trống")
  @Pattern(regexp = "^[0123456789]{10}$", message = "Sai định dạng username")
  
  private String username;

  @NotEmpty(message = "Mật khẩu không được trống")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,}$", 
  message = "Sai định dạng mật khẩu")
  private String password;

  @NotNull
  @NotEmpty(message = "Email không được trống")
  @Pattern(regexp =  "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Sai định dạng email")
  private String email;

  @NotNull
  @NotEmpty(message = "Số điện thoại không được trống")
  @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "Sai định dạng số điện thoại")
  private String phone;

  @NotNull
  @NotEmpty(message = "Sinh nhật không được trống")
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

  @Override
  public String toString() {
    return "SignUpDTO [birthday=" + birthday + ", email=" + email + ", fullName=" + fullName + ", password=" + password
        + ", phone=" + phone + ", username=" + username + "]";
  }

}
