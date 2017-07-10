package com.zjgsu.dao;

import com.zjgsu.entity.Notice;
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
 * Created by zby on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class NoticeDaoTest {
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void count() throws Exception {
        Notice notice = noticeDao.selectById(19);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("notice",notice);
        Integer num = noticeDao.count(map);
        System.out.println(num);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",0);
        map.put("pageSize",5);
        List<Notice> list = noticeDao.selectByPage(map);
        for (Notice notice : list){
            System.out.println(notice.getTitle());
        }
    }

    @Test
    public void save() throws Exception {
        User user = userDao.selectById(1);
        Notice notice = new Notice();
        notice.setTitle("测试通知待删除");
        notice.setContent("内容");
        notice.setUser(user);
        noticeDao.save(notice);
    }

    @Test
    public void deleteById() throws Exception {
        noticeDao.deleteById(20);
    }

    @Test
    public void selectById() throws Exception {
        Notice notice = noticeDao.selectById(19);
        System.out.println(notice);
    }

    @Test
    public void update() throws Exception {
        Notice notice = new Notice();
        notice.setId(20);
        notice.setTitle("已改");
        noticeDao.update(notice);
    }

}