package com.music.backend.service.impl;


import com.cloudinary.Cloudinary;
import com.music.backend.model.FileModel;
import com.music.backend.repository.FileRepository;
import com.music.backend.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class StorageServiceImpl implements StorageService {

    final Logger logger = Logger.getLogger(StorageServiceImpl.class.getName());

    @Autowired
    private Cloudinary cloudinaryConfig;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileRepository fileRepository;

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String getExtensionByApacheCommonLib(String filename) {
        String fileExtension = FilenameUtils.getExtension(filename);
        System.out.println(fileExtension);
        if (fileExtension.length() > 0) {
            if (fileExtension.equals("mp3") || fileExtension.equals("mp4") || fileExtension.equals("aac") || fileExtension.equals("wav"))
                return "video";
            else if (fileExtension.equals("png") || fileExtension.equals("jpg") || fileExtension.equals("jpe") || fileExtension.equals("jpeg"))
                return "image";
        }
        return null;
    }

    @Override
    public FileModel uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map<String, String> cloudinaryFileAttributeMap = new HashMap<>();
            String fileName = uploadedFile.getName();
            String resourceType = getExtensionByApacheCommonLib(fileName);
            if (resourceType != null) {
                cloudinaryFileAttributeMap.put("resource_type", resourceType);
            } else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "File extension not accepted!!!");
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, cloudinaryFileAttributeMap);
//            return uploadResult.get("url").toString();
            FileModel fileModel = modelMapper.map(uploadResult, FileModel.class);
            fileModel.setCreatedAt(LocalDateTime.now());
            fileModel.setResourceType(resourceType);
            fileModel.setPublicId(uploadResult.get("public_id").toString());
            fileModel.setAssetId(uploadResult.get("asset_id").toString());
            fileRepository.save(fileModel);
            logger.info("Upload success!!!");
            return fileModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String downloadFile(String originalName, String publicId, String type, String resourceType) {
//        return cloudinaryConfig
//                .url()
//                .transformation(
//                        new Transformation()
//                                .flags("attachment:" + originalName)
//                                .fetchFormat(type))
//                .imageTag(publicId + "." + type);
        StringBuilder attachUrl = new StringBuilder();
        attachUrl.append("http://res.cloudinary.com/");
        attachUrl.append("linkdoan/");
        attachUrl.append(resourceType);
        attachUrl.append("/upload/");
        attachUrl.append("fl_attachment/");
        attachUrl.append(publicId);
        attachUrl.append(".");
        attachUrl.append(type);
        return attachUrl.toString();
    }

}
