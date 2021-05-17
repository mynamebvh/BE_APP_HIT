package com.backend_app_hit.app_hit.services;

import java.util.ArrayList;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username không tồn tại");
    }

    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
        new ArrayList<>());
  }
}
