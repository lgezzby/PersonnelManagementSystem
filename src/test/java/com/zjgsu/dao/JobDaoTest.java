package com.zjgsu.dao;

import com.zjgsu.entity.Job;
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
public class JobDaoTest {
    @Autowired
    private JobDao jobDao;

    @Test
    public void update() throws Exception {
        Job job = new Job();
        job.setId(10);
        job.setName("更新后的测试");
        job.setRemark("更新后的描述");
        jobDao.update(job);
    }

    @Test
    public void save() throws Exception {
        Job job = new Job();
        job.setName("测试用例工作");
        job.setRemark("仅供添加删除");
        jobDao.save(job);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",5);
        map.put("pageSize",5);
        List<Job> list = jobDao.selectByPage(map);
        for (Job job : list){
            System.out.println(job.getName());
        }
    }

    @Test
    public void count() throws Exception {
        Job job = new Job();
        job.setName("Java");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("job",job);
        Integer num = jobDao.count(map);
        System.out.println(num);
    }

    @Test
    public void selectAllJob() throws Exception {
        List<Job> list = jobDao.selectAllJob();
        for (Job job : list){
            System.out.println(job.getName());
        }
    }

    @Test
    public void selectById() throws Exception {
        Job job = jobDao.selectById(2);
        System.out.println(job);
    }

    @Test
    public void deleteById() throws Exception {
        jobDao.deleteById(10);
    }

}