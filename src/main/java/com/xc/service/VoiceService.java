package com.xc.service;

import com.xc.entity.Voice;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/11/28.
 */
public interface VoiceService {
    //查询所有
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page);
    public Map add(Voice voice);
    public Map delete(String id);
    public Map update(Voice voice);
    //随机查询6个
    public List<Voice> searchSix();
}
