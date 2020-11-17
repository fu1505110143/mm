package com.itheima.mm.dao;


import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/2 18:04
 * @Version 1.0
 */
public interface CourseDao {
       void addCourse(Course course);
       List<Course> findByPage(QueryPageBean queryPageBean);
       Long findTotal(QueryPageBean queryPageBean);
       void update(Course course);
       void deleteById(Integer id);
       List<Course> findAll(Map parameterMap);
}
