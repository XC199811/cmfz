package com.xc.HttpUtil;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by lenovo on 2019/11/28.
 */
public class HttpUtil {
    /*
    * 作用
    * 1. 获取上传文件的网络路径
    * 2. 上传文件
    * */
    //dir是文件夹相对路径
    public static String getHttpUrl(MultipartFile file, HttpServletRequest request,String dir)  {
        //获取路径
        String realPath=request.getSession().getServletContext().getRealPath(dir);
        //判断文件夹是否存在
        File f=new File(realPath);
        if(!f.exists()){
            f.mkdirs();
        }
        //防止重名操作
        String oldName=file.getOriginalFilename();
        String newName=new Date().getTime()+"_"+oldName;
        try {
            file.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //相对路径:../xx/xx/xx.jpg
        //网络路径:http://ip:端口/项目名/文件存放位置
        String http=request.getScheme();
        String localhost=null;
        try {
            localhost= InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Integer serverPort=request.getServerPort();
        String contextPath=request.getContextPath();
        //网络拼接路径
        String uri=http+"://"+localhost.split("/")[1]+":"+serverPort+contextPath+dir+newName;
        return uri;
    }
}
