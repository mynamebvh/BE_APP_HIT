package com.backend_app_hit.app_hit.services;

import org.springframework.security.core.Authentication;

public interface UserAuthorizer {
  boolean authorizeAdmin(Authentication authentication, String role);

  boolean authorizeGetUserById(Authentication authentication, String role, Long userId);

  boolean authorizeUser(Authentication authentication, Long userId);
}
