package com.zjgsu.dao;

import com.zjgsu.entity.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface JobDao {
    List<Job> selectAllJob();

    Job selectById(int id);

    void deleteById(Integer id);

    void update(@Param("job") Job job);

    List<Job> selectByPage(@Param("param") Map<String,Object> params);

    Integer count(@Param("param") Map<String,Object> params);

    void save(Job job);
}
