package com.itheima.mm.service;

import com.itheima.mm.pojo.User;

/**
 * @Author :fuwei
 * @Date:2020/11/15 18:02
 */
public interface UserService {
    User findByUsername(User user) throws Exception;
}
