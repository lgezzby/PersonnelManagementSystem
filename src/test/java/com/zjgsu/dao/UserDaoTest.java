package com.zjgsu.dao;

import com.zjgsu.entity.User;
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
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void selectByUsernameAndPassword() throws Exception {
        User user = userDao.selectByUsernameAndPassword("admin","123456");
        System.out.println(user.getUsername());
    }

    @Test
    public void selectById() throws Exception {
        User user = userDao.selectById(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void deleteById() throws Exception {
        userDao.deleteById(3);
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("测试用例改名字");
        user.setStatus(0);
        userDao.update(user);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",0);
        map.put("pageSize",5);
        List<User> list = userDao.selectByPage(map);
        for (User user : list){
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void count() throws Exception {
        User user = new User();
        user.setUsername("测试用例");
        user.setStatus(0);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user",user);
        Integer num = userDao.count(map);
        System.out.println(num);
    }

    @Test
    public void save() throws Exception {
        User insertUser = new User();
        insertUser.setLoginname("test");
        insertUser.setPassword("123");
        insertUser.setUsername("测试用例");
        insertUser.setStatus(1);

        userDao.save(insertUser);
    }

}