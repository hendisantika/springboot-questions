package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.entity.Category;
import com.hendisantika.springbootquestions.entity.QuestionAnswers;
import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/questions")
    public String showAllQuestions(Model model) {
        model.addAttribute("questions", questionBankService.fetchAllQuestionsAndAnswers());
        return "questions";
    }

    @PostMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/questions/add")
    public String addQuestion(Model model) {
        model.addAttribute("qn", new QuestionAnswers());
        model.addAttribute("categories", questionBankService.fetchAllCategories());
        return "addQuestion";
    }

    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        questionBankService.saveCategory(category);
        return "redirect:/category";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(@ModelAttribute("qn") QuestionAnswers question) {
        questionBankService.saveQuestion(question);
        return "redirect:/questions";
    }

    @PostMapping("/category/edit")
    public String editCategory(@RequestParam("categoryId") String categoryId, Model model) {
        model.addAttribute("category", questionBankService.fetchCategory(categoryId));
        return "editCategory";
    }
}
