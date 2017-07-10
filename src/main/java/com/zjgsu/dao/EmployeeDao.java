package com.zjgsu.dao;

import com.zjgsu.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface EmployeeDao {
    Integer count(@Param("param") Map<String,Object> params);

    List<Employee> selectByPage(@Param("param") Map<String,Object> params);

    void save(Employee employee);

    void deleteById(Integer id);

    Employee selectById(Integer id);

    void update(Employee employee);
}
