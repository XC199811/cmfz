package com.xc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2019/11/29.
 */
public class Log implements Serializable {
    @Id
    private String id;
    private String user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    @Column(name = "do")
    private String doo;
    private String success;

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", time=" + time +
                ", doo='" + doo + '\'' +
                ", success='" + success + '\'' +
                '}';
    }

    public Log() {
    }

    public Log(String id, String user, Date time, String doo, String success) {

        this.id = id;
        this.user = user;
        this.time = time;
        this.doo = doo;
        this.success = success;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDoo() {
        return doo;
    }

    public void setDoo(String doo) {
        this.doo = doo;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
