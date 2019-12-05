package com.xc.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/12/4.
 */
public class User implements Serializable {
    @Id
    private String id;
    private String faname;
    private String name;
    private String sex;
    private String location;
    private String signature;
    private String phone;
    private String password;
    private String salt;
    private String status;
    @Column(name = "create_date")
    private Date create_date;
    @Column(name = "login_date")
    private Date login_date;
    @Column(name = "shangshi_id")
    private String shangshi_id;
    private String photo;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", faname='" + faname + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", location='" + location + '\'' +
                ", signature='" + signature + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", create_date=" + create_date +
                ", login_date=" + login_date +
                ", shangshi_id='" + shangshi_id + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaname() {
        return faname;
    }

    public void setFaname(String faname) {
        this.faname = faname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public String getShangshi_id() {
        return shangshi_id;
    }

    public void setShangshi_id(String shangshi_id) {
        this.shangshi_id = shangshi_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User() {

    }

    public User(String id, String faname, String name, String sex, String location, String signature, String phone, String password, String salt, String status, Date create_date, Date login_date, String shangshi_id, String photo) {

        this.id = id;
        this.faname = faname;
        this.name = name;
        this.sex = sex;
        this.location = location;
        this.signature = signature;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.create_date = create_date;
        this.login_date = login_date;
        this.shangshi_id = shangshi_id;
        this.photo = photo;
    }
}
