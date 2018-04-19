package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dao.spider.UserInfoMapper;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import cn.edu.ncut.dto.spider.UserInfo;
import cn.edu.ncut.dto.spider.extend.BarInfoData;
import cn.edu.ncut.dto.spider.extend.NameValue;
import cn.edu.ncut.util.RedisUtil;
import cn.edu.ncut.util.SystemStatusUtil;
import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:01
 */
@Controller
@RequestMapping(value = "/spider")
public class SpiderController {

    @Autowired
    private SimpleBookInfoMapper simpleBookInfoMapper;
    @Autowired
    private BookCommentMapper bookCommentMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public static final String HOST = "http://book.douban.com";
    public static final Set<String> PROVINCE_SET = Sets.newHashSet(Arrays.asList("山东", "福建", "台湾", "河北", "河南", "重庆", "湖南", "湖北", "江西", "海南", "天津", "陕西", "贵州", "新疆", "澳门", "江苏", "安徽", "西藏", "上海", "吉林", "甘肃", "山西", "宁夏", "香港", "四川", "浙江", "广西", "云南", "辽宁", "龙江", "广东", "青海", "蒙古", "北京"));


    @RequestMapping(value = "/monitor/showSysStatus")
    public String showSysStatus(HttpServletRequest request) {
        return "spider/monitor";
    }

    @RequestMapping(value = "/monitor/showDataAnalysis")
    public String showDataAnalysis(HttpServletRequest request) {
        return "spider/dataAnalysis";
    }

    /**
     * 获取爬虫系统状态
     */
    @RequestMapping(value = "/monitor/getSysStatus")
    @ResponseBody
    public Map<String, Object> getSysStatus(HttpServletRequest request) {
        long delay = SystemStatusUtil.getHostNetDelay(HOST);
        Map<String, Object> resStatus = SystemStatusUtil.getJvmStatus();
        resStatus.put("delay", delay);
        return resStatus;
    }

    /**
     * 查询数据量
     */
    @RequestMapping(value = "/monitor/getAmount")
    @ResponseBody
    public Map<String, Integer> getAmount(HttpServletRequest request) {
        Map<String, Integer> resMap = new HashMap<>();
        SimpleBookInfo bookInfo = new SimpleBookInfo();
        resMap.put("book", simpleBookInfoMapper.selectCount(bookInfo));
        //resMap.put("book", (int) (Math.random() * 100000));
        BookComment comment = new BookComment();
        resMap.put("comment", bookCommentMapper.selectCount(comment));
        //resMap.put("comment", (int) (Math.random() * 10000000));
        return resMap;
    }

    /**
     * 查询评分分布
     */
    @RequestMapping("/analysis/getRating")
    @ResponseBody
    public BarInfoData getRating() {
        List<BookComment> result = bookCommentMapper.getRatingStatistic();
        System.out.println("评分值分布" + JSON.toJSONString(result));
        String[] xAxis = new String[result.size()];
        String[] yAxis = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            xAxis[i] = result.get(i).getRating().toString();
            yAxis[i] = result.get(i).getItemResultAmount().toString();
        }
        BarInfoData commentData = new BarInfoData(yAxis, xAxis);
        commentData.setxAxis(xAxis);
        commentData.setyAxis(yAxis);
        return commentData;
    }

    /**
     * 评论数最多的用户
     *
     * @author seawayLee
     * 2016年3月23日
     */
    @RequestMapping("/analysis/getUserCommentCountSort")
    @ResponseBody
    public Object getUserCommentCountSort() {
        Map<String, Object> resMap = new HashMap<>();
        List<NameValue> commentCountStatisticResult = bookCommentMapper.getCommentNumByUserNoStatistic(10);
        resMap.put("commentCountStatisticResultInner", commentCountStatisticResult.subList(0, 3));
        resMap.put("commentCountStatisticResultOuter", commentCountStatisticResult.subList(3, commentCountStatisticResult.size()));
        resMap.put("users", commentCountStatisticResult.stream().map(obj -> obj.getName()).collect(Collectors.toList()));
        System.out.println("用户评论数分布" + JSON.toJSONString(resMap));
        return resMap;
    }

    /**
     * 获取评论时间分布
     */
    @RequestMapping("/analysis/getCommentTimeStatistic")
    @ResponseBody
    public BarInfoData getCommentTimeStatistic() {
        List<BookComment> result = bookCommentMapper.getCommentTimeStatistic();
        String[] xAxis = new String[result.size()];
        String[] yAxis = new String[result.size()];
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        for (int i = 0; i < result.size(); i++) {
            xAxis[i] = df.format(result.get(i).getTime()).substring(0, 4);
            yAxis[i] = result.get(i).getItemResultAmount().toString();
        }
        BarInfoData timeData = new BarInfoData(yAxis, xAxis);
        System.out.println("书评时间分布" + JSON.toJSONString(timeData));
        return timeData;
    }

    /**
     * 获取用户地域分布
     */
    @RequestMapping("/analysis/getLocationStatus")
    @ResponseBody
    public List<NameValue> getLocationStatus() {
        List<NameValue> result = userInfoMapper.selectLocationStatistic();
        for (NameValue kv : result) {
            if (kv == null || StringUtils.isEmpty(kv.getName())) {
                continue;
            }
            for (String prov : PROVINCE_SET) {
                if (kv.getName().contains(prov)) {
                    kv.setName(prov);
                }
            }
        }
        System.out.println("地域分布" + JSON.toJSONString(result));
        return result;
    }
}
