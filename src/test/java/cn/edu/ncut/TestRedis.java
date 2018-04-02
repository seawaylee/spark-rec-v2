package cn.edu.ncut;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author SeawayLee
 * @create 2018/4/2 20:51
 */
public class TestRedis extends TestBase {
    @Autowired
    StringRedisTemplate redisTemplate;

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
}
