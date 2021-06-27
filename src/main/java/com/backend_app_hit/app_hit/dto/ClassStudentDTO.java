package com.backend_app_hit.app_hit.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class ClassStudentDTO {
  @NotEmpty(message = "Mã lớp không được trống")
  private Long classId;
  private List<String> username;

  public ClassStudentDTO() {
  }

  public ClassStudentDTO(Long classId, List<String> username) {
    this.classId = classId;
    this.username = username;
  }

  public Long getClassId() {
    return classId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }

  public List<String> getUsername() {
    return username;
  }

  public void setUsername(List<String> username) {
    this.username = username;
  }

}
