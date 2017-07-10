package com.zjgsu.dao;

import com.zjgsu.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by zby on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class DeptDaoTest {
    @Autowired
    private DeptDao deptDao;

    @Test
    public void selectAllJob() throws Exception {
        List<Dept> list = deptDao.selectAllDept();
        for (Dept dept : list){
            System.out.println(dept.getName());
        }
    }

    @Test
    public void selectById() throws Exception {
        Dept dept = deptDao.selectById(2);
        System.out.println(dept);
    }

    @Test
    public void deleteById() throws Exception {
        deptDao.deleteById(10);
    }

    @Test
    public void update() throws Exception {
        Dept dept = new Dept();
        dept.setId(10);
        dept.setRemark("更新后的描述");
        deptDao.update(dept);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",5);
        map.put("pageSize",5);
        List<Dept> list = deptDao.selectByPage(map);
        for (Dept dept : list){
            System.out.println(dept.getName());
        }
    }

    @Test
    public void count() throws Exception {
        Dept dept = new Dept();
        dept.setName("部");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("dept",dept);
        Integer num = deptDao.count(map);
        System.out.println(num);
    }

    @Test
    public void save() throws Exception {
        Dept dept = new Dept();
        dept.setName("测试用的部门");
        dept.setRemark("描述");
        deptDao.save(dept);
    }

}