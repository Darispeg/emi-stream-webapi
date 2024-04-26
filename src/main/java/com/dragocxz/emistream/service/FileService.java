package com.dragocxz.emistream.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    Map<String, String> uploadFile(MultipartFile file);
    String removeFile(String idFile);
}
