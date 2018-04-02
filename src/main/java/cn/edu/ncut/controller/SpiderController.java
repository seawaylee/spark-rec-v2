package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import cn.edu.ncut.util.RedisUtil;
import cn.edu.ncut.util.SystemStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
        BookComment comment = new BookComment();
        resMap.put("comment", bookCommentMapper.selectCount(comment));
        return resMap;
    }

}
