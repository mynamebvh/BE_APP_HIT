package com.backend_app_hit.app_hit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend_app_hit.app_hit.dao.ClassRoom;
import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.dao.UserClass;
import com.backend_app_hit.app_hit.dto.ClassRoomDTO;
import com.backend_app_hit.app_hit.dto.ClassStudentDTO;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.exception.NotFoundException;
import com.backend_app_hit.app_hit.repository.ClassRoomRepository;
import com.backend_app_hit.app_hit.repository.UserClassRepository;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/class")
public class ClassRoomController {

  @Autowired
  private ClassRoomRepository classRoomRepository;

  @Autowired
  private UserClassRepository userClassRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{classId}")
  public ResponseEntity<?> getClass(@PathVariable Long classId) {
    try {

      Optional<ClassRoom> classOptional = classRoomRepository.findById(classId);

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }

      ClassRoom classRoom = classOptional.get();
      return ResponseEntity.status(HttpStatus.OK).body(classRoom);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @PostMapping("/")
  public ResponseEntity<?> createClass(@RequestBody ClassRoomDTO classRoomDTO) {
    try {
      ClassRoom classRoom = new ClassRoom(null, classRoomDTO.getLeader(), classRoomDTO.getName(), null, null, null);
      classRoomRepository.save(classRoom);

      return ResponseEntity.status(HttpStatus.CREATED).body(classRoom);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @PatchMapping("/{classId}")
  public ResponseEntity<?> updateClass(@PathVariable Long classId, @RequestBody ClassRoomDTO classRoomDTO) {
    try {
      Optional<ClassRoom> classOptional = classRoomRepository.findById(classId);

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }
      ClassRoom classRoom = classOptional.get();

      classRoom.setLeader(classRoomDTO.getLeader());
      classRoom.setName(classRoomDTO.getName());

      classRoomRepository.save(classRoom);

      return ResponseEntity.status(HttpStatus.OK).body(classRoom);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @DeleteMapping("/{classId}")
  public ResponseEntity<?> deleteClass(@PathVariable Long classId, @RequestBody ClassRoomDTO classRoomDTO) {
    try {
      Optional<ClassRoom> classOptional = classRoomRepository.findById(classId);

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }

      classRoomRepository.deleteById(classId);

      return ResponseEntity.status(HttpStatus.OK).body("oki");
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @GetMapping("/{classId}/listStudent")
  public ResponseEntity<?> getListMember(@PathVariable Long classId) {
    try {
      Optional<ClassRoom> classOptional = classRoomRepository.findById(classId);

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }
      ClassRoom classRoom = classOptional.get();

      List<UserClass> userClasses = userClassRepository.findByClassRoomId(classRoom.getId());
      return ResponseEntity.status(HttpStatus.OK).body(userClasses);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @PostMapping("/addStudent")
  public ResponseEntity<?> addStudent(@RequestBody ClassStudentDTO classStudentDTOs) {
    try {
      Optional<User> uOptional = null;
      User user = null;

      Optional<ClassRoom> classOptional = classRoomRepository.findById(classStudentDTOs.getClassId());

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }
      ClassRoom classRoom = classOptional.get();
      List<UserClass> userClasses = new ArrayList<UserClass>();

      for (String username : classStudentDTOs.getUsername()) {
        uOptional = userRepository.findByUserName(username);
        user = uOptional.get();
        userClasses.add(new UserClass(user, classRoom));
      }

      userClassRepository.saveAll(userClasses);
      return ResponseEntity.status(HttpStatus.OK).body(classRoom);
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @DeleteMapping("/{classId}/{username}")
  public ResponseEntity<?> deleteStudentFromClass(@PathVariable Long classId, @PathVariable String username) {
    try {
      Optional<ClassRoom> classOptional = classRoomRepository.findById(classId);

      if (!classOptional.isPresent()) {
        throw new InvalidException("Lớp không tồn tại");
      }
      ClassRoom classRoom = classOptional.get();

      Optional<User> uOptional = userRepository.findByUserName(username);
      if (!uOptional.isPresent()) {
        throw new InvalidException("Người dùng không tồn tại");
      }

      User user = uOptional.get();

      return ResponseEntity.status(HttpStatus.OK).body("Xoá thành công");
    } catch (Exception e) {
      throw new NotFoundException(e.getMessage());
    }
  }

}
