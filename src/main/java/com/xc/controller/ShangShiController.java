package com.xc.controller;

import com.xc.entity.ShangShi;
import com.xc.service.ShangShiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/12/2.
 */
@Controller
@RequestMapping("ShangShi")
public class ShangShiController {
    @Autowired
    private ShangShiService shangShiService;
    @RequestMapping("findAll")
    @ResponseBody
    public List<ShangShi> findAll(){
        return shangShiService.findAll();
    }
    @RequestMapping("findAllFront")
    @ResponseBody
    public Map<String,Object> findAllFront(String uid){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        map.put("message","当前上师列表");
        List<ShangShi> shangShis = shangShiService.findAll();
        map.put("list", shangShis);
        return map;
    }
}
