package com.backend_app_hit.app_hit.repository;

import com.backend_app_hit.app_hit.dao.UserLeader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLeaderRepository extends JpaRepository<UserLeader, Long>{
  
}
