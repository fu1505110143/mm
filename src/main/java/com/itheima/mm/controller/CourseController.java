package com.itheima.mm.controller;


import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/2 18:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping ("/add")
    public Result addCourse(@RequestBody Course course, HttpSession session) throws IOException {
        try {
            User user = (User) session.getAttribute (Constants.USER_SESSION_KEY);
            course.setCreateDate (DateUtils.parseDate2String (new Date ()));
            course.setOrderNo (1);
            course.setUserId (user.getId ());
            courseService.addCourse(course);
            return new Result (true,"添加成功");
        } catch (IOException e) {
            e.printStackTrace ();
            return new Result (false,"添加成功");
        }

    }
    @RequestMapping ("/list")
    public Result findListByPage(@RequestBody QueryPageBean queryPageBean) throws IOException {
        try {
            PageResult pageResult = courseService.findListByPage (queryPageBean);
            return new Result (true,"查询成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,"查询失败");
        }

        //2.调用业务 获得分页的数据 PageResult
        //3.把PageResult封装成Result 响应json
    }
    @RequestMapping ("/update")
    public Result updateCourse(@RequestBody Course course,HttpSession session) throws IOException {

        //2.给Course补全数据(修改人的id, 时间...)
        //3.调用业务
        //4.响应
        try {

            User user = (User)session.getAttribute (Constants.USER_SESSION_KEY);
            course.setUserId (user.getId ());
            course.setCreateDate (DateUtils.parseDate2String(new Date ()));
             courseService.update(course);
            return new Result (true,"修改成功",course);
        } catch (IOException e) {
            e.printStackTrace ();
            return new Result (false,"修改失败");
        }
    }
    @RequestMapping ("/delete")
    public Result deleteById(int id) throws IOException {
        try {
            courseService.deleteById(id);
            return new Result (true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,e.getMessage ());
        }

    }
    @RequestMapping ("/findAll")
    public Result findAll(@RequestBody Map parameterMap) throws IOException {
        try {
            List<Course> courseList= courseService.findAll(parameterMap);
            System.out.println (courseList);
            return new Result (true,"加载所有学科成功",courseList);
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,"加载所有学科失败");
        }

    }
}
