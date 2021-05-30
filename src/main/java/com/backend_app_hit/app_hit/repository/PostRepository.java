package com.backend_app_hit.app_hit.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend_app_hit.app_hit.dao.Post;
import com.backend_app_hit.app_hit.dao.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
  List<Post> findByUserId(Long userId);

  Optional<Post> findById(Long id);
}
