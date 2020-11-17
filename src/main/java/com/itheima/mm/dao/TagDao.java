package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/3 16:46
 * @Version 1.0
 */
public interface TagDao {
    Long findById(Integer id);
    List<Tag> findTagListByCourseId(int courseId);

    void associationQuestionTag(Map parameterMap);
}
