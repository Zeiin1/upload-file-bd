package com.example.uploadfilebd.repository;

import com.example.uploadfilebd.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Files,String> {
     public Files getFirstByFileName(String fileName);
}
