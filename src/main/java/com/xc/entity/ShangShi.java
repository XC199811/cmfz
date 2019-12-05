package com.xc.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lenovo on 2019/12/2.
 */
public class ShangShi implements Serializable {
    @Id
    private String id;
    private String name;
    private String nickname;
    private String image;
    private String status;

    @Override
    public String toString() {
        return "ShangShi{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public ShangShi() {
    }

    public ShangShi(String id, String name, String nickname, String image, String status) {

        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.image = image;
        this.status = status;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
