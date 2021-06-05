package com.backend_app_hit.app_hit.repository;

import java.util.List;

import com.backend_app_hit.app_hit.dao.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
  List<Comment> findByPostId(Long id);
  void deleteById(Long id);
}
