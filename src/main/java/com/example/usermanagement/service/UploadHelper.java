package com.example.usermanagement.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Component
public class UploadHelper {

    private final String UPLOADED_FOLDER = "photos/";

    public Path uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + new Timestamp(System.currentTimeMillis()).getTime() + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            return path;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
