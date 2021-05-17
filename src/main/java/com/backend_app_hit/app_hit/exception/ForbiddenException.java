package com.backend_app_hit.app_hit.exception;

public class ForbiddenException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ForbiddenException(String message) {
    super(message);
  }
}
