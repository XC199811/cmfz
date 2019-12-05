package com.xc.service;

import com.xc.dao.CounterDao;
import com.xc.entity.Counter;
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
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Counter> findByUidAndId(String uid, String id) {
        Counter counter=new Counter();
        counter.setClass_id(id);
        counter.setUser_id(uid);
        return counterDao.select(counter);
    }

    @Override
    public void add(Counter counter) {
        counterDao.insert(counter);
    }

    @Override
    public void delete(String uid, String id) {
        Counter counter=new Counter();
        counter.setUser_id(uid);
        counter.setId(id);
        counterDao.delete(counter);
    }

    @Override
    public void update(Counter counter) {
        counterDao.updateByPrimaryKeySelective(counter);
    }
}
