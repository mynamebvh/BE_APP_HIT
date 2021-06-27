package com.backend_app_hit.app_hit.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.exception.LoginException;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.security.LoginAttemptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LoginAttemptService loginAttemptService;

  @Autowired
  private HttpServletRequest request;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> uOptional = userRepository.findByUsername(username);
    if (!uOptional.isPresent()) {
      throw new UsernameNotFoundException("Username không tồn tại");
    }

    User user = uOptional.get();

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        grantedAuthorities);
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
