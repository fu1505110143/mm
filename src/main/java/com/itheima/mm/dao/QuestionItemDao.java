package com.itheima.mm.dao;

import com.itheima.mm.pojo.QuestionItem;

import java.util.List;

/**
 * @Author :fuwei
 * @Date:2020/11/4 22:01
 * @Version 1.0
 */
public interface QuestionItemDao {
   void addQuestionItem(QuestionItem questionItem);
   void addQuestionItemList(List<QuestionItem> questionItemList) ;

}
