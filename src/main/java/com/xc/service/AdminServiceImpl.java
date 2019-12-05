package com.xc.service;

import com.xc.dao.AdminDao;
import com.xc.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenovo on 2019/11/26.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public void login(Admin admin) {//登录查询
        Admin adminSelect=new Admin();
        adminSelect.setUsername(admin.getUsername());
        Admin adminJudge = adminDao.selectOne(adminSelect);
        if(adminJudge==null)throw new RuntimeException("该用户不存在");
        if(!(adminJudge.getPassword().equals(admin.getPassword())))throw new RuntimeException("密码错误");
    }
}
