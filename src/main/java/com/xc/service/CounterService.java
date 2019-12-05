package com.xc.service;

import com.xc.entity.Counter;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
public interface CounterService {
    //根据uid和功课id查询
    public List<Counter> findByUidAndId(String uid,String id);
    //添加计数器
    public void add(Counter counter);
    //删除计数器
    public void delete(String uid,String id);
    //表更计数器
    public void update(Counter counter);
}
