package com.xc.service;

import com.xc.entity.OptionL;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
public interface OptionLService {
    //根据用户id查询所有功课
    public List<OptionL> findAll(String id);
    //添加功课
    public void add(OptionL optionL);
    //删除功课
    public void delete(String uid,String id);
}
