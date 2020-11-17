package com.itheima.mm.service.impl;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :fuwei
 * @Date:2020/11/2 16:04
 * @Version 1.0
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUsername(User user) throws Exception {

        User maper1 = userDao.findByUsername (user.getUsername ());
        if(maper1!=null){
            if(maper1.getPassword ().equals (user.getPassword ())){
                return maper1;
            }else{
                throw new RuntimeException ("密码错误");
            }
        }else{
            throw new RuntimeException ("用户名错误");
        }
    }
}
