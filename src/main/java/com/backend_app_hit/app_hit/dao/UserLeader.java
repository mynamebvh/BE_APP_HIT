package com.backend_app_hit.app_hit.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "UserLeader")
public class UserLeader implements Serializable{
  
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "class_id")
  @JsonIgnore
  private ClassRoom classRoom;

  public UserLeader() {
  }

  public UserLeader(User user, ClassRoom classRoom) {
    this.user = user;
    this.classRoom = classRoom;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ClassRoom getClassRoom() {
    return classRoom;
  }

  public void setClassRoom(ClassRoom classRoom) {
    this.classRoom = classRoom;
  }

  
}
