package com.backend_app_hit.app_hit.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PasswordDTO {
  @NotEmpty(message = "Mật khẩu cũ không được trống")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,}$", 
  message = "Sai định dạng mật khẩu")
  private String passwordOld;

  @NotEmpty(message = "Mật khẩu mới không được trống")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,}$", 
  message = "Sai định dạng mật khẩu")
  private String passwordNew;

  public PasswordDTO() {
  }

  public PasswordDTO(String passwordOld, String passwordNew) {
    this.passwordOld = passwordOld;
    this.passwordNew = passwordNew;
  }

  public String getPasswordOld() {
    return passwordOld;
  }

  public void setPasswordOld(String passwordOld) {
    this.passwordOld = passwordOld;
  }

  public String getPasswordNew() {
    return passwordNew;
  }

  public void setPasswordNew(String passwordNew) {
    this.passwordNew = passwordNew;
  }

}
