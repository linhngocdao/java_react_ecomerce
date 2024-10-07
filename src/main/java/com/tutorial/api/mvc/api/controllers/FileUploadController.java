package com.tutorial.api.mvc.api.controllers;

import com.tutorial.api.mvc.api.models.ResponseObject;
import com.tutorial.api.mvc.api.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/file")
public class FileUploadController {
    //this controller will be used to upload file
    //injected service here
    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = imageStorageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "File uploaded successfully", fileName)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Failed", exception.getMessage(), null)
            );
        }
    }

    //GET IMAGE URL
    @GetMapping("/files/{fileName:.+}")
    // this is request: http://localhost:8080/api/v1/file/files/46bcf59e-8f84-447b-88a8-1a3b6cf591d2.jpg
    public ResponseEntity<byte[]> readFile(@PathVariable String fileName) {
        try {
            byte[] bytes = imageStorageService.readFile(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    //LOAD ALL FILES UPLOADED
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllFiles() {
        try {
            List<String> url = imageStorageService.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("Success", "Files loaded successfully", url));
        } catch (Exception exception) {
            return ResponseEntity.ok(new ResponseObject("Fail", "Files loaded failed", new String[]{}));
        }
    }

    // DELETE ALL FILES
    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseObject> deleteAllFiles() {
        try {
            imageStorageService.deleteAll();  // Gọi phương thức deleteAll() từ service
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "All files deleted successfully", null)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Failed", "Failed to delete files", null)
            );
        }
    }
}
