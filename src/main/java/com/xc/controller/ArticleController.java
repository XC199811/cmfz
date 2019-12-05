package com.xc.controller;

import com.xc.HttpUtil.HttpUtil;
import com.xc.dao.ArticleDao;
import com.xc.entity.Article;
import com.xc.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2019/12/2.
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String, Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page){
        return articleService.findAll(_search,searchField,searchString,searchOper,rows,page);
    }
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String id){
        articleService.delete(id);
    }
    @RequestMapping("insertArticle")
    @ResponseBody
    public void insertArticle(Article article, MultipartFile articleImg, HttpServletRequest request){
        String dir="/upload/article/";
        //处理图片
        String httpUrl = HttpUtil.getHttpUrl(articleImg, request, dir);
        article.setImage(httpUrl);
        article.setId(UUID.randomUUID().toString());
        article.setCreate_date(new Date());
        article.setPublish_date(new Date());
        if("1".equals(article.getStatus())){
            article.setStatus("展示");
        }else {
            article.setStatus("不展示");
        }
        articleService.add(article);
    }
    @RequestMapping("uploadImg")
    @ResponseBody
    public Map uploadImg(MultipartFile imgFile,HttpServletRequest request){
        HashMap hashMap=new HashMap();
        String dir="/upload/text/";
        try {
            String httpUrl = HttpUtil.getHttpUrl(imgFile, request,dir);
            hashMap.put("error",0);
            hashMap.put("url",httpUrl);
        } catch (Exception e) {
            hashMap.put("error",1);
            hashMap.put("message","上传错误");
            e.printStackTrace();
        }
        return hashMap;
    }
    @RequestMapping("showAllImgs")
    @ResponseBody
    public Map showAllImgs(HttpSession session,HttpServletRequest request){
        //获取文件绝对路径
        String realPath=session.getServletContext().getRealPath("/upload/text");
        //准备返回的Json数据
        HashMap hashMap=new HashMap();
        ArrayList arrayList=new ArrayList();
        //获取目标文件夹
        File file=new File(realPath);
        File[] files = file.listFiles();
        //遍历文件夹中的文件
        for(File file1:files){
            //文件属性封装
            HashMap fileMap=new HashMap();
            fileMap.put("id_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            //获取文件后缀| 文件类型
            String extension=FilenameUtils.getExtension(file1.getName());
            fileMap.put("filetype", extension);
            fileMap.put("filename",file1.getName());
            //获取文件上传时间 1.截取时间戳 2.创建格式转化对象 3.格式类型转换
            String s=file1.getName().split("_")[0];
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           String format=simpleDateFormat.format(new Date(Long.valueOf(s)));
           fileMap.put("datetime",format);
           arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        hashMap.put("total_count",arrayList.size());
        //返回路径为项目名+文件夹路径
        hashMap.put("current_url",request.getContextPath()+"/upload/text/");
        return hashMap;
    }
    @RequestMapping("updateArticle")
    @ResponseBody
    public void updateArticle(MultipartFile articleImg,HttpServletRequest request,Article article){
        if(!(articleImg.getOriginalFilename().isEmpty()||articleImg.getOriginalFilename()==null)){
            String dir="/upload/article/";
            //处理图片
            String httpUrl = HttpUtil.getHttpUrl(articleImg, request, dir);
            article.setImage(httpUrl);
        }
        if("1".equals(article.getStatus())){
            article.setStatus("展示");
        }else {
            article.setStatus("不展示");
        }
        articleService.update(article);
    }
    @RequestMapping("detail")
    @ResponseBody
    public Map<String,Object> detailArticle(String uid,String id){
        Article article=new Article();
        article.setId(id);
        Article selectOne = articleDao.selectOne(article);
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        map.put("article",selectOne);
        return map;
    }
}
