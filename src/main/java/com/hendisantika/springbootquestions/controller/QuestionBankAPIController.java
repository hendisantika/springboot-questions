package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
