package com.music.backend.controller;

import com.music.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileStorageController {

    @Autowired
    StorageService storageService;

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("originalName") String originalName,
                                          @RequestParam("publicId") String publicId,
                                          @RequestParam("type")  String type,
                                          @RequestParam("resourceType")  String resourceType) {
        return new ResponseEntity<>(
                storageService.downloadFile(originalName, publicId, type, resourceType), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
    }

}
