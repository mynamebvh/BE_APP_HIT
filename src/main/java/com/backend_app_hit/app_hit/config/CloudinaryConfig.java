package com.backend_app_hit.app_hit.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

  @Bean
  public Cloudinary config() {
    Map<String,String> config = new HashMap<>();
    config.put("cloud_name", "");
    config.put("api_key", "");
    config.put("api_secret", "");
    Cloudinary cloudinary = new Cloudinary(config);
    return cloudinary;
  }

}
