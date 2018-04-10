package cn.edu.ncut.dao.cf;

import cn.edu.ncut.dao.base.BaseDao;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/9 18:35
 */
@Repository
public class UserRatingDao extends BaseDao {

    public List<String> userRatingStrList;

    @PostConstruct
    private void init() {
        userRatingStrList = JSON.parseObject(redisTemplate.opsForValue().get("cf:hasrating"), List.class);
    }

    public List<String> getUserRatingStrList() {
        return userRatingStrList;
    }

}
