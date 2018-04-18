package cn.edu.ncut.service.similar;

import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dto.hybrid.WordCloud;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import cn.edu.ncut.dto.spider.UserBookStatus;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SeawayLee
 * @create 2018/4/15 16:44
 */
@Service
public class SimilarService {
    public static final String COLLECT_TABLE = "tb_userbookcollect";
    public static final String DO_TABLE = "tb_userbookdo";
    public static final String WISH_TABLE = "tb_userbookwish";
    public static final String[] USET_BOOK_STATUS_TYPE = new String[]{COLLECT_TABLE, DO_TABLE, WISH_TABLE};
    public static final String PREPARE_USER_KEY = "PREPARE_USER";
    public static final String USER_BOOK_STATUS_KEY = "USER_BOOK_STATUS_";
    public static final String USER_MAX_SIMILAR_KEY = "USER_MAX_SIMILAR_";
    public static final float k1Collect = 0.8F;
    public static final float k2Wish = 0.1F;
    public static final float k3Do = 0.1F;
    public static final String DO_KEY = "tb_userbookdo";
    public static final String WISH_KEY = "tb_userbookwish";
    public static final String COLLECT_KEY = "tb_userbookcollect";
    public static final String USER_MAX_VALUE_SPLITER = " - ";
    public static final Random RANDOM = new Random(System.currentTimeMillis());
    public static final Integer MAX_WORD = 1000;
    public static final Map<String, String> TYPE_SIM_MAP = new HashMap<String, String>() {{
        put(COLLECT_KEY, "collectSim");
        put(WISH_KEY, "wishSim");
        put(DO_KEY, "doSim");
    }};

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserBookStatusMapper userBookStatusMapper;
    @Autowired
    private SimilarService similarService;
    @Autowired
    private SimpleBookInfoMapper simpleBookInfoMapper;

    public Map<String, Object> calcSimilar(String userA, String userB) {
        Map<String, Set<String>> userDataMapA = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.USER_BOOK_STATUS_KEY + userA), Map.class);
        Map<String, Set<String>> userDataMapB = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.USER_BOOK_STATUS_KEY + userB), Map.class);
        Map<String, Object> similarResMap = new HashMap<>();
        similarResMap.put("userA", userA);
        similarResMap.put("userB", userB);
        similarResMap.put("userDataMapA", userDataMapA);
        similarResMap.put("userDataMapB", userDataMapB);
        similarResMap.put("collectSim", k1Collect * calcSimilar(COLLECT_KEY, userDataMapA, userDataMapB));
        similarResMap.put("wishSim", k2Wish * calcSimilar(DO_KEY, userDataMapA, userDataMapB));
        similarResMap.put("doSim", k3Do * calcSimilar(WISH_KEY, userDataMapA, userDataMapB));
        similarResMap.put("similarRes", (Float) similarResMap.get("collectSim") + (Float) similarResMap.get("wishSim") + (Float) similarResMap.get("doSim"));
        return similarResMap;
    }

    public float calcSimilar(String prefereType, Map<String, Set<String>> userA, Map<String, Set<String>> userB) {
        Set<String> unionRes = new HashSet<>();
        final Set<String> collectListA = Sets.newHashSet(userA.get(prefereType));
        final Set<String> collectListB = Sets.newHashSet(userB.get(prefereType));
        unionRes.addAll(collectListA);
        unionRes.addAll(collectListB);
        int unionSize = unionRes.size();
        collectListA.retainAll(collectListB);
        int mergeSize = collectListA.size();
        return (mergeSize * 1.0F) / (unionSize * 1.0F);
    }

    /**
     * 获取与User最相似的用户
     */
    public String getMaxSimilarUser(String user) {
        String maxSimStr = redisTemplate.opsForValue().get(SimilarService.USER_MAX_SIMILAR_KEY + user);
        if (StringUtils.isEmpty(maxSimStr)) {
            return "";
        }
        String[] maxSimilarData = maxSimStr.split(SimilarService.USER_MAX_VALUE_SPLITER);
        return maxSimilarData[0];
    }

    /**
     * 获取用户的偏好-书籍映射表
     */
    public Map<String, List<UserBookStatus>> getUserStatusTypeMap(String user) {
        Map<String, List<UserBookStatus>> userBookStatusMap = new HashMap<>();
        for (String userBookType : USET_BOOK_STATUS_TYPE) {
            userBookStatusMap.put(userBookType, userBookStatusMapper.selectByTypeAndUser(userBookType, user));
        }
        return userBookStatusMap;
    }

    public Map<String, List<WordCloud>> transWordCloudData(Map<String, List<UserBookStatus>> userStatusMap) {
        Map<String, List<WordCloud>> wordCloudMap = new HashMap<>();
        List<WordCloud> collectList = new ArrayList<>();
        List<WordCloud> doList = new ArrayList<>();
        List<WordCloud> wishList = new ArrayList<>();
        Map<String, List<WordCloud>> keyMap = new HashMap<String, List<WordCloud>>() {
            {
                put(COLLECT_TABLE, collectList);
                put(DO_TABLE, doList);
                put(WISH_TABLE, wishList);
            }
        };
        for (Map.Entry<String, List<UserBookStatus>> entry : userStatusMap.entrySet()) {

            entry.getValue().forEach(statusObj -> {
                try {
                    SimpleBookInfo bookInfo = simpleBookInfoMapper.getBookByNo(statusObj.getBookno());
                    WordCloud wordCloud = null;
                    if (bookInfo == null) {
                        wordCloud = new WordCloud(statusObj.getBookno(), 1);
                    } else {
                        wordCloud = new WordCloud(simpleBookInfoMapper.getBookByNo(statusObj.getBookno()).getTitle(), 2 + RANDOM.nextInt(2));
                    }
                    keyMap.get(entry.getKey()).add(wordCloud);
                } catch (Exception e) {
                    System.out.println("数据转换异常:" + e.getMessage());
                }
            });
        }
        wordCloudMap.put("collect", collectList.subList(0, Math.min(collectList.size(), MAX_WORD)));
        wordCloudMap.put("do", doList.subList(0, Math.min(doList.size(), MAX_WORD)));
        wordCloudMap.put("wish", wishList.subList(0, Math.min(wishList.size(), MAX_WORD)));
        return wordCloudMap;
    }


}
