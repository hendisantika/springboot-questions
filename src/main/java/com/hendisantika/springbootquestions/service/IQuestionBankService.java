package com.hendisantika.springbootquestions.service;

import com.hendisantika.springbootquestions.entity.Category;
import com.hendisantika.springbootquestions.entity.QuestionAnswers;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.30
 */
public interface IQuestionBankService {
    List<Category> fetchAllCategories();

    List<QuestionAnswers> fetchAllQuestionsAndAnswers();

    void saveCategory(Category category);

    void saveQuestion(QuestionAnswers question);

    void deleteQuestion(String questionId);

    void deleteCategory(String categoryId);

    Category fetchCategory(String categoryId);

    QuestionAnswers fetchQuestionAnswer(String questionId);
}