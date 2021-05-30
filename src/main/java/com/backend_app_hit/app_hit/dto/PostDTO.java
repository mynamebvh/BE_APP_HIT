package com.backend_app_hit.app_hit.dto;

import java.util.Date;

public class PostDTO {
    private String username;
    private String content;
    private Date createAt;
    private Date updateAt;
    public PostDTO() {
    }

    
    public PostDTO(String username, String content, Date createAt, Date updateAt) {
        this.username = username;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreateAt() {
        return createAt;
    }


    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public Date getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    
}
