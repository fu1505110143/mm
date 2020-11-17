package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

/**
 * @Author :fuwei
 * @Date:2020/11/2 16:18
 * @Version 1.0
 */
public interface UserDao {
    User findByUsername(String username);
}
