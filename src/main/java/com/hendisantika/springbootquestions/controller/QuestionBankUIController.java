package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/category")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", questionBankService.fetchAllCategories());
        return "category";
    }
}
