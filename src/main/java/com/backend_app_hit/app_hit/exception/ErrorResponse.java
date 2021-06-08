package com.backend_app_hit.app_hit.exception;

public class ErrorResponse {
  private Integer status;
  private String error;

  public ErrorResponse(Integer status, String error) {
    this.status = status;
    this.error = error;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

}