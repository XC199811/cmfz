package com.xc.service;

import com.xc.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/12/2.
 */
public interface ArticleService {
    //分页查询所有
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page);
    //删除
    public void delete(String id);
    //添加
    public void add(Article article);
    //查找一个
    public Article findOneById(String id);
    //修改
    public void update(Article article);
    //随机查询五个
    public List<Article> searchFive();
    //查询该用户关注的上师文章
    public List<Article> searchShangShi(String id);
    //查询所有的通用文章
    public List<Article> searchNone();
}
