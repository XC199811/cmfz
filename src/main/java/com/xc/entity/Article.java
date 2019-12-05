package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/12/2.
 */
public class Article implements Serializable {
    @Id
    private String id;
    private String title;
    private String status;
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    @Column(name = "publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publish_date;
    private String content;
    @Column(name = "author_id")
    private String author_id;
    private String image;
    @Transient
    private String a_photo;
    @Transient
    private String author;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", create_date=" + create_date +
                ", publish_date=" + publish_date +
                ", content='" + content + '\'' +
                ", author_id='" + author_id + '\'' +
                ", image='" + image + '\'' +
                ", a_photo='" + a_photo + '\'' +
                ", author='" + author + '\'' +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getA_photo() {
        return a_photo;
    }

    public void setA_photo(String a_photo) {
        this.a_photo = a_photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Article() {

    }

    public Article(String id, String title, String status, Date create_date, Date publish_date, String content, String author_id, String image, String a_photo, String author) {

        this.id = id;
        this.title = title;
        this.status = status;
        this.create_date = create_date;
        this.publish_date = publish_date;
        this.content = content;
        this.author_id = author_id;
        this.image = image;
        this.a_photo = a_photo;
        this.author = author;
    }
}
