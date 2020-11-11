package com.hendisantika.springbootquestions.service;

import com.hendisantika.springbootquestions.entity.Category;
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

}
