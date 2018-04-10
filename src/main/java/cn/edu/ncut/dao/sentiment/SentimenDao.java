package cn.edu.ncut.dao.sentiment;

import cn.edu.ncut.dao.base.BaseDao;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/8 21:10
 */
@Repository
public class SentimenDao extends BaseDao {

    public List<String> vocabStrList;
    public List<String> bayesResStrList;
    public List<String> noRatingCommentStrList;

    @PostConstruct
    private void init() throws IOException {
        vocabStrList = JSON.parseObject(redisTemplate.opsForValue().get("sentiment:vocab"), List.class);
        bayesResStrList = JSON.parseObject(redisTemplate.opsForValue().get("sentiment:bayes:rating"), List.class);
        noRatingCommentStrList = JSON.parseObject(redisTemplate.opsForValue().get("sentiment:norating:comment"), List.class);
    }

    public List<String> getVocabStrList() {
        return vocabStrList;
    }

    public List<String> getBayesResStrList() {
        return bayesResStrList;
    }

    public List<String> getNoRatingCommentStrList() {
        return noRatingCommentStrList;
    }
}
