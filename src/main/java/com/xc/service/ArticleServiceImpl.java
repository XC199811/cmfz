package com.xc.service;

import com.xc.dao.ArticleDao;
import com.xc.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/12/2.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page) {
        if(_search){
            //需要返回的对象又四个 当前页 page 查询到的集合 rows 总条数 records 总页数 total
            Map<String, Object> map = new HashMap<>();
            List<Article> articles = articleDao.searchLike(searchField, searchString, searchOper, (page - 1) * rows, rows);
            map.put("rows", articles);
            map.put("page", page);
            Integer i = articleDao.countLike(searchField,searchString,searchOper);
            map.put("records", i);
            Integer total = i % rows == 0 ? i / rows : i / rows + 1;
            map.put("total", total);
            return map;
        }else {
            //需要返回的对象又四个 当前页 page 查询到的集合 rows 总条数 records 总页数 total
            Map<String, Object> map = new HashMap<>();
            Article article = new Article();
            List<Article> articles = articleDao.selectByRowBounds(article, new RowBounds((page - 1) * rows, rows));
            map.put("rows", articles);
            map.put("page", page);
            Integer i = articleDao.selectCount(article);
            map.put("records", i);
            Integer total = i % rows == 0 ? i / rows : i / rows + 1;
            map.put("total", total);
            return map;
        }
    }
    @Override
    public void delete(String id) {
        Article article=new Article();
        article.setId(id);
        articleDao.delete(article);
    }

    @Override
    public void add(Article article) {
        articleDao.insert(article);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Article findOneById(String id) {
        Article article=new Article();
        article.setId(id);
        return articleDao.selectOne(article);
    }

    @Override
    public void update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> searchFive() {//随机查询五个
        return articleDao.searchFive();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> searchShangShi(String id) {
        return articleDao.searchShangShi(id);
    }

    @Override
    public List<Article> searchNone() {
        return articleDao.searchNone();
    }
}
