package com.zjgsu.controller;

import com.zjgsu.entity.Dept;
import com.zjgsu.entity.Job;
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
public class JobController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping("/job/selectJob")
    private String selectJob(Integer pageIndex, Model model, Job job){
        System.out.println("job = " + job);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        model.addAttribute("job",job);
        List<Job> jobs = hrmService.findJob(job,pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "job/job";
    }

    @RequestMapping("/job/removeJob")
    private String removeJob(String ids){
        String[] idArray = ids.split(",");
        for (String s : idArray){
            hrmService.removeJobById(Integer.parseInt(s));
        }
        return "redirect:/job/selectJob";
    }

    @RequestMapping("/job/addJob")
    private String addJob(String flag, Job job){
        if (flag.equals("1")){
            return "job/showAddJob";
        } else {
            hrmService.addJob(job);
            return "redirect:/job/selectJob";
        }
    }

    @RequestMapping("/job/updateJob")
    private String updateJob(String flag, Job job, Model model){
        if (flag.equals("1")){
            Job target = hrmService.findJobById(job.getId());
            model.addAttribute("job",target);
            return "job/showUpdateJob";
        } else {
               hrmService.modifyJob(job);
               return "redirect:/job/selectJob";
        }
    }
}
