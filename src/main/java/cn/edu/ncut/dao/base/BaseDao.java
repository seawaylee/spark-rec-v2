package cn.edu.ncut.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author SeawayLee
 * @create 2018/4/9 18:36
 */
@Repository
public class BaseDao {
    @Autowired
    public StringRedisTemplate redisTemplate;

    public static String ROOT_DIR = "/Users/lixiwei-mac/Documents/DataSet/recommend/";
    public static String BOOK_NAME = ROOT_DIR + "BookName.txt";
    public static String PN_VOCAB = ROOT_DIR + "PNVocab.txt";
    public static String NB_RESULT = ROOT_DIR + "NBSResult.txt";
    public static String NO_RATING_COMMENT = ROOT_DIR + "NoRatingUserComment.txt";
    public static String USER_HAS_RATING = ROOT_DIR + "UserRating.txt";
}
