package com.xc.service;

import com.xc.annotation.LogAnnotation;
import com.xc.dao.BannerDao;
import com.xc.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2019/11/27.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    //@LogAnnotation(value = "查询轮播图信息")
    public Map<String, Object> findAll(Boolean _search,String searchField,String searchString,String searchOper,Integer rows,Integer page) {
        //jqgrid分页查询所需返回数据 total 总页数 records 总条数 rows 查询到的集合 page 当前页
        Map<String,Object> map=new HashMap<>();
        searchField="`"+searchField+"`";
        if(_search){
            List<Banner> banners = bannerDao.search(searchField,searchString,searchOper,(page-1)*rows,rows);
            map.put("rows", banners);
            Integer count = bannerDao.count(searchField,searchString,searchOper);
            map.put("records", count);
            map.put("page", page);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }else {
            List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds((page - 1) * rows, rows));
            map.put("rows", banners);
            Integer count = bannerDao.selectCount(new Banner());
            map.put("records", count);
            map.put("page", page);
            Integer total = count % rows == 0 ? count / rows : count / rows + 1;
            map.put("total", total);
            return map;
        }
    }

    @Override
    public Map add(Banner banner) {
        HashMap hashMap=new HashMap();
        bannerDao.insert(banner);
        hashMap.put("bannerId", banner.getId());
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map update(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
        return null;
    }

    @Override
    public Map delete(List<String> ids) {
        bannerDao.deleteByIdList(ids);
        return null;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> searchFive() {
        return bannerDao.searchFive();
    }
}
