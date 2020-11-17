package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/3 16:47
 * @Version 1.0
 */
public interface QuestionDao {
     Long findById(Integer id);
     Long findTotal(QueryPageBean queryPageBean);
    List<Question> findByPage(QueryPageBean queryPageBean);

    void addQuestion(Question question);

    void associationQuestionAndTag(Map parameterMap);
}
