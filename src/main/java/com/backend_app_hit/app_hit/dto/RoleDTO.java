package com.backend_app_hit.app_hit.dto;

public class RoleDTO {
  private String id;
  private String nameRole;

  public RoleDTO() {
  }

  public RoleDTO(String id, String nameRole) {
    this.id = id;
    this.nameRole = nameRole;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNameRole() {
    return nameRole;
  }

  public void setNameRole(String nameRole) {
    this.nameRole = nameRole;
  }

}
