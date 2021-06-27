package com.backend_app_hit.app_hit.helpers;

import com.backend_app_hit.app_hit.dto.PhotoUpload;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class PhotoUploadValidator {

  public boolean supports(Class clazz) {
    return PhotoUpload.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "title", "title.empty");
    PhotoUpload pu = (PhotoUpload) obj;
    if (pu.getFile() == null || pu.getFile().isEmpty()) {
      if (!pu.validSignature()) {
        e.rejectValue("signature", "signature.mismatch");
      }
    }
  }
}