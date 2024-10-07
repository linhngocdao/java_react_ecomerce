package com.tutorial.api.mvc.api.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.StandardCopyOption;


@Service
public class ImageStorageService implements IStorageService {
    private final Path StorageFolder = Path.of("uploads");

    //Contructor
    public ImageStorageService() {
        //create folder if not exist
        try {
            Files.createDirectories(StorageFolder);
        } catch (IOException exception) {
            throw new RuntimeException("Could not create folder to store file", exception);
        }
    }

    private boolean isImage(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert fileExtension != null;
        return Arrays.asList(new String[]{"jpg", "jpeg", "png", "gif", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("Hello");
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            //check file is image
            if (!isImage(file)) {
                throw new RuntimeException("Failed to store file. Only image file is accepted");
            }
            //file max size <= 5MB
            float fileSize = file.getSize() / 1_000_000.0f;
            if (fileSize > 5.0f) {
                throw new RuntimeException("Failed to store file. File size must be less than 5MB");
            }
            // Sửa lại đoạn lấy file name: lấy tên gốc của file
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("_", "");
            generatedFileName = generatedFileName + "." + fileExtension;  // Đảm bảo phần mở rộng file là đúng

            Path targetLocation = this.StorageFolder.resolve(
                            Path.of(generatedFileName))
                    .normalize().toAbsolutePath();

            if (!targetLocation.getParent().equals(this.StorageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (IOException exception) {
            throw new RuntimeException("Fail to store file", exception);
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try{
            //list all file StorageFolder
            return Files.walk(StorageFolder, 1)
                    .filter(path -> !path.equals(this.StorageFolder) && !path.toString().contains("._"))
                    .map(this.StorageFolder::relativize);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to read stored files", exception);
        }
    }

    @Override
    public byte[] readFile(String fileName) {
        try {
            Path filePath = StorageFolder.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return Files.readAllBytes(filePath);
            } else {
                throw new RuntimeException("Could not read file" + fileName);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Could not read file" + fileName, exception);
        }
    }

    @Override
    public void deleteAll() {
        try {
            // Walk through all files and delete them, but keep the root folder
            Files.walk(StorageFolder, 1)  // 1: Only the files and directories directly inside the root
                    .filter(path -> !path.equals(this.StorageFolder))  // Filter out the root folder
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Could not delete file: " + path, e);
                        }
                    });
        } catch (IOException exception) {
            throw new RuntimeException("Failed to delete files", exception);
        }
    }
}
