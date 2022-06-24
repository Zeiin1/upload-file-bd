package com.example.uploadfilebd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@NoArgsConstructor
public class Files {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String date;
    private String time;
    private String downloadurl;
    private String fileType;
    @Lob
    private byte [] data;

    public Files(String fileName,String date, String time, byte[] data, String downloadurl,String fileType)
    {

        this.fileName = fileName;
        this.date = date;
        this.time=time;
        this.data = data;
        this.downloadurl= downloadurl;
        this.fileType =fileType;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }


    public String getTime() {
        return time;
    }

    public byte[] getData() {
        return data;
    }

    public String getDate() {
        return date;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getId() {
        return id;
    }
}
