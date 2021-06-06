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
@Table(name = "ClassRooms")
public class ClassRoom implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_id", nullable = false)
  private Long id;

  @Nationalized
  @Column(name = "leader", nullable = false)
  private String leader;

  @Nationalized
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "quantity", nullable = true)
  private Long quantity;

  @Column(name = "folder_url", nullable = true)
  private String folderUrl;

  @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  private List<UserClass> userClasses;

  @CreationTimestamp
  private Timestamp createAt;

  @UpdateTimestamp
  private Timestamp updateAt;

  public ClassRoom() {
  }

  public ClassRoom(Long id, String leader, String name, Long quantity, String folderUrl, List<UserClass> userClasses) {
    this.id = id;
    this.leader = leader;
    this.name = name;
    this.quantity = quantity;
    this.folderUrl = folderUrl;
    this.userClasses = userClasses;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getFolderUrl() {
    return folderUrl;
  }

  public void setFolderUrl(String folderUrl) {
    this.folderUrl = folderUrl;
  }

  public List<UserClass> getUserClasses() {
    return userClasses;
  }

  public void setUserClasses(List<UserClass> userClasses) {
    this.userClasses = userClasses;
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
