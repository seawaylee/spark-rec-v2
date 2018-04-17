package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dao.spider.UserInfoMapper;
import cn.edu.ncut.dto.similar.MaxSimilarDTO;
import cn.edu.ncut.dto.spider.UserBookStatus;
import cn.edu.ncut.service.similar.SimilarService;
import cn.edu.ncut.util.PageUtils;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:02
 */
@Controller
@RequestMapping(value = "/similar")
public class SimilarityController {
    @Autowired
    private UserBookStatusMapper userBookStatusMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    SimilarService similarService;
    @Autowired
    UserInfoMapper userInfoMapper;


    @RequestMapping(value = "/showData")
    public String showSysStatus(HttpServletRequest request) {
        return "similar/data";
    }

    @RequestMapping(value = "/showCalcSimilar")
    public String showCalcSimilar(HttpServletRequest request, ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "similar/calcSimilar";
    }


    @RequestMapping(value = "/getData")
    public String getData(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "tb_userbookcollect") String dataType, ModelMap modelMap) {
        System.out.println("dataType:" + dataType);
        List<UserBookStatus> ubStatusList = userBookStatusMapper.selectByType(dataType, PageUtils.getFrom(pageNum), PageUtils.PAGE_SIZE);
        Integer totalRecord = userBookStatusMapper.selectStatusCount(dataType);
        modelMap.put("ubStatusList", ubStatusList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", totalRecord);
        modelMap.put("dataType", dataType);
        modelMap.put("totalPage", PageUtils.getTotalPage(totalRecord));
        return "similar/data";
    }

    @RequestMapping(value = "/calcSimilar")
    public String calcSimilar(String userA, String userB, ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        if (userSet.contains(userA) && userSet.contains(userB)) {
            Map<String, Object> similarResMap = similarService.calcSimilar(userA, userB);
            modelMap.putAll(similarResMap);
            modelMap.put("userSet", userSet);
        }
        return "similar/calcSimilar";
    }

    @RequestMapping(value = "/getSimilarResData")
    public String getData(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        List<String> userInfos = userInfoMapper.selectAllUserNo();
        List<String> userSet = Lists.newArrayList(JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class));
        List<String> pageUsers = userSet.subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        List<MaxSimilarDTO> similarList = new ArrayList<>();
        for (String user : pageUsers) {
            String[] maxSimilarData = redisTemplate.opsForValue().get(SimilarService.USER_MAX_SIMILAR_KEY + user).split(SimilarService.USER_MAX_VALUE_SPLITER);
            similarList.add(new MaxSimilarDTO(user, maxSimilarData[0], maxSimilarData[1]));
        }
        modelMap.put("similarList", similarList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", userInfos.size());
        modelMap.put("totalPage", PageUtils.getTotalPage(userInfos.size()));
        return "similar/similarResData";
    }

}
