package com.xc.dao;

import com.xc.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/11/27.
 */
public interface BannerDao extends Mapper<Banner>,DeleteByIdListMapper<Banner,String> {
    //根据条件查询
    public List<Banner> search(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper,
            @Param(value = "begin") Integer begin,@Param(value = "rows") Integer rows
    );
    //根据条件查询
    public Integer count(
            @Param(value = "searchField")String searchField,
            @Param(value = "searchString")String searchString,@Param(value = "searchOper")String searchOper
    );
    //随机查询五条
    public List<Banner> searchFive();
}
