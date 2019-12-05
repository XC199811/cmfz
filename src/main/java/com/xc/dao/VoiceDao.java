package com.xc.dao;

import com.xc.entity.Voice;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/11/28.
 */
public interface VoiceDao extends Mapper<Voice> {
    //根据条件查询
    public List<Voice> search(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper,
            @Param(value = "begin") Integer begin,@Param(value = "rows") Integer rows
    );
    //根据条件查询出来的数量
    public Integer countSearch(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper
    );
    //随机查询6条
    public List<Voice> searchSix();
}
