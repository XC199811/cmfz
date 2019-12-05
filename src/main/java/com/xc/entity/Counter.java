package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/12/4.
 */
public class Counter implements Serializable {
    @Id
    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private Integer count;
    @Column(name = "class_id")
    private String class_id;
    @Column(name = "user_id")
    private String user_id;

    @Override
    public String toString() {
        return "Counter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", create_date=" + create_date +
                ", count=" + count +
                ", class_id='" + class_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Counter() {

    }

    public Counter(String id, String name, Date create_date, Integer count, String class_id, String user_id) {

        this.id = id;
        this.name = name;
        this.create_date = create_date;
        this.count = count;
        this.class_id = class_id;
        this.user_id = user_id;
    }
}
