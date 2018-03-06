package cn.edu.ncut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NikoBelic
 * @create 2018/3/6 10:46
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @RequestMapping(value = "index")
    public String index() {
        return "main";
    }
}
