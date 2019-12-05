package com.xc.dao;

import com.xc.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/12/2.
 */
public interface ArticleDao extends Mapper<Article> {
    //条件查询
    public List<Article> searchLike(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper,
            @Param(value = "begin") Integer begin,@Param(value = "rows") Integer rows
    );
    //条件查询总个数
    public Integer countLike(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper
    );
    //随机查询五个
    public List<Article> searchFive();
    //查询该用户关注的所有的大师秒宝
    public List<Article> searchShangShi(String id);
    //查询所有的通用文章
    public List<Article> searchNone();
}
