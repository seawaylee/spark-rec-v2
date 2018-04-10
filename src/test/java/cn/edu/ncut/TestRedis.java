package cn.edu.ncut;

import cn.edu.ncut.dao.cf.UserRatingDao;
import cn.edu.ncut.dao.sentiment.SentimenDao;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/2 20:51
 */
public class TestRedis extends TestBase {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    private SentimenDao sentimenDao;
    @Autowired
    private UserRatingDao userRatingDao;
    @Test
    public void test() {
        System.out.println(redisTemplate);
    }

    @Test
    public void testOp() {
        //redisTemplate.opsForValue().set("shit", "hahaha");
        //System.out.println(redisTemplate.opsForValue().get("shit"));
        System.out.println(redisTemplate.execute(RedisServerCommands::dbSize));
    }

    @Test
    public void pushToRedis() {
        List<String> bayesResStrList = sentimenDao.getBayesResStrList();
        List<String> noRatingCommentStrList = sentimenDao.getNoRatingCommentStrList();
        List<String> vocabStrList = sentimenDao.getVocabStrList();
        List<String> userRatingStrList = userRatingDao.getUserRatingStrList();
        redisTemplate.opsForValue().set("sentiment:bayes:rating", JSON.toJSONString(bayesResStrList));
        redisTemplate.opsForValue().set("sentiment:norating:comment", JSON.toJSONString(noRatingCommentStrList));
        redisTemplate.opsForValue().set("sentiment:vocab", JSON.toJSONString(vocabStrList));
        redisTemplate.opsForValue().set("cf:hasrating", JSON.toJSONString(userRatingStrList));
    }

    @Test
    public void readReadis() {
        List<String> list = JSON.parseObject(redisTemplate.opsForValue().get("sentiment:norating:comment"), List.class);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
