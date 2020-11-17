package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

import java.io.IOException;

/**
 * @Author :fuwei
 * @Date:2020/11/15 18:02
 */
public interface QuestionService {
    PageResult findByPage(QueryPageBean queryPageBean) throws IOException;

    void addQuestion(Question question);
}
