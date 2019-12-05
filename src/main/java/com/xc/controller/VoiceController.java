package com.xc.controller;

import com.xc.HttpUtil.HttpUtil;
import com.xc.dao.ChapterDao;
import com.xc.dao.VoiceDao;
import com.xc.entity.Chapter;
import com.xc.entity.Voice;
import com.xc.service.ChapterService;
import com.xc.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by lenovo on 2019/11/28.
 */
@Controller
@RequestMapping("voice")
public class VoiceController {
    @Autowired
    private VoiceService voiceService;
    @Autowired
    private VoiceDao voiceDao;
    @Autowired
    private ChapterDao chapterDao;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows,Integer page){
        return voiceService.findAll(_search,searchField,searchString,searchOper,rows, page);
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String oper, String id, Voice voice,HttpServletRequest request){
        if("edit".equals(oper)){
            if(voice.getImage().isEmpty())voice.setImage(null);
            return voiceService.update(voice);
        }
        if("del".equals(oper)){
            Map map = voiceService.delete(id);
            String fileName= (String) map.get("fileName");
            if(fileName!=null){
                String realPath=request.getSession().getServletContext().getRealPath("/upload/voice");
                File file=new File(realPath,fileName);
                file.delete();
            }
            return null;
        }
        if("add".equals(oper)){
            voice.setId(UUID.randomUUID().toString());
            voice.setCreate_date(new Date());
            return voiceService.add(voice);
        }
        return null;
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile image, HttpServletRequest request,String voiceId){
        HttpUtil httpUtil=new HttpUtil();
        String uri=httpUtil.getHttpUrl(image, request, "/upload/voice/");
        Voice voice=new Voice();
        voice.setId(voiceId);
        voice.setImage(uri);
        voiceService.update(voice);
    }
    @RequestMapping("detail")
    @ResponseBody
    public Map<String,Object> detail(String uidd,String id){
        Map<String,Object> map=new HashMap<>();
        map.put("status","200");
        //查询ablum专辑
        Voice voice=new Voice();
        voice.setId(id);
        Voice selectOne = voiceDao.selectOne(voice);
        map.put("ablum",selectOne);
        //查询其所有章节
        Chapter chapter=new Chapter();
        chapter.setVoice_id(id);
        List<Chapter> chapters = chapterDao.select(chapter);
        map.put("list",chapters);
        return map;
    }
}
