package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.entity.Category;
import com.hendisantika.springbootquestions.entity.QuestionAnswers;
import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.40
 */
@RestController
@RequestMapping("/api")
public class QuestionBankAPIController {
    @Autowired
    private QuestionBankService questionBankService;

    @RequestMapping("/questions")
    List<QuestionAnswers> fetchAllQuestionsAndAnswers() {
        return questionBankService.fetchAllQuestionsAndAnswers();
    }

    @RequestMapping("/categories")
    List<Category> fetchAllCategories() {
        return questionBankService.fetchAllCategories();
    }

}
