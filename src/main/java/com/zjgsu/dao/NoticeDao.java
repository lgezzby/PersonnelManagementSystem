package com.zjgsu.dao;

import com.zjgsu.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface NoticeDao {
    Integer count(@Param("param") Map<String,Object> params);

    List<Notice> selectByPage(@Param("param") Map<String,Object> params);

    void save(Notice notice);

    void deleteById(Integer id);

    Notice selectById(Integer id);

    void update(Notice notice);
}
