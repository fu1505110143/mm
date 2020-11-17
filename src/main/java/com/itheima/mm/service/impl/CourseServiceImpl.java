package com.itheima.mm.service.impl;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CourseService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/2 18:04
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CatalogDao catalogDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private QuestionDao questionDao;
    @Override
    public void addCourse(Course course) throws IOException {
       courseDao.addCourse (course);
    }
@Override
    public PageResult findListByPage(QueryPageBean queryPageBean) throws IOException {
        Map map = queryPageBean.getQueryParams ();
        if (map != null) {
            if (!map.get ("status").equals ("")) {
                String status = (Integer) map.get ("status") + "";
                map.put ("status", status);
            }
        }
        Long total = courseDao.findTotal (queryPageBean);
        List<Course> courseList = courseDao.findByPage (queryPageBean);
        PageResult pageResult = new PageResult (total, courseList);
        return pageResult;
    }
@Override
    public void update(Course course) throws IOException {
        courseDao.update (course);
    }
@Override
@Transactional
    public void deleteById(Integer id) throws Exception {
        SqlSession sqlSession = null;
        try {
            Long long1 = catalogDao.findById (id);
            if (long1 != 0) {
                throw new RuntimeException ("有关联的二级目录,不能删除");
            }
            Long byId = tagDao.findById (id);
            if (byId != 0) {
                throw new RuntimeException ("有关联的标签,不能删除");
            }
           ;
            Long byId1 = questionDao.findById (id);
            if (byId1 != 0) {
                throw new RuntimeException ("有关联的题目,不能删除");
            }
            courseDao.deleteById (id);
        } catch (Exception e) {
            e.printStackTrace ();
            throw new RuntimeException (e.getMessage ());
        }
    }

@Override
    public List<Course> findAll(Map parameterMap) throws IOException {
            List<Course> all = courseDao.findAll (parameterMap);
            return all;
    }
}
