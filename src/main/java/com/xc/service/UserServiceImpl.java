package com.xc.service;

import com.xc.dao.UserDao;
import com.xc.entity.User;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User login(User user) {
        User userSearch=new User();
        userSearch.setPhone(user.getPhone());
        User userOld = userDao.selectOne(userSearch);
        if(userOld==null)throw new RuntimeException("该手机号未存在注册用户");
        if(!(userOld.getPassword().equals(user.getPassword())))throw new RuntimeException("密码不正确");
        return userOld;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> searchShangShiId(String id) {
        return userDao.searchShangShiId(id);
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }
}
