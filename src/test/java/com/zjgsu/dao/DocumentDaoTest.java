package com.zjgsu.dao;

import com.zjgsu.entity.Document;
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
public class DocumentDaoTest {
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void count() throws Exception {
        Document document = new Document();
        document.setTitle("测试文件");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("document",document);
        Integer num = documentDao.count(map);
        System.out.println(num);
    }

    @Test
    public void selectByPage() throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstLimitPage",0);
        map.put("pageSize",5);
        List<Document> list = documentDao.selectByPage(map);
        for (Document document : list){
            System.out.println(document.getTitle());
        }
    }

    @Test
    public void save() throws Exception {
        Document document = new Document();
        document.setTitle("测试文件");
        document.setFilename("文件名");
        User user = userDao.selectById(1);
        document.setUser(user);
        documentDao.save(document);
    }

    @Test
    public void deleteById() throws Exception {
        documentDao.deleteById(7);
    }

    @Test
    public void selectById() throws Exception {
        Document document = documentDao.selectById(7);
        System.out.println(document.getTitle());
    }

    @Test
    public void update() throws Exception {
        Document document = new Document();
        document.setId(7);
        document.setTitle("更改名字");
        documentDao.update(document);
    }

}