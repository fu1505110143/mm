package com.itheima.mm.service.impl;


import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.QuestionItemDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;
import com.itheima.mm.pojo.Tag;
import com.itheima.mm.service.QuestionService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :fuwei
 * @Date:2020/11/4 17:45
 * @Version 1.0
 */
@Service
public class QuestionServiceImpl  implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionItemDao questionItemDao;
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) throws IOException {
        Long total = questionDao.findTotal (queryPageBean);
        List<Question> questionList =questionDao.findByPage (queryPageBean);
        return new PageResult(total,questionList);
    }
@Override
@Transactional
    public void addQuestion(Question question){
        //这三步需要使用事务
        SqlSession sqlSession = null;
        try {
            //1. 将题目自身的。信息存储到t_question表
            questionDao.addQuestion(question);

            //2. 将题目的选项信息存储到t_question_item表
            //获取题目选项列表
            List<QuestionItem> questionItemList = question.getQuestionItemList();
            if (questionItemList != null) {
                for (QuestionItem questionItem : questionItemList) {
                    //遍历出每一个题目选项
                    //添加之前，设置每一个选项的所属试题的id(questionId)
                    questionItem.setQuestionId(question.getId());
                    //添加每一个选项
                    questionItemDao.addQuestionItem(questionItem);
                }
            }

            //3. 关联题目和tag(标签)
            //获取该题目的标签列表
            List<Tag> tagList = question.getTagList();
            //遍历出每一个标签
            if (tagList != null) {
                for (Tag tag : tagList) {
                    //封装要存储到数据库的数据
                    Map parameterMap = new HashMap();
                    parameterMap.put("questionId", question.getId());
                    parameterMap.put("tagId", tag.getId());
                    questionDao.associationQuestionAndTag(parameterMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
