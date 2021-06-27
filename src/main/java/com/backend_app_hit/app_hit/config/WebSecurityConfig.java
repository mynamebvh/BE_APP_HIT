package com.backend_app_hit.app_hit.config;

import com.backend_app_hit.app_hit.filter.JwtFilter;
import com.backend_app_hit.app_hit.services.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtFilter jwtFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
        .antMatchers("/api/v1/user/**", "/api/v1/post/**", "/api/v1/comment/**", "/api/v1/class/**" , "/api/v1/post/**").authenticated()
        .antMatchers("api/v1/admin/**").hasRole("ADMIN").and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
