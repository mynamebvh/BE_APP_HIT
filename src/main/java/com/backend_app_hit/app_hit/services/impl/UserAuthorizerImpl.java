package com.backend_app_hit.app_hit.services.impl;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.exception.ForbiddenException;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.services.UserAuthorizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("userAuthorizer")
public class UserAuthorizerImpl implements UserAuthorizer {
  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean authorizeAdmin(Authentication authentication, String role) {
    Object[] objectAuthentication = authentication.getAuthorities().toArray();
    if (objectAuthentication[0].toString().compareTo(role) != 0) {
      throw new ForbiddenException("Access denied");
    }
    return true;
  }

  @Override
  public boolean authorizeGetUserById(Authentication authentication, String role, Long userId) {
    Object[] objectAuthentication = authentication.getAuthorities().toArray();
    if (objectAuthentication[0].toString().compareTo(role) == 0) {
      return true;
    }

    User userAuth = (User) authentication.getPrincipal();
    User user = userRepository.findByUserName(userAuth.getUserName());

    if (user.getUserId() != userId) {
      throw new ForbiddenException("Access denied");
    }
    return true;
  }

  @Override
  public boolean authorizeUser(Authentication authentication, Long userId) {
    User userAuthentication = (User) authentication.getPrincipal();

    User user = userRepository.findByUserName(userAuthentication.getUserName());

    if (user.getUserId() != userId) {
      throw new ForbiddenException("Access denied");
    }
    return true;
  }

}
