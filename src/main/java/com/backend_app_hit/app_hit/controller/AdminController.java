package com.backend_app_hit.app_hit.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dto.UserDTO;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.models.Response;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user")
  public ResponseEntity<?> getAllUser(@RequestParam Integer page){
    Pageable pageable = PageRequest.of(page, 10);
    Page<User> pages = userRepository.findAll(pageable);

    return ResponseEntity.status(HttpStatus.OK).body(pages);
  }

  @GetMapping("/findUser")
  public ResponseEntity<?> findUserByName(@RequestBody HashMap<String, String> option){
     Optional<User> uOptional = null;
    switch (option.get("option")) {
      case "name":
        uOptional = userRepository.findByFullName(option.get("name"));
        if(!uOptional.isPresent()){
          throw new InvalidException("Người dùng không tồn tại");
        }
        break;
      case "username":
        uOptional = userRepository.findByUsername(option.get("username"));
        if(!uOptional.isPresent()){
          throw new InvalidException("Người dùng không tồn tại");
        }
        break;
      case "email":
        uOptional = userRepository.findByEmail(option.get("email"));
        if(!uOptional.isPresent()){
          throw new InvalidException("Người dùng không tồn tại");
        }
        break;
      default:
        throw new InvalidException("Vui lòng nhập tuỳ chọn tìm kiếm");
    }
    return ResponseEntity.status(HttpStatus.OK).body(uOptional.get());
  }

  @PatchMapping("/updateUser/{username}")
  public ResponseEntity<?> updateUser(@PathVariable String username,@Valid @RequestBody UserDTO userDTO ){

    Optional<User> uOptional = userRepository.findByUsername(username);

    if(!uOptional.isPresent()){
      throw new InvalidException("Người dùng không tồn tại");
    }
    
    User user = uOptional.get();
    user.setFullName(userDTO.getFullName());
    user.setUsername(userDTO.getUsername());
    user.setPhone(userDTO.getPhone());
    user.setBirthday(userDTO.getBirthday());
    user.setEmail(userDTO.getEmail());
    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Cập nhật thành công"));
  }

  @DeleteMapping("/user/{username}")
  public ResponseEntity<?> deleteUser(@PathVariable String username){
    Optional<User> uOptional = userRepository.findByUsername(username);

    if(!uOptional.isPresent()){
      throw new InvalidException("Người dùng không tồn tại");
    }

    userRepository.deleteById(uOptional.get().getId());

    return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Xoá thành công"));
  }

  @GetMapping("/grantPermission")
  public ResponseEntity<?> grantPermission(@RequestParam String username){
  
    Optional<User> uOptional = userRepository.findByUsername(username);

    if(uOptional.isPresent()){
      throw new InvalidException("Người dùng không tồn tại");
    }

    uOptional.get().setRole("ADMIN");
    userRepository.save(uOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Cấp quyền thành công")); 
  }



}