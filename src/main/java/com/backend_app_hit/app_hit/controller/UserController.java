package com.backend_app_hit.app_hit.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.PasswordDTO;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.models.Response;
import com.backend_app_hit.app_hit.repository.UserRepository;
import com.backend_app_hit.app_hit.utils.GetUserNameByContext;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public PasswordEncoder passwordEncoder;

  @Autowired
  private Cloudinary cloudinary;

  @GetMapping(value = "/me")
  @PreAuthorize("@userAuthorizer.authorizeAdmin(authentication, 'MEMBER')")
  public ResponseEntity<?> me() {
    String username = GetUserNameByContext.getUserName();

    Optional<User> uOptional = userRepository.findByUsername(username);
    if (!uOptional.isPresent()) {
      throw new UsernameNotFoundException("Username không tồn tại");
    }

    User user = uOptional.get();

    return ResponseEntity.ok(user);
  }

  @PostMapping("/change-password")
  @PreAuthorize("@userAuthorizer.authorizeAdmin(authentication, 'MEMBER')")
  public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDTO passwordDTO) {
    String username = GetUserNameByContext.getUserName();

    User user = userRepository.findByUsername(username).get();
    if (passwordEncoder.matches(passwordDTO.getPasswordOld(), user.getPassword())) {
      user.setPassword(passwordEncoder.encode(passwordDTO.getPasswordNew()));
      userRepository.save(user);
    } else {
      throw new InvalidException("Mật khẩu cũ sai");
    }

    return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Đổi mật khẩu thành công"));
  }

  @PostMapping("/upload-avatar")
  public ResponseEntity<?> updateAvatar(@RequestParam("file") MultipartFile file) throws IOException {
    final String URL = "https://res.cloudinary.com/dhlmdhzbz/image/upload/v1624677040/";
    
    String username = GetUserNameByContext.getUserName();

    Optional<User> uOptional = userRepository.findByUsername(username);

    Map params = ObjectUtils.asMap("overwrite", true, "resource_type", "image", "folder", "avatar_user");

    Map uploadResult = null;

    if (file != null && !file.isEmpty()) {
      uploadResult = this.cloudinary.uploader().upload(file.getBytes(), params);
    }
    else {
      throw new InvalidException("File không được trống");
    }
    uOptional.get().setUrl(URL + uploadResult.get("public_id"));

    return ResponseEntity.status(HttpStatus.CREATED).body(uOptional.get());
  }
}
