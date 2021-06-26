package com.backend_app_hit.app_hit.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.backend_app_hit.app_hit.dao.Post;
import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.exception.NotFoundException;
import com.backend_app_hit.app_hit.models.PostResponse;
import com.backend_app_hit.app_hit.repository.PostRepository;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.utils.GetUserNameByContext;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final Bucket bucket;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    public PostController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestParam("content") String content, @RequestParam("file") MultipartFile file) throws IOException {
        if (bucket.tryConsume(1)) {
            final String URL = "https://res.cloudinary.com/dhlmdhzbz/image/upload/v1624677040/";
            Map params = ObjectUtils.asMap("overwrite", true, "resource_type", "image", "folder", "img_post");

            Map uploadResult = null;

            if (file != null && !file.isEmpty()) {
                uploadResult = this.cloudinary.uploader().upload(file.getBytes(), params);
            } else {
                throw new InvalidException("File không được trống");
            }

            String username = GetUserNameByContext.getUserName();

            Post post = new Post();

            Optional<User> uOptional = userRepository.findByUsername(username);
            if (!uOptional.isPresent()) {
                throw new UsernameNotFoundException("Username không tồn tại");
            }

            User user = uOptional.get();

            post.setContent(content);
            post.setUser(user);
            post.setUrlImg(URL + uploadResult.get("public_id"));
            postRepository.save(post);

            List<Post> posts = new ArrayList<Post>();
            posts.add(post);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new PostResponse(201, "Thêm thành công", username, posts));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

    }

    @GetMapping("/me")
    public ResponseEntity<?> postMe() {
        String username = GetUserNameByContext.getUserName();

        Optional<User> uOptional = userRepository.findByUsername(username);
        if (!uOptional.isPresent()) {
            throw new UsernameNotFoundException("Username không tồn tại");
        }

        User user = uOptional.get();

        List<Post> posList = postRepository.findByUserId(user.getId());

        return ResponseEntity.ok(new PostResponse(200, "Thành công", user.getUsername(), posList));
    }

    @PatchMapping(value = "/{postId}")
    public ResponseEntity<?> updatePost(@RequestBody String content, @PathVariable Long postId) {

        if (bucket.tryConsume(1)) {
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
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long postId) {
        if (bucket.tryConsume(1)) {
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
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
    }
}
