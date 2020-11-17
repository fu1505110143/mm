package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/15 18:02
 */
public interface CourseService {
    void addCourse(Course course) throws IOException;

    PageResult findListByPage(QueryPageBean queryPageBean) throws IOException;

    void update(Course course) throws IOException;

    void deleteById(Integer id) throws Exception;

    List<Course> findAll(Map parameterMap) throws IOException;
}
