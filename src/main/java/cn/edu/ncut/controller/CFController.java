package cn.edu.ncut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:01
 */
@Controller
@RequestMapping(value = "/cf")
public class CFController {

    @RequestMapping(value = "getDataStatus")
    public String getDataStatus(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        return "cf/dataStatus";
    }

    @RequestMapping(value = "buildModel")
    public String buildModel(ModelMap modelMap) {
        return "cf/dataModel";
    }

    @RequestMapping(value = "getRecRes")
    public String getRecRes(ModelMap modelMap) {
        return "cf/recRes";
    }
}
