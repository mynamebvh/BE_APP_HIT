package com.backend_app_hit.app_hit.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
  public static final String stripXSS = null;

  public XSSRequestWrapper(HttpServletRequest request) {
    super(request);
    // TODO Auto-generated constructor stub
  }

  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return null;
    }
    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = stripXSS(values[i]);
    }
    return encodedValues;
  }

  @Override
  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);
    return stripXSS(value);
  }

 

  @Override
  public Enumeration<String> getHeaders(String name) {
    List result = new ArrayList<>();
    Enumeration headers = super.getHeaders(name);
    while (headers.hasMoreElements()) {
        String header = (String) headers.nextElement();
        String[] tokens = header.split(",");
        for (String token : tokens) {
            result.add(stripXSS(token));
        }
    }
    return Collections.enumeration(result);
  }

  public String stripXSS(String value) {
    if (value != null) {
      // NOTE: It's highly recommended to use the ESAPI library and uncomment the
      // following line to
      // avoid encoded attacks.
      // value = ESAPI.encoder().canonicalize(value);

      // Avoid null characters
      value = value.replaceAll("", "");

      // Avoid anything between script tags
      Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid anything in a src='...' type of expression
      scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
          Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");

      scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
          Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");

      // Remove any lonesome </script> tag
      scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");

      // Remove any lonesome <script ...> tag
      scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid eval(...) expressions
      scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid expression(...) expressions
      scriptPattern = Pattern.compile("expression\\((.*?)\\)",
          Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid javascript:... expressions
      scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid vbscript:... expressions
      scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
      value = scriptPattern.matcher(value).replaceAll("");

      // Avoid onload= expressions
      scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
      value = scriptPattern.matcher(value).replaceAll("");
    }
    return value;
  }

  
}
