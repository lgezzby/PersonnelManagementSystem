package com.zjgsu.controller;

import com.zjgsu.entity.User;
import com.zjgsu.service.HrmService;
import com.zjgsu.util.common.HrmConstants;
import com.zjgsu.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zby on 2017/7/11.
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier(value = "hrmService")
    private HrmService hrmService;


    /**
     * 处理登录请求
     * @param loginname
     * @param password
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession httpSession,
                              Model model){
        User user = hrmService.login(loginname,password);
        if (user != null){
            httpSession.setAttribute(HrmConstants.USER_SESSION,user);
            // 跳转到main页面
            return  "redirect:/main";
        } else {
            model.addAttribute("message","登录名或密码错误!请重新输入");
            return  "forward:/loginForm";
        }
    }

    @RequestMapping("/user/selectUser")
    public String selectUser(Integer pageIndex,
                             Model model,
                             HttpSession httpSession){
        User user = (User) httpSession.getAttribute(HrmConstants.USER_SESSION);
        System.out.println("user = " + user);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<User> users = hrmService.findUser(user,pageModel);
        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "user/user";
    }
}
