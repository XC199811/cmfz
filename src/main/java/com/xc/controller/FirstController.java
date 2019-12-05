package com.xc.controller;

import com.xc.dao.ArticleDao;
import com.xc.dao.UserDao;
import com.xc.dao.VoiceDao;
import com.xc.entity.Article;
import com.xc.entity.Banner;
import com.xc.entity.Voice;
import com.xc.service.ArticleService;
import com.xc.service.BannerService;
import com.xc.service.UserService;
import com.xc.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/12/4.
 */
@RestController
@RequestMapping("first")
public class FirstController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private VoiceDao voiceDao;
    @RequestMapping("find")
    public Map<String,Object> find(String uid,String type,String sub_type){
        Map<String,Object> map= new HashMap<>();
        if("all".equals(type)){//主页的一级页面
            map.put("status","200");
            //轮播图
            List<Banner> banners = bannerService.searchFive();
            map.put("head",banners);
            //专辑
            List<Voice> voices = voiceService.searchSix();
            map.put("albums",voices);
            //文章
            List<Article> articles = articleService.searchFive();
            map.put("articles",articles);
            return map;
        }
        if("wen".equals(type)){//专辑的一级页面
            map.put("status","200");
            //查询到的专辑
            List<Voice> voices = voiceDao.selectAll();
            map.put("albums",voices);
            return map;
        }
        if("si".equals(type)){//文章的页面
            if("ssjy".equals(sub_type)||sub_type==null){//上师言教
                map.put("status","200");
                //根据uid去redis中查询其关注的上师id
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                redisTemplate.setValueSerializer(new StringRedisSerializer());
                List<String> shangShiIds = redisTemplate.opsForList().range(uid, 0, -1);
                List<Article> list=new ArrayList<>();
                for (String shangShiId : shangShiIds) {
                    List<Article> articles = articleService.searchShangShi(shangShiId);
                    list.addAll(articles);
                }
                map.put("articles",list);
                return map;
            }else {
                map.put("status","200");
                List<Article> list = articleService.searchNone();
                map.put("articles",list);
                return map;
            }
        }
        return null;
    }
}
