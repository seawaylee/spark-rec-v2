package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import cn.edu.ncut.dto.spider.extend.BarInfoData;
import cn.edu.ncut.util.RedisUtil;
import cn.edu.ncut.util.SystemStatusUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private RedisUtil redisUtil;

    public static final String HOST = "http://book.douban.com";


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
        //resMap.put("book", simpleBookInfoMapper.selectStatusCount(bookInfo));
        resMap.put("book", (int) (Math.random() * 100000));
        BookComment comment = new BookComment();
        //resMap.put("comment", bookCommentMapper.selectStatusCount(comment));
        resMap.put("comment", (int) (Math.random() * 10000000));
        return resMap;
    }

    /**
     * 查询评分分布
     */
    @RequestMapping("/analysis/getRating")
    @ResponseBody
    public BarInfoData getRating()
    {
        List<BookComment> result = bookCommentMapper.getRatingStatistic();
        System.out.println("评分值分布"+JSON.toJSONString(result));
        String[] xAxis = new String[result.size()];
        String[] yAxis = new String[result.size()];
        for(int i=0;i<result.size();i++)
        {
            xAxis[i] = result.get(i).getRating().toString();
            yAxis[i]  = result.get(i).getItemResultAmount().toString();
        }
        BarInfoData commentData = new BarInfoData(yAxis,xAxis);
        commentData.setxAxis(xAxis);
        commentData.setyAxis(yAxis);
        return commentData;
    }

}
