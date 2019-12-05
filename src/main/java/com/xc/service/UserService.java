package com.xc.service;

import com.xc.entity.User;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
public interface UserService {
    //登录查询
    public User login(User user);
    //查询该用户关注的所有上师id
    public List<String> searchShangShiId(String id);
    //添加用户
    public void add(User user);
    //修改用户信息
    public void update(User user);
}
