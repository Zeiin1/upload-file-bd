package com.example.uploadfilebd.service;

import com.example.uploadfilebd.entity.Files;
import com.example.uploadfilebd.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentRepository attachmentRepository;

    public  AttachmentServiceImpl (AttachmentRepository attachmentRepository)
    {
        this.attachmentRepository = attachmentRepository;
    }


    @Override
    public Files saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String date = fileName.substring(0,2)+"."+fileName.substring(2,4)+"."+fileName.substring(4,8);
        String time =fileName.substring(9,11)+":"+fileName.substring(11,13)+":"+fileName.substring(13,15);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();
        try {
            Files files = new Files(fileName,date,time, file.getBytes(),url,file.getContentType());

            return attachmentRepository.save(files);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Files findFile(String fileName) throws Exception {
        Files files = attachmentRepository.getFirstByFileName(fileName);
        if(files==null)
        {
            throw new Exception("there is no file with name "+fileName);
        }






        return files;
    }
    @Override
    public Files getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
