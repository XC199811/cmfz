package com.xc.dao;

import com.xc.entity.OptionL;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
public interface OptionLDao extends Mapper<OptionL> {
    //根据用户id查询所有功课
    public List<OptionL> findByUid(String uid);
}
