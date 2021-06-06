package com.backend_app_hit.app_hit.repository;

import java.util.Optional;

import com.backend_app_hit.app_hit.dao.ClassRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>{
  Optional<ClassRoom> findById(Long id);

  void deleteById(Long id);
}
