package com.tutorial.api.mvc.api.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll(); // load all files trong thư mục
    public byte[] readFile(String fileName); // đọc file
    public void deleteAll(); // xóa tất cả file
}
