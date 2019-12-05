package com.xc.service;

import com.xc.entity.Chapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/11/28.
 */
public interface ChapterService {
    //根据专辑id查询所有
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page,String voice_id);
    //添加到voice下
    public Map add(Chapter chapter);
    //删除voice下的
    public Map delete(String id);
    //修改
    public Map update(Chapter chapter);
    //随机查询五个
    public List<Chapter> searchFive();
}
