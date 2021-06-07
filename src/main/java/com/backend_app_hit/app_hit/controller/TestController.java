package com.backend_app_hit.app_hit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
  
  @GetMapping
  public String hello(){
    return "Hello";
  }
}
