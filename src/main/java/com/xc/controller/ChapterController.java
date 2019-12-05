package com.xc.controller;

import com.xc.HttpUtil.HttpUtil;
import com.xc.entity.Chapter;
import com.xc.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lenovo on 2019/11/28.
 */
@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows,Integer page,String voice_id){
        return chapterService.findAll(_search,searchField,searchString,searchOper,rows, page, voice_id);
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String oper, String id, String voice_id, Chapter chapter, HttpServletRequest request){
        if("edit".equals(oper)){
            if(chapter.getFile()=="")chapter.setFile(null);
            return chapterService.update(chapter);
        }
        if("add".equals(oper)){
            chapter.setId(UUID.randomUUID().toString());
            chapter.setVoice_id(voice_id);
            return chapterService.add(chapter);
        }
        if("del".equals(oper)){
            Map map = chapterService.delete(id);
            String fileName= (String) map.get("fileName");
            if(fileName!=null){
                String realPath=request.getSession().getServletContext().getRealPath("/upload/music");
                File file=new File(realPath,fileName);
                file.delete();
            }
            return null;
        }
        return null;
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile file,HttpServletRequest request,String chapterId) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        HttpUtil httpUtil=new HttpUtil();
        String uri=httpUtil.getHttpUrl(file, request, "/upload/music/");
        Chapter chapter=new Chapter();
        chapter.setId(chapterId);
        chapter.setFile(uri);
        //获取绝对路径
        String realPath=request.getSession().getServletContext().getRealPath("/upload/music/");
        //获取文件名
        String[] strings=uri.split("/");
        String fileName=strings[strings.length-1];
        File fileMusic=new File(realPath,fileName);
        //文件字节长度
        long length=fileMusic.length();
        String size=length/1024/1024+"MB";
        chapter.setSize(size);
        MP3File mp3File= (MP3File) AudioFileIO.read(fileMusic);
        MP3AudioHeader mp3AudioHeader = mp3File.getMP3AudioHeader();
        //获取当前音频有多少秒
        Integer trackLength=mp3AudioHeader.getTrackLength();
        String min=trackLength/60+"分";
        String s=trackLength%60+"秒";
        chapter.setTime(min+s);
        chapterService.update(chapter);
    }
    @RequestMapping("download")
    @ResponseBody
    public void download(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //根据相对路径获得绝对路径
        String realPath=request.getSession().getServletContext().getRealPath("/upload/music");
        //获取文件名
        String[] strings=url.split("/");
        String fileName=strings[strings.length-1];
        //根据文件名去指定的文件读取文件
        FileInputStream is=new FileInputStream(new File(realPath,fileName));
        //设置下载响应头
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //通过响应流响应
        ServletOutputStream outputStream=response.getOutputStream();
        //流的复制
        IOUtils.copy(is,outputStream);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(outputStream);
    }
}
