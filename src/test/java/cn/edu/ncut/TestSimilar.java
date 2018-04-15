package cn.edu.ncut;

import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dao.spider.UserInfoMapper;
import cn.edu.ncut.dto.spider.UserBookStatus;
import cn.edu.ncut.dto.spider.UserInfo;
import cn.edu.ncut.service.similar.SimilarService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SeawayLee
 * @create 2018/4/15 13:42
 */
public class TestSimilar extends TestBase {

    @Autowired
    UserBookStatusMapper bookStatusMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    SimilarService similarService;
    @Autowired
    UserInfoMapper userInfoMapper;


    @Test
    public void prepareUser() {
        List<UserBookStatus> userBookCollect = bookStatusMapper.selectByType("tb_userbookcollect", 0, 10000);
        Set<String> userList = new HashSet<>();
        for (UserBookStatus userBookStatus : userBookCollect) {
            userList.add(userBookStatus.getUserno());
        }
        redisTemplate.opsForValue().set(SimilarService.PREPARE_USER_KEY, JSON.toJSONString(userList));
        System.out.println(userList.size());

    }

    @Test
    public void prepareData() {
        String[] dataTypes = new String[]{"tb_userbookdo", "tb_userbookwish", "tb_userbookcollect"};
        List<String> userList = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), List.class);
        Map<String, Set<String>> userBookStatusMap = new HashMap<>();
        for (int i = 0; i < userList.size(); i++) {
            String user = userList.get(i);
            userBookStatusMap.clear();
            for (String dataType : dataTypes) {
                Set<String> bookIds = bookStatusMapper.selectByUserNo(dataType, user);
                userBookStatusMap.put(dataType, bookIds);
            }
            redisTemplate.opsForValue().set(SimilarService.USER_BOOK_STATUS_KEY + user, JSON.toJSONString(userBookStatusMap));
            System.out.println(i + "/" + userList.size());
        }
    }

    @Test
    public void calcMostSimilar() {
        List<String> userList = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), List.class);
        Map<String, Float> userMaxSimValueMap = new HashMap<>();
        Map<String, String> userMaxSimUserMap = new HashMap<>();
        Map<String, Set<String>> userDataMapA;
        Map<String, Set<String>> userDataMapB;
        float collectSim;
        float wishSim;
        float doSim;
        float similarRes;
        for (String userA : userList) {
            for (String userB : userList) {
                if (userA.equals(userB)) {
                    continue;
                }
                userDataMapA = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.USER_BOOK_STATUS_KEY + userA), Map.class);
                userDataMapB = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.USER_BOOK_STATUS_KEY + userB), Map.class);
                collectSim = SimilarService.k1Collect * similarService.calcSimilar(SimilarService.COLLECT_KEY, userDataMapA, userDataMapB);
                wishSim = SimilarService.k2Wish * similarService.calcSimilar(SimilarService.DO_KEY, userDataMapA, userDataMapB);
                doSim = SimilarService.k3Do * similarService.calcSimilar(SimilarService.WISH_KEY, userDataMapA, userDataMapB);
                similarRes = collectSim + wishSim + doSim;
                if (!userMaxSimValueMap.containsKey(userA)) {
                    userMaxSimValueMap.put(userA, similarRes);
                    userMaxSimUserMap.put(userA, userB);
                } else {
                    if (userMaxSimValueMap.get(userA) < similarRes) {
                        userMaxSimValueMap.put(userA, similarRes);
                        userMaxSimUserMap.put(userA, userB);
                    }
                }
            }
            System.out.println(userA + SimilarService.USER_MAX_VALUE_SPLITER + userMaxSimUserMap.get(userA) + SimilarService.USER_MAX_VALUE_SPLITER + userMaxSimValueMap.get(userA));
            redisTemplate.opsForValue().set(SimilarService.USER_MAX_SIMILAR_KEY + userA, userMaxSimUserMap.get(userA) + SimilarService.USER_MAX_VALUE_SPLITER + userMaxSimValueMap.get(userA));
        }
    }

    @Test
    public void fillOthers() {
        List<String> existUserList = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), List.class);
        List<String> userInfos = userInfoMapper.selectAllUserNo();
        userInfos.removeAll(existUserList);
        Random random = new Random();
        for (int i = 0; i < userInfos.size(); i++) {
            float value = random.nextFloat() * 0.2F;
            redisTemplate.opsForValue().set(SimilarService.USER_MAX_SIMILAR_KEY + SimilarService.USER_MAX_SIMILAR_KEY + userInfos.get(i), userInfos.get(random.nextInt(userInfos.size())) + SimilarService.USER_MAX_VALUE_SPLITER + value);
            System.out.println(i + "/" + userInfos.size());
        }
    }
}
