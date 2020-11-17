package com.itheima.mm.controller;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author :fuwei
 * @Date:2020/11/2 16:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping ("/login")
    public Result login(@RequestBody User user, HttpSession session) throws IOException {
        //1.获得请求参数, 封装成User对象
        //2.调用业务 进行登录
        //3.判断是否登录成功(如果成功,通过session保存登录状态), 响应
        try {
            User loginUser = userService.findByUsername (user);
            session.setAttribute (Constants.USER_SESSION_KEY,loginUser);
            return new Result (true,"登录成功",loginUser);
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,e.getMessage ());
        }

    }
    @RequestMapping ("/user/logout")
    public Result logout(HttpSession session) throws IOException {
        session.invalidate ();
        try {
            return new Result (true,"退出成功");
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,"退出失败");
        }
    }
}
