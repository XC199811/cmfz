package com.xc.controller;

import com.xc.entity.Banner;
import com.xc.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by lenovo on 2019/11/27.
 */
@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows,Integer page){
        return bannerService.findAll(_search,searchField,searchString,searchOper,rows,page);
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String oper, String[] id, Banner banner){
        if("edit".equals(oper)){
            if(banner.getFile().isEmpty())banner.setFile(null);
            Map map = bannerService.update(banner);
            return map;
        }
        if("del".equals(oper)){
            List<String> resultList= new ArrayList<>(Arrays.asList(id));
            Map map = bannerService.delete(resultList);
            return map;
        }
        if("add".equals(oper)){
            banner.setId(UUID.randomUUID().toString().replace("-",""));
            banner.setCreate_date(new Date());
            Map map = bannerService.add(banner);
            return map;
        }
        return null;
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile file, HttpServletRequest request,String bannerId) throws IOException {
        //获取绝对路径
        String realPath=request.getSession().getServletContext().getRealPath("/upload/image");
        //判断路径文件夹是否存在
        File file1=new File(realPath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        //防止重名操作
        String oldName=file.getOriginalFilename();
        String newName=new Date().getTime()+"_"+oldName;
        file.transferTo(new File(realPath,newName));
        //相对路径:项目名/文件/文件/文件名
        //网络路径:http://IP:端口/项目名/文件存放的文件夹名/文件名
        String http=request.getScheme();//协议名
        String localhost= InetAddress.getLocalHost().toString();//ip地址
        Integer serverPort=request.getServerPort();//端口号
        String contextPath=request.getContextPath();//项目名
        String uri=http+"://"+localhost.split("/")[1]+":"+serverPort+contextPath+"/upload/image/"+newName;
        Banner banner=new Banner();
        banner.setId(bannerId);
        banner.setFile(uri);
        bannerService.update(banner);
    }
}
