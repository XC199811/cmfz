package com.xc.dao;

import com.xc.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/11/28.
 */
public interface ChapterDao extends Mapper<Chapter> {
    //条件查询
    public List<Chapter> search(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper,
            @Param(value = "begin") Integer begin,@Param(value = "rows") Integer rows,
            @Param(value = "voice_id") String voice_id
    );
    //条件查询数量
    public Integer countSearch(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper,
            @Param(value = "voice_id") String voice_id
    );
    //随机查询5条
    public List<Chapter> searchFive();
}
