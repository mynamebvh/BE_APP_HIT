package com.backend_app_hit.app_hit.exception;

public class InvalidException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidException(String message) {
    super(message);
  }
}
