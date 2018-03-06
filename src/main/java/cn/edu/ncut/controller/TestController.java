package cn.edu.ncut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author NikoBelic
 * @create 2018/3/2 11:16
 */
@Controller
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        return "sample";
    }
}