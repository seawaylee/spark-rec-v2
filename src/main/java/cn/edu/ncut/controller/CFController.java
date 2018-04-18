package cn.edu.ncut.controller;

import cn.edu.ncut.dao.cf.UserRatingDao;
import cn.edu.ncut.dao.sentiment.SentimenDao;
import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dto.cf.PredictDTO;
import cn.edu.ncut.dto.cf.UserRatingDTO;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import cn.edu.ncut.dto.spider.extend.BarInfoData;
import cn.edu.ncut.service.cf.CfService;
import cn.edu.ncut.service.similar.SimilarService;
import cn.edu.ncut.util.HttpRequestUtil;
import cn.edu.ncut.util.PageUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import com.google.common.reflect.TypeToken;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:01
 */
@Controller
@RequestMapping(value = "/cf")
public class CFController {

    @Autowired
    private UserRatingDao userRatingDao;
    @Autowired
    private BookCommentMapper bookCommentMapper;
    @Autowired
    private SimpleBookInfoMapper simpleBookInfoMapper;
    @Autowired
    private SentimenDao sentimenDao;
    @Autowired
    private CfService cfService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/showDataStatus")
    public String showDataStatus(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        return "cf/dataStatus";
    }

    @RequestMapping(value = "/buildModel")
    public String buildModel(ModelMap modelMap) {
        return "cf/dataModel";
    }

    @RequestMapping(value = "/showRecRes")
    public String getRecRes(ModelMap modelMap) {
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        return "cf/recRes";
    }

    @RequestMapping(value = "/showCfData")
    public String showCfData(ModelMap modelMap) {
        return "cf/cfData";
    }

    @RequestMapping(value = "/getUserRating")
    @ResponseBody
    public List<UserRatingDTO> getUserRating() {
        List<String> userRatingStrList = userRatingDao.getUserRatingStrList();
        List<UserRatingDTO> rationgDTOList = new ArrayList<>();
        int max = 10000;
        String[] ratingArray;
        Integer userId;
        Integer bookId;
        Integer rating;
        for (String ratingStr : userRatingStrList) {
            if (max-- <= 0) {
                break;
            }
            ratingArray = ratingStr.split(",");
            userId = Math.abs(ratingArray[0].hashCode()) % 10000;
            bookId = Math.abs(ratingArray[1].hashCode()) % 10000;
            rating = Integer.valueOf(ratingArray[2]);
            rationgDTOList.add(new UserRatingDTO(userId, bookId, rating));
        }
        return rationgDTOList;
    }

    @RequestMapping(value = "/getRelation")
    @ResponseBody
    public Object getRelation() {
        List<SimpleBookInfo> bookInfos = bookCommentMapper.getBookRatingTimes(20).stream()
                .map(book -> simpleBookInfoMapper.getBookByNo(book.getBookno()))
                .collect(Collectors.toList());
        Map<Object, List<Object>> nodeMap = new HashMap<>();
        Map<String, Object> relationMap = new HashMap<>();
        List<Object> categories = cfService.buildCategories(bookInfos);
        List<Map> nodes = cfService.buildNodes(bookInfos, nodeMap);
        List<Object> links = cfService.buildLinks(nodes, nodeMap);
        relationMap.put("type", "force");
        relationMap.put("categories", categories);
        relationMap.put("nodes", nodes);
        relationMap.put("links", links);
        relationMap.put("bookNames", bookInfos.stream().map(SimpleBookInfo::getTitle).collect(Collectors.toList()));
        return relationMap;
    }

    @RequestMapping(value = "/getCfStatistic")
    @ResponseBody
    public Object getCfStatistic(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        List<BookComment> result = bookCommentMapper.getRatingStatistic();
        List<String> bayesResStrList = sentimenDao.getBayesResStrList();
        String[] xAxis = new String[result.size()];
        String[] yAxis = new String[result.size()];
        for (int i = 1; i < result.size(); i++) {
            xAxis[i] = result.get(i).getRating().toString();
            yAxis[i] = result.get(i).getItemResultAmount().toString();
        }
        Integer rating;
        for (int i = 0; i < bayesResStrList.size(); i++) {
            rating = Integer.valueOf(bayesResStrList.get(i).split(",")[2]);
            yAxis[rating] = Integer.valueOf(yAxis[rating]) + 1 + "";
        }

        BarInfoData commentData = new BarInfoData(yAxis, xAxis);
        commentData.setxAxis(xAxis);
        commentData.setyAxis(yAxis);
        return commentData;
    }

    @RequestMapping(value = "/getRatingData")
    public Object getRatingData(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        List<String> userRatingStrList = Lists.newArrayList(userRatingDao.getUserRatingStrList());
        userRatingStrList.addAll(sentimenDao.getBayesResStrList());
        Integer totalRecord = userRatingStrList.size();
        userRatingStrList = userRatingStrList.subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        List<JSONObject> userRatingObjList = new ArrayList<>();
        JSONObject ratingObj;
        for (String ratingStr : userRatingStrList) {
            String[] ratingStrArr = ratingStr.split(",");
            ratingObj = new JSONObject();
            ratingObj.put("userId", ratingStrArr[0]);
            ratingObj.put("bookId", ratingStrArr[1]);
            ratingObj.put("rating", ratingStrArr[2]);
            userRatingObjList.add(ratingObj);
        }
        modelMap.put("userRatingObjList", userRatingObjList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", totalRecord);
        modelMap.put("totalPage", PageUtils.getTotalPage(totalRecord));
        return "cf/userRating";
    }

    @RequestMapping("/getRecommend")
    public Object getRecommend(String userno, ModelMap modelMap) {
        List<PredictDTO> predictList = cfService.getRecRes(userno);
        Set<String> userSet = JSON.parseObject(redisTemplate.opsForValue().get(SimilarService.PREPARE_USER_KEY), Set.class);
        modelMap.put("userSet", userSet);
        modelMap.put("user", userno);
        modelMap.put("predictList", predictList);
        return "cf/recRes";
    }
}
