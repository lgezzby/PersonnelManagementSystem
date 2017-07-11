package com.zjgsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 动态页面跳转
 * Created by zby on 2017/7/11.
 */
@Controller
public class FormController {
    @RequestMapping("/{formName}")
    public String loginForm(@PathVariable String formName){
        System.out.println("formName : " + formName);
        return formName;
    }
}
