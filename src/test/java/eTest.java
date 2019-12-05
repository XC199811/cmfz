import com.xc.CmfzApplication;
import com.xc.dao.AdminDao;
import com.xc.dao.BannerDao;
import com.xc.dao.ChapterDao;
import com.xc.dao.ShangShiDao;
import com.xc.entity.Admin;
import com.xc.entity.Banner;
import com.xc.entity.Chapter;
import com.xc.entity.ShangShi;
import com.xc.service.ChapterService;
import com.xc.service.ShangShiService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2019/11/26.
 */
@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class eTest {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private ShangShiDao shangShiDao;

    @Test
    public void testAdmin() {
        //List<Admin> admins = adminDao.selectAll();
        // admins.forEach(admin -> System.out.println(admin));
        Admin admin = new Admin(null, "admin", "admin");
        Admin admin1 = adminDao.selectOne(admin);
        System.out.println(admin1);
    }

    @Test
    public void testBanner() {
        //List<Banner> banners = bannerDao.selectAll();
        //banners.forEach(banner -> System.out.println(banner));
        Banner banner = new Banner("1", "蒹葭苍苍", null, "1.jpg", null, "1", new Date());
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Test
    public void testChapter() {
        Chapter chapter = new Chapter(null, null, null, null, null, "3", null);
        List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, new RowBounds(0, 2));
        chapters.forEach(chapter1 -> System.out.println(chapter1));
    }

    @Test
    public void testShangShi() {
        List<ShangShi> shangShis = shangShiDao.selectAll();
        shangShis.forEach(shangShi -> System.out.println(shangShi));
    }
}