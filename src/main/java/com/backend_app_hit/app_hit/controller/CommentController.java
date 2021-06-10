package com.backend_app_hit.app_hit.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.backend_app_hit.app_hit.dao.Comment;
import com.backend_app_hit.app_hit.dao.Post;
import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.CommentDTO;
import com.backend_app_hit.app_hit.exception.NotFoundException;
import com.backend_app_hit.app_hit.repository.CommentRepository;
import com.backend_app_hit.app_hit.repository.PostRepository;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.utils.GetUserNameByContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{postId}")
  public ResponseEntity<?> getComment(@PathVariable Long postId) {
    try {
      List<Comment> comments = commentRepository.findByPostId(postId);

      return ResponseEntity.status(HttpStatus.OK).body(comments);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> postComment(@Valid @RequestBody CommentDTO commentDTO) {
    try {
      String userName = GetUserNameByContext.getUserName();
      Optional<Post> postOptional = postRepository.findById(commentDTO.getPostId());

      Optional<User> uOptional = userRepository.findByUsername(userName);
      if (!uOptional.isPresent()) {
        throw new UsernameNotFoundException("Username không tồn tại");
      }

      User user = uOptional.get();

      if (!postOptional.isPresent()) {
        throw new NotFoundException("Bài viết không tồn tại");
      }

      Post post = postOptional.get();

      Comment comment = new Comment(null, commentDTO.getComment(), post, user, null, null);
      commentRepository.save(comment);

      return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
    try {
      String userName = GetUserNameByContext.getUserName();
      Optional<Comment> commentOptional = commentRepository.findById(commentId);

      if (!commentOptional.isPresent()) {
        throw new NotFoundException("Comment không tồn tại");
      }

      Comment comment = commentOptional.get();

      if (comment.getUser().getUsername().equals(userName)) {
        commentRepository.deleteById(commentId);
      } else {
        throw new NotFoundException("Bạn không có quyền");
      }

      return ResponseEntity.status(HttpStatus.OK).body(comment);

    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @PatchMapping(value = "/{commentId}")
  public ResponseEntity<?> updateComment(@RequestBody String content, @PathVariable Long commentId) {
    try {
      String userName = GetUserNameByContext.getUserName();
      Optional<Comment> commentOptional = commentRepository.findById(commentId);

      if (!commentOptional.isPresent()) {
        throw new NotFoundException("Comment không tồn tại");
      }

      Comment comment = commentOptional.get();

      if (comment.getUser().getUsername().equals(userName)) {
        comment.setContent(content);
        commentRepository.save(comment);
      } else {
        throw new NotFoundException("Bạn không có quyền");
      }
      return ResponseEntity.status(HttpStatus.OK).body(comment);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }

  }
}
