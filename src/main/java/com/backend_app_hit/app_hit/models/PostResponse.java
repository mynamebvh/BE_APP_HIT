package com.backend_app_hit.app_hit.models;

import java.util.Date;
import java.util.List;

import com.backend_app_hit.app_hit.dao.Post;

public class PostResponse {
  private String username;
  private List<Post> postList;

  public PostResponse() {
  }

  public PostResponse(String username, List<Post> postList) {
    this.username = username;
    this.postList = postList;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Post> getPostList() {
    return postList;
  }

  public void setPostList(List<Post> postList) {
    this.postList = postList;
  }

  
  
}
