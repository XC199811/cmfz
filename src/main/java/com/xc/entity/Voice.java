package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/11/28.
 */
public class Voice implements Serializable {
    @Id
    private String id;
    private String title;
    private String image;
    private String level;
    private String author;
    private String boyin;
    private String count;
    private String synopsis;
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private String status;

    @Override
    public String toString() {
        return "Voice{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", level='" + level + '\'' +
                ", author='" + author + '\'' +
                ", boyin='" + boyin + '\'' +
                ", count='" + count + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", create_date=" + create_date +
                ", status='" + status + '\'' +
                '}';
    }

    public Voice() {
    }

    public Voice(String id, String title, String image, String level, String author, String boyin, String count, String synopsis, Date create_date, String status) {

        this.id = id;
        this.title = title;
        this.image = image;
        this.level = level;
        this.author = author;
        this.boyin = boyin;
        this.count = count;
        this.synopsis = synopsis;
        this.create_date = create_date;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBoyin() {
        return boyin;
    }

    public void setBoyin(String boyin) {
        this.boyin = boyin;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
