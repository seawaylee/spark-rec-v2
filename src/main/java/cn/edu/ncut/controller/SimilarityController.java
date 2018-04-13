package cn.edu.ncut.controller;

import cn.edu.ncut.dao.spider.UserBookStatusMapper;
import cn.edu.ncut.dto.spider.UserBookStatus;
import cn.edu.ncut.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:02
 */
@Controller
@RequestMapping(value = "/similar")
public class SimilarityController {
    @Autowired
    private UserBookStatusMapper userBookStatusMapper;

    @RequestMapping(value = "/showData")
    public String showSysStatus(HttpServletRequest request) {
        return "similar/data";
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
}
