package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/11/27.
 */
public class Banner implements Serializable {
    @Id
    private String id;
    @Column(name = "`describe`")
    private String describe;
    private String title;
    private String file;
    private String href;
    private String status;
    @Column(name="create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", describe='" + describe + '\'' +
                ", title='" + title + '\'' +
                ", file='" + file + '\'' +
                ", href='" + href + '\'' +
                ", status='" + status + '\'' +
                ", create_date=" + create_date +
                '}';
    }

    public Banner() {
    }

    public Banner(String id, String describe, String title, String file, String href, String status, Date create_date) {

        this.id = id;
        this.describe = describe;
        this.title = title;
        this.file = file;
        this.href = href;
        this.status = status;
        this.create_date = create_date;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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
}
