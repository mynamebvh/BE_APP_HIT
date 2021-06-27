package com.backend_app_hit.app_hit.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Nationalized
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "user_name", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  @JsonIgnore
  private String password;

  @Column(name = "birthday", nullable = false)
  private String birthday;

  @Column(name = "role")
  private String role;

  @Column(name = "phone", unique = true, nullable = false)
  private String phone;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "point")
  private Long point;

  @Column(name = "img_src")
  private String url;

  @Column(name = "token_reset_password")
  @JsonIgnore
  private String tokenResetPass;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<UserClass> userClasses;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Post> posts;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Comment> comments;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<UserLeader> userLeaders;

  @CreationTimestamp
  private Timestamp createAt;

  @UpdateTimestamp
  private Timestamp updateAt;

  public User() {
  }

  public User(String fullName, String userName, String password, String birthday, String role, String phone,
      String email, Long point, List<UserClass> userClasses, List<Post> posts, List<Comment> comments) {
    this.fullName = fullName;
    this.username = userName;
    this.password = password;
    this.birthday = birthday;
    this.role = role;
    this.phone = phone;
    this.email = email;
    this.point = point;
    this.userClasses = userClasses;
    this.posts = posts;
    this.comments = comments;
  }

  
  public User(String fullName, String username, String password, String birthday, String phone, String email) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.birthday = birthday;
    this.phone = phone;
    this.email = email;
    this.role = "MEMBER";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<UserLeader> getUserLeaders() {
    return userLeaders;
  }

  public void setUserLeaders(List<UserLeader> userLeaders) {
    this.userLeaders = userLeaders;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getPoint() {
    return point;
  }

  public void setPoint(Long point) {
    this.point = point;
  }

  public List<UserClass> getUserClasses() {
    return userClasses;
  }

  public void setUserClasses(List<UserClass> userClasses) {
    this.userClasses = userClasses;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
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

  public String getTokenResetPass() {
    return tokenResetPass;
  }

  public void setTokenResetPass(String tokenResetPass) {
    this.tokenResetPass = tokenResetPass;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
