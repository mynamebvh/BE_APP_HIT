package com.backend_app_hit.app_hit.models;

public class Response {
  private Integer status;
  private String msg;

  public Response() {
  }

  public Response(Integer status, String msg) {
    this.status = status;
    this.msg = msg;
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

}
