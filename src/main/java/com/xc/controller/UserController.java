package com.xc.controller;

import com.xc.dao.ShangShiDao;
import com.xc.dao.UserDao;
import com.xc.entity.ShangShi;
import com.xc.entity.User;
import com.xc.service.UserService;
import com.xc.util.SendCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2019/12/4.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private ShangShiDao shangShiDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("login")
    public Map<String,Object> login(User user){
        Map<String,Object> map=new HashMap<>();
        try {
            User loginUser = userService.login(user);
            map.put("status","200");
            map.put("user",loginUser);
        } catch (Exception e) {
            map.put("status","-200");
            map.put("message",e.getMessage());
        }
        return  map;
    }
    @RequestMapping("sendCode")
    public Map<String,String> sendCode(String phone){
        Map<String,String> map=new HashMap<>();
        try {
            String code=UUID.randomUUID().toString().substring(0,5);
            SendCode.sendCode(phone,code);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set("code",code);
            redisTemplate.expire(phone,60, TimeUnit.SECONDS);
            map.put("status","200");
            map.put("message","验证码已成功发送至"+phone);
        } catch (Exception e) {
            map.put("status","-200");
            map.put("message","验证码发送失败请重试");
        }
        return map;
    }
    @RequestMapping("regist")
    public Map<String,Object> regist(String code){
        Map<String,Object> map=new HashMap<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        String codeOld= (String) redisTemplate.opsForValue().get("code");
        if(codeOld.equals(code)){
            map.put("status","200");
            map.put("message","注册成功");
        }else {
            map.put("status","-200");
            map.put("message","验证码有误");
        }
        return map;
    }
    @RequestMapping("registAll")
    public Map<String,Object> registAll(User user){
        Map<String,Object> map=new HashMap<>();
        try {
            user.setId(UUID.randomUUID().toString());
            if(user.getPhoto()==null)user.setPhoto("15151415.jpg");
            userService.add(user);
            map.put("status","200");
            map.put("user",user);
        } catch (Exception e) {
            map.put("status","-200");
            map.put("message","注册异常");
        }
        return map;
    }
    @RequestMapping("update")
    public Map<String,Object> update(User user,String uid,String sign,String nick_name){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        user.setId(uid);
        user.setSignature(sign);
        user.setFaname(nick_name);
        userService.update(user);
        User user1=new User();
        user1.setId(uid);
        User user2 = userDao.selectOne(user1);
        map.put("user",user2);
        return map;
    }
    @RequestMapping("guanzhu")
    public Map<String,Object> guanzhu(String uid,String id){
        //将关注的信息分别1存入redis
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForList().leftPush(uid,id);
        redisTemplate.opsForList().leftPush("s"+id,uid);
        Map<String,Object> map=new HashMap<>();
        map.put("status", "200");
        List<String> shangIds = redisTemplate.opsForList().range(uid, 0, -1);
        List<ShangShi> shangShis=new ArrayList<>();
        for (String shangId : shangIds) {
            ShangShi shangShi=new ShangShi();
            shangShi.setId(shangId);
            ShangShi shangShi1 = shangShiDao.selectOne(shangShi);
            shangShis.add(shangShi1);
        }
        map.put("message", "关注成功");
        map.put("list",shangShis);
        return map;
    }
    @RequestMapping("recommend")
    public List<User> recommend(String uid){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //获取上师id的集合
        List<String> shangShiIds=redisTemplate.opsForList().range(uid, 0, -1);
        //通过上师id的集合查找所有关注其的用户id并且合并去重
        Set<String> all=new HashSet<>();
        List<String> userIds=new ArrayList<>();
        for (String shangShiId : shangShiIds) {
            List<String> list=redisTemplate.opsForList().range("s"+shangShiId,0,-1);
            userIds.addAll(list);
        }
        for (String userId : userIds) {
            all.add(userId);
        }
        all.remove(uid);
        List<User> users=new ArrayList<>();
        for (String s : all) {
            User user=new User();
            user.setId(s);
            User selectOne = userDao.selectOne(user);
            users.add(selectOne);
        }
        return users;
    }
}
