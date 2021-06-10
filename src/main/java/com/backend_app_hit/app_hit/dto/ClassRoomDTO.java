package com.backend_app_hit.app_hit.dto;

import java.util.List;

public class ClassRoomDTO {
  private List<String> leaderList;
  private List<String> memberList;
  private String name;

  public ClassRoomDTO() {
  }

  public ClassRoomDTO(List<String> leaderList, List<String> memberList, String name) {
    this.leaderList = leaderList;
    this.memberList = memberList;
    this.name = name;
  }

  public List<String> getLeaderList() {
    return leaderList;
  }

  public void setLeaderList(List<String> leaderList) {
    this.leaderList = leaderList;
  }

  public List<String> getMemberList() {
    return memberList;
  }

  public void setMemberList(List<String> memberList) {
    this.memberList = memberList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
