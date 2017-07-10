package com.zjgsu.dao;

import com.sun.xml.internal.bind.v2.TODO;
import com.zjgsu.entity.Dept;
import com.zjgsu.entity.Employee;
import com.zjgsu.entity.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by zby on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;

    @Test
    public void count() throws Exception {
        // 取出一个员工实例,作为参数输入
        Employee employee = employeeDao.selectById(1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("employee",employee);
        Integer num = employeeDao.count(map);
        System.out.println(num);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",0);
        map.put("pageSize",5);
        List<Employee> list = employeeDao.selectByPage(map);
        for (Employee employee : list){
            System.out.println(employee.getName());
        }
    }

    @Test
    public void save() throws Exception {
        Employee employee = new Employee();
        employee.setName("测试员工");
        employee.setCardId("330327199405080250");
        employee.setAddress("浙江温州");
        employee.setPostCode("310018");
        employee.setTel("15757129711");
        employee.setPhone("669711");
        employee.setQqNum("634065623");
        employee.setEmail("634065623@qq.com");
        employee.setSex(1);
        employee.setParty("党员");
        SimpleDateFormat sdfBirthday = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdfBirthday.parse("1994-05-08");
        employee.setBirthday(birthday);
        employee.setRace("汉");
        employee.setEducation("本科");
        employee.setSpeciality("计算机科学与技术");
        employee.setHobby("羽毛球");
        employee.setRemark("测试数据");
        SimpleDateFormat sdfCreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createDate = sdfCreateDate.parse("2017-7-10 10:30:00");
        employee.setCreateDate(createDate);
        Dept dept = deptDao.selectById(1);
        employee.setDept(dept);
        Job job = jobDao.selectById(2);
        employee.setJob(job);
        employeeDao.save(employee);
    }

    @Test
    public void deleteById() throws Exception {
        employeeDao.deleteById(22);
    }

    @Test
    public void selectById() throws Exception {
        Employee employee = employeeDao.selectById(1);
        System.out.println(employee.getName());
        System.out.println(employee.getDept().getName());
        System.out.println(employee.getJob().getName());
    }

    @Test
    public void update() throws Exception {
        Employee employee = new Employee();
        employee.setId(22);
        employee.setName("简化修改测试数据");
        employeeDao.update(employee);
    }

}