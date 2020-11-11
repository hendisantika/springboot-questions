package com.hendisantika.springbootquestions.service;

import com.hendisantika.springbootquestions.entity.Category;
import com.hendisantika.springbootquestions.entity.QuestionAnswers;
import com.hendisantika.springbootquestions.repository.CategoryRepository;
import com.hendisantika.springbootquestions.repository.QuestionAnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.32
 */
@Service
public class QuestionBankService implements IQuestionBankService {

    @Autowired
    private QuestionAnswersRepository questionAnswersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories() {
        return categoryRepository.findAllByOrderByCategoryId();
    }

    @Override
    public List<QuestionAnswers> fetchAllQuestionsAndAnswers() {
        return questionAnswersRepository.findAllByOrderByQuestionId();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void saveQuestion(QuestionAnswers question) {
        questionAnswersRepository.save(question);
    }

    @Override
    public void deleteQuestion(String questionId) {
        questionAnswersRepository.deleteById(Integer.valueOf(questionId));
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(Integer.valueOf(categoryId));
    }

    @Override
    public Category fetchCategory(String categoryId) {
        return categoryRepository.findById(Integer.valueOf(categoryId)).orElse(null);
    }

    @Override
    public QuestionAnswers fetchQuestionAnswer(String questionId) {
        return questionAnswersRepository.findById(Integer.valueOf(questionId)).orElse(null);
    }
}
