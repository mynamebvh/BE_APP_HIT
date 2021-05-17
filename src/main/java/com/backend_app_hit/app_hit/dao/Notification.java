package com.backend_app_hit.app_hit.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "noti_id", nullable = false)
  private Long notiId;

  @Column(name = "content", nullable = false)
  private String content;

  public Notification() {
  }

  public Notification(Long notiId, String content) {
    this.notiId = notiId;
    this.content = content;
  }

  public Long getNotiId() {
    return notiId;
  }

  public void setNotiId(Long notiId) {
    this.notiId = notiId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
