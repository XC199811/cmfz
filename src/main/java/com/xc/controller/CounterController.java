package com.xc.controller;

import com.xc.dao.CounterDao;
import com.xc.entity.Counter;
import com.xc.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by lenovo on 2019/12/4.
 */
@RequestMapping("counter")
@RestController
public class CounterController {
    @Autowired
    private CounterService counterService;
    @Autowired
    private CounterDao counterDao;
    @RequestMapping("find")
    public Map<String,Object> find(String uid,String id){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        List<Counter> counters = counterService.findByUidAndId(uid, id);
        map.put("counters",counters);
        return map;
    }
    @RequestMapping("add")
    public Map<String,Object> add(String uid,String title,String class_id){
        Map<String,Object> map=new HashMap<>();
        Counter counter=new Counter();
        counter.setId(UUID.randomUUID().toString());
        counter.setUser_id(uid);
        counter.setClass_id(class_id);
        counter.setCount(0);
        counter.setCreate_date(new Date());
        counter.setName(title);
        counterService.add(counter);
        map.put("status","200");
        map.put("counters",counter);
        return map;
    }
    @RequestMapping("delete")
    public Map<String,Object> delete(String uid,String id){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        Counter counter=new Counter();
        counter.setId(id);
        Counter selectOne = counterDao.selectOne(counter);
        map.put("counters",selectOne);
        counterService.delete(uid, id);
        return map;
    }
    @RequestMapping("update")
    public Map<String,Object> update(String uid,String id,Integer count){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        Counter counter=new Counter();
        counter.setUser_id(uid);
        counter.setId(id);
        counter.setCount(count);
        counterService.update(counter);
        Counter counter1=new Counter();
        counter.setId(id);
        Counter counter2 = counterDao.selectOne(counter);
        map.put("counters",counter2);
        return map;
    }
}
