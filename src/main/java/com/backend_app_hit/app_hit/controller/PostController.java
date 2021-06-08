package com.backend_app_hit.app_hit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend_app_hit.app_hit.dao.Post;
import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.exception.NotFoundException;
import com.backend_app_hit.app_hit.models.PostResponse;
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
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody String content) {
        try {
            String userName = GetUserNameByContext.getUserName();

            Post post = new Post();

            Optional<User> uOptional = userRepository.findByUsername(userName);
            if (!uOptional.isPresent()) {
                throw new UsernameNotFoundException("Username không tồn tại");
            }

            User user = uOptional.get();

            post.setContent(content);
            post.setUser(user);
            postRepository.save(post);

            List<Post> posts = new ArrayList<Post>();
            posts.add(post);
            return ResponseEntity.ok(new PostResponse(userName, posts));
        } catch (Exception e) {
            throw new InvalidException(e.getMessage());
        }

    }

    @GetMapping("/me")
    public ResponseEntity<?> postMe() {

        try {
            String userName = GetUserNameByContext.getUserName();

            Optional<User> uOptional = userRepository.findByUsername(userName);
            if (!uOptional.isPresent()) {
                throw new UsernameNotFoundException("Username không tồn tại");
            }

            User user = uOptional.get();

            List<Post> posList = postRepository.findByUserId(user.getId());

            return ResponseEntity.ok(new PostResponse(user.getUsername(), posList));
        } catch (Exception e) {
            throw new InvalidException(e.getMessage());
        }
    }

    @PatchMapping(value = "/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody String content, @PathVariable Long postId) {
        String userName = GetUserNameByContext.getUserName();
        Optional<Post> postOptional = postRepository.findById(postId);

        if (!postOptional.isPresent()) {
            throw new NotFoundException("Bài viết không tồn tại");
        }

        Post post = postOptional.get();

        if (!post.getUser().getUsername().equals(userName)) {
            throw new NotFoundException("Không có quyền sửa bài viết này");
        }

        post.setContent(content);
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.OK).body(post);

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        String userName = GetUserNameByContext.getUserName();
        Optional<Post> postOptional = postRepository.findById(postId);

        if (!postOptional.isPresent()) {
            throw new NotFoundException("Bài viết không tồn tại");
        }

        Post post = postOptional.get();

        if (!post.getUser().getUsername().equals(userName)) {
            throw new NotFoundException("Không có quyền xoá bài viết này");
        }
        postRepository.deleteById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Xoá thành công");
    }
}
