package com.xc.service;

import com.xc.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/11/27.
 */
public interface BannerService {
    //分页查询所有并传到jqgrid(还可根据条件查询)
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows,Integer page);
    //添加
    public Map add(Banner banner);
    //修改
    public Map update(Banner banner);
    //删除
    public Map delete(List<String> ids);
    //随机查询五条
    public List<Banner> searchFive();
}
