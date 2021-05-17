package com.backend_app_hit.app_hit.controller;

import javax.security.auth.login.LoginException;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.UserDTO;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/{username}")
  @PreAuthorize("@userAuthorizer.authorizeAdmin(authentication, 'MEMBER')")
  public ResponseEntity<?> me(@PathVariable String username) {
    UserDTO userDTO = null;

    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Object[] userNameObject = auth.getPrincipal().toArray();

      if (objectAuthentication[0].toString().compareTo(role) != 0) {
        throw new ForbiddenException("Access denied");
      }
      User user = userRepository.findByUserName(username);
      if (!user.getUserName().equals()) {
        throw new LoginException("error");
      }
      userDTO = new UserDTO(user);
      return ResponseEntity.ok(userDTO);
    } catch (Exception e) {

    }

    return ResponseEntity.ok("no");
  }
}
