package com.music.backend.service;

import com.cloudinary.Url;
import com.music.backend.model.FileModel;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    FileModel uploadFile(MultipartFile files);

    String downloadFile(String originalName, String publicId, String type, String resourceType);

}
