package cn.edu.ncut.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author SeawayLee
 * @create 2018/4/2 21:15
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public long getDbSize() {
        return redisTemplate.execute(RedisServerCommands::dbSize);
    }
}
