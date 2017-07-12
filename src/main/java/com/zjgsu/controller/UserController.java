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

    /**
     * 处理查询请求
     * @param pageIndex 被请求的是第几页
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/user/selectUser")
    public String selectUser(Integer pageIndex, Model model,  User user){
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

    /**
     * 处理删除用户请求
     * @param ids   需要删除的id字符串
     * @return
     */
    @RequestMapping("/user/removeUser")
    public String removeUser(String ids){
        String[] idArray = ids.split(",");
        for (String id : idArray){
            hrmService.removeUserById(Integer.parseInt(id));
        }
        return "redirect:/user/selectUser";
    }

    /**
     * 处理修改用户请求
     * @param flag  标记,1表示跳转到修改页面,2表示执行修改操作
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/user/updateUser")
    public String updateUser(String flag, Model model, User user){
        if (flag.equals("1")){
            User target = hrmService.findUserById(user.getId());
            model.addAttribute("user",target);
            return "user/showUpdateUser";
        } else {
            hrmService.modifyUser(user);
            return  "redirect:/user/selectUser";
        }
    }

    /**
     * 处理添加请求
     * @param flag
     * @param user
     * @return
     */
    @RequestMapping("/user/addUser")
    public String addUser(String flag, User user){
        if (flag.equals("1")){
            return "user/showAddUser";
        } else {
            hrmService.addUser(user);
            return "redirect:/user/selectUser";
        }
    }


}
