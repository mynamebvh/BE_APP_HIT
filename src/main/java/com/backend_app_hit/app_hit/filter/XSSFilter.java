package com.backend_app_hit.app_hit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.backend_app_hit.app_hit.security.XSSRequestWrapper;

import org.springframework.stereotype.Component;

@Component
public class XSSFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest) request);
    chain.doFilter(wrappedRequest, response);
  }
}
