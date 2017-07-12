package com.zjgsu.controller;

import com.zjgsu.entity.Dept;
import com.zjgsu.entity.Employee;
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
public class EmployeeController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping("/employee/selectEmployee")
    private String selectEmployee(Integer pageIndex, Model model, Employee employee, Integer job_id, Integer dept_id) {
        // 模糊查找时判断是否有关联对象传递,如果有创建并封装关联对象
        this.genericAssociation(job_id, dept_id, employee);

        System.out.println("employee = " + employee);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        // 装填view视图参数
        List<Job> jobs = hrmService.findAllJob();
        List<Dept> depts = hrmService.findAllDept();

        List<Employee> employees = hrmService.findEmployee(employee, pageModel);

        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("employees", employees);
        model.addAttribute("pageModel", pageModel);
        return "employee/employee";
    }

    private void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
        if (job_id != null && job_id != 0 && !job_id.equals("0")) {
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null && dept_id != 0 && !dept_id.equals("0")) {
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}
