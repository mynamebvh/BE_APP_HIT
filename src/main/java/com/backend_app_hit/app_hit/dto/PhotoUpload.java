package com.backend_app_hit.app_hit.dto;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class PhotoUpload extends StoredFile {
  @Autowired
  private Cloudinary cloudinary;
  
  private String title;

  private MultipartFile file;

  public String getUrl() {
    if (version != null && format != null && publicId != null) {
      return cloudinary.url().resourceType(resourceType).type(type).format(format).version(version)
          .generate(publicId);
    } else
      return null;
  }

  public String getThumbnailUrl() {
    if (version != null && format != null && publicId != null) {
      return Singleton.getCloudinary().url().format(format).resourceType(resourceType).type(type).version(version)
          .transformation(new Transformation().width(150).height(150).crop("fit")).generate(publicId);
    } else
      return null;
  }

  public String getComputedSignature() {
    return getComputedSignature(Singleton.getCloudinary());
  }

  public boolean validSignature() {
    return getComputedSignature().equals(signature);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }
}