package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dto.cf.PredictDTO;
import cn.edu.ncut.dto.hybrid.WordCloud;
import cn.edu.ncut.dto.spider.UserBookStatus;
import cn.edu.ncut.service.cf.CfService;
import cn.edu.ncut.service.similar.SimilarService;
import cn.edu.ncut.util.PageUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    @Autowired
    private CfService cfService;


    @RequestMapping(value = "/showSimilarDataStatus")
    public String showSimilarDataStatus(ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "hybrid/similarDataStatus";
    }

    @RequestMapping(value = "/showCfRecDataStatus")
    public String showCfRecDataStatus(ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "hybrid/cfRecDataStatus";
    }

    @RequestMapping(value = "/showHybridRecResult")
    public String showHybridRecResult(ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "hybrid/hybridRecResult";
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

    @RequestMapping(value = "/cfRecDataStatus")
    public String cfRecDataStatus(@RequestParam(name = "userno", defaultValue = "Thomas.Xie") String userno, ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageNum) {
        System.out.println("user=" + userno);
        Map<String, Object> resultMap = new HashMap<>();
        // 获取CF推荐结果
        List<PredictDTO> predictList = cfService.getRecRes(userno);
        Integer totalRecord = predictList.size();
        predictList = predictList.subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        modelMap.put("user", userno);
        modelMap.put("predictList", predictList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", totalRecord);
        modelMap.put("totalPage", PageUtils.getTotalPage(totalRecord));
        return "hybrid/cfRecDataStatus";
    }


    @RequestMapping(value = "/getHybridRecResult")
    public String getHybridRecResult(@RequestParam(name = "userno", defaultValue = "Thomas.Xie") String userno, ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageNum) {
        System.out.println("user=" + userno);
        Map<String, Object> resultMap = new HashMap<>();
        // 获取CF推荐结果
        List<PredictDTO> predictList = cfService.getRecRes(userno);
        Integer totalRecord = predictList.size();
        // 获取最相似用户特征
        String maxSimilarUser = similarService.getMaxSimilarUser(userno.trim());
        Map<String, List<UserBookStatus>> maxSimUserStatusMap = similarService.getUserStatusTypeMap(maxSimilarUser);
        Map<String, List<UserBookStatus>> currUserStatusMap = similarService.getUserStatusTypeMap(userno);
        Map<String, Object> similarMap = similarService.calcSimilar(userno, maxSimilarUser);
        int kindWeight = 1;
        float kindSimilar = 0.0F;
        for (PredictDTO predictDTO : predictList) {
            for (Map.Entry<String, List<UserBookStatus>> entry : maxSimUserStatusMap.entrySet()) {
                String statusType = entry.getKey();
                List<UserBookStatus> simTypedBookStatusList = entry.getValue();
                for (UserBookStatus simBookStatus : simTypedBookStatusList) {
                    if (simBookStatus.getBookno().equals(predictDTO.getBookId().toString())) {
                        if (currUserStatusMap.get(statusType).stream().anyMatch(userBookStatus -> simBookStatus.getBookno().equals(userBookStatus.getBookno()))) {
                            kindWeight = 2;
                        }
                        kindSimilar = Float.valueOf(similarMap.get(SimilarService.TYPE_SIM_MAP.get(statusType)).toString());
                        System.out.println("kindWeight=" + kindWeight + ", kindSimilar=" + kindSimilar);
                        predictDTO.setScore(predictDTO.getScore() + predictDTO.getScore() * kindWeight * kindSimilar);
                    }
                }
            }

        }
        Collections.sort(predictList, Comparator.comparing(PredictDTO::getScore));
        Collections.reverse(predictList);
        predictList = predictList.subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        modelMap.put("user", userno);
        modelMap.put("predictList", predictList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", totalRecord);
        modelMap.put("totalPage", PageUtils.getTotalPage(totalRecord));

        return "hybrid/hybridRecResult";
    }

}
