package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dto.hybrid.WordCloud;
import cn.edu.ncut.dto.spider.UserBookStatus;
import cn.edu.ncut.service.similar.SimilarService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:02
 */
@Controller
@RequestMapping(value = "/hybrid")
public class HybridRecController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserBookStatusMapper userBookStatusMapper;
    @Autowired
    private SimilarService similarService;


    @RequestMapping(value = "/showSimilarDataStatus")
    public String showSimilarDataStatus(ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "hybrid/similarDataStatus";
    }

    @RequestMapping(value = "/similarDataStatus")
    @ResponseBody
    public Object similarDataStatus(@RequestParam(defaultValue = "Thomas.Xie") String user) {
        Map<String, Object> resultMap = new HashMap<>();
        // 获取最相似用户的偏好数据
        String maxSimilarUser = similarService.getMaxSimilarUser(user.trim());
        Map<String, List<UserBookStatus>> maxSimUserStatusMap = similarService.getUserStatusTypeMap(maxSimilarUser);
        Map<String, List<WordCloud>> wordCloudMap = similarService.transWordCloudData(maxSimUserStatusMap);
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        resultMap.put("userSet", userSet);
        resultMap.put("maxSimUser", maxSimilarUser);
        resultMap.put("simData", wordCloudMap);
        resultMap.put("user", user);
        System.out.println(resultMap);
        return resultMap;
    }


}
