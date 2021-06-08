package com.backend_app_hit.app_hit.repository;

import java.util.List;

import com.backend_app_hit.app_hit.dao.UserClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long>{
  void deleteByUserIdAndClassRoomId(Long userId, Long classId);
  List<UserClass> findByClassRoomId (Long id);
}
