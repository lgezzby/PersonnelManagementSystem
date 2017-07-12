package com.zjgsu.controller;

import com.zjgsu.entity.Dept;
import com.zjgsu.service.HrmService;
import com.zjgsu.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zby on 2017/7/11.
 */
@Controller
public class DeptController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * @param pageIndex
     * @param model
     * @param dept
     * @return
     */
    // TODO 查询时点击下一页 参数不会传递过来
    @RequestMapping("/dept/selectDept")
    private String selectDept(Integer pageIndex, Model model, Dept dept){
        System.out.println("dept = " + dept);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Dept> depts = hrmService.findDept(dept,pageModel);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        return "dept/dept";
    }

    @RequestMapping("/dept/removeDept")
    private String removeDept(String ids){
        String[] idArray = ids.split(",");
        for (String s : idArray){
            hrmService.removeDeptById(Integer.parseInt(s));
        }
        return "redirect:/dept/selectDept";
    }

    @RequestMapping("/dept/addDept")
    private String addDept(String flag, Dept dept){
        if (flag.equals("1")){
            return "dept/showAddDept";
        } else {
            hrmService.addDept(dept);
            return "redirect:/dept/selectDept";
        }
    }

    @RequestMapping("/dept/updateDept")
    private String updateDept(String flag, Dept dept, Model model){
        if (flag.equals("1")){
            Dept target = hrmService.findDeptById(dept.getId());
            model.addAttribute("dept",target);
            return "dept/showUpdateDept";
        } else {
               hrmService.modifyDept(dept);
               return "redirect:/dept/selectDept";
        }
    }
}
