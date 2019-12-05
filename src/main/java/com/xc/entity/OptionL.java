package com.xc.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lenovo on 2019/12/4.
 */
public class OptionL {
    @Id
    private String id;
    private String name;
    @Column(name = "user_id")
    private String user_id;

    @Override
    public String toString() {
        return "OptionL{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public OptionL() {
    }

    public OptionL(String id, String name, String user_id) {

        this.id = id;
        this.name = name;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
