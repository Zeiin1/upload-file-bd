package com.example.uploadfilebd.controller;

import com.example.uploadfilebd.entity.Files;
import com.example.uploadfilebd.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {
    private AttachmentService attachmentService;

    public AttachmentController (AttachmentService attachmentService)
    {
        this.attachmentService = attachmentService;
    }


    @PostMapping("/upload")
    public Files uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

       return attachmentService.saveFile(file);
    }

    @GetMapping ("/fileinfo/{filename}")
    public Files fileInformantion(@PathVariable("filename")String filename) throws Exception {
        return attachmentService.findFile(filename);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {
        Files files = null;
        files = attachmentService.findFile(filename);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + files.getFileName()
                                + "\"")
                .body(new ByteArrayResource(files.getData()));
    }
}
