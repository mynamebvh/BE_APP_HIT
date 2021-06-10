package com.backend_app_hit.app_hit.models;

import java.util.List;

import com.backend_app_hit.app_hit.dao.ClassRoom;
import com.backend_app_hit.app_hit.dao.UserClass;
import com.backend_app_hit.app_hit.dao.UserLeader;

public class ClassRoomResponse {
  private Integer status;
  private String msg;
  private ClassRoom classRoom;
  private List<UserClass> usClasses;
  private List<UserLeader> userLeaders;

  public ClassRoomResponse() {
  }

  public ClassRoomResponse(Integer status, String msg, ClassRoom classRoom, List<UserClass> usClasses,
      List<UserLeader> userLeaders) {
    this.status = status;
    this.msg = msg;
    this.classRoom = classRoom;
    this.usClasses = usClasses;
    this.userLeaders = userLeaders;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public ClassRoom getClassRoom() {
    return classRoom;
  }

  public void setClassRoom(ClassRoom classRoom) {
    this.classRoom = classRoom;
  }

  public List<UserClass> getUsClasses() {
    return usClasses;
  }

  public void setUsClasses(List<UserClass> usClasses) {
    this.usClasses = usClasses;
  }

  public List<UserLeader> getUserLeaders() {
    return userLeaders;
  }

  public void setUserLeaders(List<UserLeader> userLeaders) {
    this.userLeaders = userLeaders;
  }

  

}
