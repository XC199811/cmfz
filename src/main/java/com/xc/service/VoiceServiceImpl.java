package com.xc.service;

import com.xc.dao.VoiceDao;
import com.xc.entity.Voice;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/11/28.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VoiceServiceImpl implements VoiceService {
    @Autowired
    private VoiceDao voiceDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page) {//查询所有
        if(_search){
            Map<String, Object> map = new HashMap<>();
            //需要传的参数
            //查询到的集合 rows  总条数 records  总页数 total 当前页 page
            List<Voice> voices = voiceDao.search(searchField,searchString,searchOper,(page-1)*rows,rows);
            map.put("rows", voices);
            int count = voiceDao.countSearch(searchField,searchString,searchOper);
            System.out.println(count);
            map.put("records", count);
            map.put("page", page);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }else {
            Map<String, Object> map = new HashMap<>();
            //需要传的参数
            //查询到的集合 rows  总条数 records  总页数 total 当前页 page
            List<Voice> voices = voiceDao.selectByRowBounds(new Voice(), new RowBounds((page - 1) * rows, rows));
            map.put("rows", voices);
            int count = voiceDao.selectCount(new Voice());
            map.put("records", count);
            map.put("page", page);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }
    }
    @Override
    public Map add(Voice voice) {
        HashMap map=new HashMap();
        voiceDao.insert(voice);
        map.put("voiceId",voice.getId());
        map.put("status",200);
        return map;
    }

    @Override
    public Map delete(String id) {
        Voice voice=new Voice();
        voice.setId(id);
        Voice selectOne = voiceDao.selectOne(voice);
        String[] strings = selectOne.getImage().split("/");
        String fileName=strings[strings.length-1];
        HashMap map=new HashMap();
        map.put("fileName",fileName);
        map.put("status",200);
        voiceDao.delete(voice);
        return map;
    }

    @Override
    public Map update(Voice voice) {
        voiceDao.updateByPrimaryKeySelective(voice);
        return null;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Voice> searchSix() {//随机查询6个
        return voiceDao.searchSix();
    }
}
