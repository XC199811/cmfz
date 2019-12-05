package com.xc.service;

import com.xc.dao.ChapterDao;
import com.xc.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows, Integer page,String voice_id) {
        if(_search){
            Map<String, Object> map = new HashMap<>();
            //jqgrid需要返回的数据 四个
            //查询到的总数据 rows 当前页 page 总条数 records 总页数 total
            List<Chapter> chapters = chapterDao.search(searchField, searchString, searchOper, (page - 1) * rows, rows, voice_id);
            map.put("rows", chapters);
            map.put("page", page);
            Integer count = chapterDao.countSearch(searchField, searchString, searchOper, voice_id);
            map.put("records", count);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }else {
            Map<String, Object> map = new HashMap<>();
            //jqgrid需要返回的数据 四个
            //查询到的总数据 rows 当前页 page 总条数 records 总页数 total
            Chapter chapter = new Chapter(null, null, null, null, null, voice_id, null);
            List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, new RowBounds((page - 1) * rows, rows));
            map.put("rows", chapters);
            map.put("page", page);
            Integer count = chapterDao.selectCount(chapter);
            map.put("records", count);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }
    }

    @Override
    public Map add(Chapter chapter) {
        HashMap map=new HashMap();
        chapterDao.insert(chapter);
        map.put("chapterId",chapter.getId());
        map.put("status",200);
        return map;
    }

    @Override
    public Map delete(String id) {
        Chapter chapter=new Chapter();
        chapter.setId(id);
        Chapter selectOne = chapterDao.selectOne(chapter);
        String[] strings = selectOne.getFile().split("/");
        String fileName=strings[strings.length-1];
        HashMap map=new HashMap();
        map.put("fileName",fileName);
        map.put("status",200);
        chapterDao.delete(chapter);
        return map;
    }

    @Override
    public Map update(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
        return null;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Chapter> searchFive() {//随机查询五个
        return chapterDao.searchFive();
    }
}
