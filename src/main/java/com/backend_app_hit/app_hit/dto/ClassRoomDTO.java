package com.backend_app_hit.app_hit.dto;

public class ClassRoomDTO {
  private String leader;
  private String name;
  public ClassRoomDTO() {
  }
  public ClassRoomDTO(String leader, String name, Long quantity) {
    this.leader = leader;
    this.name = name;
  }
  public String getLeader() {
    return leader;
  }
  public void setLeader(String leader) {
    this.leader = leader;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
