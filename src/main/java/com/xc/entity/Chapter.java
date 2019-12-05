package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/11/28.
 */
public class Chapter implements Serializable {
    @Id
    private String id;
    private String title;
    private String file;
    private String size;
    private String time;
    @Column(name = "voice_id")
    private String voice_id;
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", file='" + file + '\'' +
                ", size='" + size + '\'' +
                ", time='" + time + '\'' +
                ", voice_id='" + voice_id + '\'' +
                ", create_date=" + create_date +
                '}';
    }

    public Chapter() {
    }

    public Chapter(String id, String title, String file, String size, String time, String voice_id, Date create_date) {

        this.id = id;
        this.title = title;
        this.file = file;
        this.size = size;
        this.time = time;
        this.voice_id = voice_id;
        this.create_date = create_date;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVoice_id() {
        return voice_id;
    }

    public void setVoice_id(String voice_id) {
        this.voice_id = voice_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
