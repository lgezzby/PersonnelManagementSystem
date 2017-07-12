package com.zjgsu.dao;

import com.zjgsu.entity.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface DocumentDao {
    Integer count(@Param("param") Map<String,Object> params);

    List<Document> selectByPage(@Param("params") Map<String,Object> params);

    void save(Document document);

    void deleteById(Integer id);

    Document selectById(Integer id);

    void update(Document document);
}
