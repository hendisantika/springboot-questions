package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.41
 */
@Controller
public class QuestionBankUIController {

    @Autowired
    private QuestionBankService questionBankService;

}
