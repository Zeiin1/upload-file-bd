package com.example.uploadfilebd.service;

import com.example.uploadfilebd.entity.Files;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Files saveFile(MultipartFile file);

    Files findFile(String fileName) throws Exception;

    Files getAttachment(String fileId) throws Exception;
}
