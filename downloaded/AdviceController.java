package com.xc.springMvc.controller;

import com.xc.springMvc.demain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/11/20.
 */
@Controller
public class AdviceController {


    @RequestMapping("/advice")
    public String getSomething(
            @ModelAttribute("msg") String msg,
            DemoObj obj){
        throw new IllegalArgumentException("非常抱歉，参数有误/ " +
        " @ModelAttribute: "+msg);
    }
}
