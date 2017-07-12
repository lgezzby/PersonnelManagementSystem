package com.zjgsu.dao;

import com.zjgsu.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface DeptDao {
    List<Dept> selectAllDept();

    Dept selectById(int id);

    void deleteById(Integer id);

    void update(@Param("dept") Dept dept);

    List<Dept> selectByPage(@Param("params") Map<String,Object> params);

    Integer count(@Param("param") Map<String,Object> params);

    void save(Dept job);
}
