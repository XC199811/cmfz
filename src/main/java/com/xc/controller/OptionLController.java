package com.xc.controller;

import com.xc.dao.OptionLDao;
import com.xc.entity.OptionL;
import com.xc.service.OptionLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lenovo on 2019/12/4.
 */
@RequestMapping("optionL")
@RestController
public class OptionLController {
    @Autowired
    private OptionLService optionLService;
    @Autowired
    private OptionLDao optionLDao;
    @RequestMapping("find")
    public Map<String,Object> find(String uid){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        List<OptionL> optionLS = optionLService.findAll(uid);
        map.put("option",optionLS);
        return map;
    }
    @RequestMapping("add")
    public Map<String,Object> add(String uid,String title){
        Map<String,Object> map=new HashMap<>();
        OptionL optionL=new OptionL();
        optionL.setId(UUID.randomUUID().toString());
        optionL.setName(title);
        optionL.setUser_id(uid);
        optionLService.add(optionL);
        map.put("status","200");
        map.put("option",optionL);
        return map;
    }
    @RequestMapping("delete")
    public Map<String,Object> delete(String uid,String id){
        Map<String,Object> map=new HashMap<>();
        OptionL optionL=new OptionL();
        optionL.setId(id);
        OptionL selectOne = optionLDao.selectOne(optionL);
        map.put("status", "200");
        map.put("option", selectOne);
        optionLService.delete(uid,id);
        return map;
    }
}
