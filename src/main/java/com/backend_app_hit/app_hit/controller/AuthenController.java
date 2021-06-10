package com.backend_app_hit.app_hit.controller;

import java.time.Duration;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.SignUpDTO;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.helpers.ConvertObject;
import com.backend_app_hit.app_hit.models.AuthenticationRequest;
import com.backend_app_hit.app_hit.models.AuthenticationResponse;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.services.CustomUserDetailsService;
import com.backend_app_hit.app_hit.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenController {
  private final Bucket bucket;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  public PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomUserDetailsService userService;

  public AuthenController() {
    Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
    this.bucket = Bucket4j.builder().addLimit(limit).build();
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) throws Exception {
    if (bucket.tryConsume(1)) {
      try {
        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
      } catch (Exception e) {
        throw new LoginException("Incorrect username or password");
      }

      Optional<User> uOptional = userRepository.findByUsername(request.getUsername());
      if (!uOptional.isPresent()) {
        throw new UsernameNotFoundException("Username không tồn tại");
      }

      User user = uOptional.get();
      String token = jwtUtil.generateToken(request.getUsername());
      
      return ResponseEntity
          .ok(new AuthenticationResponse(200, token, user.getId(), user.getUsername(), user.getRole()));
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

  }

  @PostMapping(value = "/signup")
  public ResponseEntity<?> register(@RequestBody SignUpDTO signUpDTO) {
    if (bucket.tryConsume(1)) {
      Optional<User> uOptional = userRepository.findByUsername(signUpDTO.getUsername());

      if (uOptional.isPresent()) {
        throw new InvalidException("user not exits");
      }

      User newUser = ConvertObject.fromSignUpDTOToUserDAO(signUpDTO);

      if (newUser == null) {
        throw new InvalidException("Invalid user");
      }
      newUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
      userRepository.save(newUser);

      final UserDetails userDetails = userService.loadUserByUsername(newUser.getUsername());
      final String jwt = jwtUtil.generateToken(userDetails.getUsername());

      return ResponseEntity
          .ok(new AuthenticationResponse(201, jwt, newUser.getId(), newUser.getUsername(), newUser.getRole()));
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

  }

}
