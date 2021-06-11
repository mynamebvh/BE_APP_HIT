package com.backend_app_hit.app_hit.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import com.backend_app_hit.app_hit.dto.PhotoUpload;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/api/v1")
public class TestController {
  @Autowired
  private Cloudinary cloudinary;

  private final Bucket bucket;

  public TestController() {
    Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
    this.bucket = Bucket4j.builder().addLimit(limit).build();
  }

  @GetMapping("/")
  public ResponseEntity<?> hello() {
    if (bucket.tryConsume(1)) {
      return ResponseEntity.ok("hello");
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
  }

  @PostMapping(value = "/upload")
  public ResponseEntity<?> upload(@ModelAttribute PhotoUpload photoUpload) throws IOException {
    Map uploadResult = null;

    if (photoUpload.getFile() != null && !photoUpload.getFile().isEmpty()) {
      uploadResult = this.cloudinary.uploader().upload(photoUpload.getFile().getBytes(),
          ObjectUtils.asMap("resource_type", "auto", "folder", "avatar_user"));
          
      photoUpload.setPublicId((String) uploadResult.get("public_id"));
      Object version = uploadResult.get("version");
      if (version instanceof Integer) {
        photoUpload.setVersion(new Long((Integer) version));
      } else {
        photoUpload.setVersion((Long) version);
      }

      photoUpload.setSignature((String) uploadResult.get("signature"));
      photoUpload.setFormat((String) uploadResult.get("format"));
      photoUpload.setResourceType((String) uploadResult.get("resource_type"));
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(photoUpload.getUrl());
  }

}
