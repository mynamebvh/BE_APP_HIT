package com.backend_app_hit.app_hit.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import com.backend_app_hit.app_hit.dao.User;
import com.backend_app_hit.app_hit.exception.InvalidException;
import com.backend_app_hit.app_hit.models.Response;
import com.backend_app_hit.app_hit.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/api/v1")
public class ForgetPasswordController {

  private final Bucket bucket;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public PasswordEncoder passwordEncoder;

  @Autowired
  public JavaMailSender emailSender;

  public ForgetPasswordController() {
    Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofMinutes(1)));
    this.bucket = Bucket4j.builder().addLimit(limit).build();
  }

  @PostMapping("/forgetPassword/{email}")
  public ResponseEntity<?> generate(@PathVariable String email) {
    final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    if (bucket.tryConsume(1)) {
      if (Pattern.matches(regexEmail, email)) {
        Optional<User> uOptional = userRepository.findByEmail(email);

        if (uOptional.isPresent()) {
          SimpleMailMessage message = new SimpleMailMessage();

          String tokenResetPass = (String) (UUID.randomUUID().toString()).subSequence(0, 8);
          uOptional.get().setTokenResetPass(tokenResetPass);
          userRepository.save(uOptional.get());

          // sennd mail
          message.setTo(uOptional.get().getEmail());
          message.setSubject("Qu??n m???t kh???u App HIT");
          message.setText("????y m?? b?? m???t ?????i m???t kh???u c???a b???n " + tokenResetPass);

          this.emailSender.send(message);

          return ResponseEntity.status(HttpStatus.OK)
              .body(new Response(200, "???????ng link ?????i m???t kh???u ???? ???????c g???i v??o email c???a b???n"));
        } else {
          throw new InvalidException("Email kh??ng t???n t???i");
        }
      } else {
        throw new InvalidException("Kh??ng ????ng ?????nh d???ng email");
      }
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

  }

  @PostMapping("/check-token")
  public ResponseEntity<?> checkToken(@RequestParam(name = "token") String token){
    if (bucket.tryConsume(1)) {
      HashMap<String, Boolean> response = new HashMap<String, Boolean>();
      Optional<User> uOptional = userRepository.findByTokenResetPass(token);
      if (uOptional.isPresent()) {
        
        response.put("exist", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
      }
      else{
        response.put("exist", false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
      }
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");
  }

  @PostMapping("/resetPassword")
  public ResponseEntity<?> forgetPassword(@RequestParam String token, @RequestBody HashMap<String, String> password) {
    if (bucket.tryConsume(1)) {
      Optional<User> uOptional = userRepository.findByTokenResetPass(token);

      if (uOptional.isPresent()) {
        uOptional.get().setPassword(passwordEncoder.encode(password.get("password")));
        uOptional.get().setTokenResetPass("");
        userRepository.save(uOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "?????i m???t kh???u th??nh c??ng"));
      } else {
        throw new InvalidException("Token kh??ng h???p l???");
      }
    }
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("TOO_MANY_REQUESTS");

  }
}
