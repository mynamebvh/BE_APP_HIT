package com.backend_app_hit.app_hit.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Comment")
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id", nullable = false)
  private Long id;

  @Nationalized
  @Column(name = "content", nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp
  private Timestamp createAt;

  @UpdateTimestamp
  private Timestamp updateAt;

  public Comment() {
  }

  public Comment(Long id, String content, Post post, User user, Timestamp createAt, Timestamp updateAt) {
    this.id = id;
    this.content = content;
    this.post = post;
    this.user = user;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Timestamp createAt) {
    this.createAt = createAt;
  }

  public Timestamp getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Timestamp updateAt) {
    this.updateAt = updateAt;
  }

  
  
}
