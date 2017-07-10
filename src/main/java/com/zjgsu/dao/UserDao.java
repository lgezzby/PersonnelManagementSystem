package com.zjgsu.dao;

import com.zjgsu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zby on 2017/7/7.
 */
public interface UserDao {
    User selectByUsernameAndPassword(@Param("loginname") String loginname,
                                     @Param("password") String password);

    User selectById(@Param("id") Integer id);

    void deleteById(@Param("id") Integer id);

    void update(@Param("user") User user);

    // 动态查询
    List<User> selectByPage(@Param("param") Map<String,Object> params);

    // 根据参数查询用户总数
    Integer count(Map<String,Object> params);

    // 动态插入用户
    void save(@Param("user") User user);
}
