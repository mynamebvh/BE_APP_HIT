package com.backend_app_hit.app_hit.repository;


import java.util.Optional;

import com.backend_app_hit.app_hit.dao.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String userName);

  Optional<User> findByEmail(String email);

  Optional<User> findByTokenResetPass(String token);
}
