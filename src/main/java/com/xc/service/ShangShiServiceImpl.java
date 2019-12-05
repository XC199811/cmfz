package com.xc.service;

import com.xc.dao.ShangShiDao;
import com.xc.entity.ShangShi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2019/12/2.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ShangShiServiceImpl implements ShangShiService {
    @Autowired
    private ShangShiDao shangShiDao;
    @Override
    public List<ShangShi> findAll() {
        return shangShiDao.selectAll();
    }
}
