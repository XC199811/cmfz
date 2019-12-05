package com.xc.service;

import com.xc.dao.OptionLDao;
import com.xc.entity.OptionL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2019/12/4.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OptionLServiceImpl implements OptionLService {
    @Autowired
    private OptionLDao optionLDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OptionL> findAll(String id) {
        return optionLDao.findByUid(id);
    }

    @Override
    public void add(OptionL optionL) {
        optionLDao.insert(optionL);
    }

    @Override
    public void delete(String uid, String id) {
        OptionL optionL=new OptionL();
        optionL.setId(id);
        optionL.setUser_id(uid);
        optionLDao.delete(optionL);
    }
}
