package com.backend_app_hit.app_hit.dto;

public class SignUpDTO {
  private String fullName;
  private String userName;
  private String password;
  private String email;
  private String phone;
  private String birthday;

  public SignUpDTO() {
  }

  public SignUpDTO(String fullName, String userName, String password, String email, String phone, String birthday) {
    this.fullName = fullName;
    this.userName = userName;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "SignUpDTO [birthday=" + birthday + ", email=" + email + ", fullName=" + fullName + ", password=" + password
        + ", phone=" + phone + ", userName=" + userName + "]";
  }

}
