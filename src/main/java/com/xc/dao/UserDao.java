package com.xc.dao;

import com.xc.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
public interface UserDao extends Mapper<User> {
    //查询用户关注的所有上师id
    public List<String> searchShangShiId(String id);
}
