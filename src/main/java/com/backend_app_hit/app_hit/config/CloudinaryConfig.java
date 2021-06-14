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
    config.put("cloud_name", "dhlmdhzbz");
    config.put("api_key", "664472915342121");
    config.put("api_secret", "qZuy6eUSptCh-XrgREQtpIWbNZY");
    Cloudinary cloudinary = new Cloudinary(config);
    return cloudinary;
  }

}
