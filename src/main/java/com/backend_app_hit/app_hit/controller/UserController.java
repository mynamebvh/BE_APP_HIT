package com.backend_app_hit.app_hit.controller;

import java.util.Optional;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/me")
  @PreAuthorize("@userAuthorizer.authorizeAdmin(authentication, 'MEMBER')")
  public ResponseEntity<?> me() {
    String userName;
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetails) {
        userName = ((UserDetails) principal).getUsername();
      } else {
        userName = principal.toString();
      }

      Optional<User> uOptional = userRepository.findByUsername(userName);
      if (!uOptional.isPresent()) {
        throw new UsernameNotFoundException("Username không tồn tại");
      }

      User user = uOptional.get();

      return ResponseEntity.ok(user);
    } catch (Exception e) {

    }

    return ResponseEntity.ok("yes");
  }
}
