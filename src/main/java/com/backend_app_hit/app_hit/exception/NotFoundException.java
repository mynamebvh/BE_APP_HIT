package com.backend_app_hit.app_hit.exception;

public class NotFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
}
