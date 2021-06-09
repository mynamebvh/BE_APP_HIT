package com.backend_app_hit.app_hit.controller;

import java.util.Optional;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.PasswordDTO;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.models.Response;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.utils.GetUserNameByContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public PasswordEncoder passwordEncoder;

  @GetMapping(value = "/me")
  @PreAuthorize("@userAuthorizer.authorizeAdmin(authentication, 'MEMBER')")
  public ResponseEntity<?> me() {
    String username = GetUserNameByContext.getUserName();

    Optional<User> uOptional = userRepository.findByUsername(username);
    if (!uOptional.isPresent()) {
      throw new UsernameNotFoundException("Username không tồn tại");
    }

    User user = uOptional.get();

    return ResponseEntity.ok(user);
  }

  @PostMapping("/changePassword")
  public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO) {
    String username = GetUserNameByContext.getUserName();

    User user = userRepository.findByUsername(username).get();
    if (passwordEncoder.matches(passwordDTO.getPasswordOld(), user.getPassword())) {
      user.setPassword(passwordEncoder.encode(passwordDTO.getPasswordNew()));
      userRepository.save(user);
    } else {
      throw new InvalidException("Mật khẩu cũ sai");
    }

    return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Đổi mật khẩu thành công"));
  }
}
