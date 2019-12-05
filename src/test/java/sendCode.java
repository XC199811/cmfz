import com.xc.CmfzApplication;
import com.xc.util.SendCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2019/12/4.
 */
@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class sendCode {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testSendCode(){
        SendCode.sendCode("123","xczuishuai");
    }
    @Test
    public void testRedis(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("15890328229","xiaohong");
        redisTemplate.expire("15890328229",60, TimeUnit.SECONDS);
    }
    @Test
    public void redis(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForList().leftPush("1","2");
        List<String> range = redisTemplate.opsForList().range("1",0,-1);
        System.out.println(range);
    }
    @Test
    public void redisAdd(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForList().leftPush("s1","1");
    }
}
