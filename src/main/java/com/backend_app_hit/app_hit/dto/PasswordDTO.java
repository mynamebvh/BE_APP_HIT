package com.backend_app_hit.app_hit.dto;

public class PasswordDTO {
  private String passwordOld;
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
