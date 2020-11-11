package com.hendisantika.springbootquestions.controller;

import com.hendisantika.springbootquestions.entity.Category;
import com.hendisantika.springbootquestions.entity.QuestionAnswers;
import com.hendisantika.springbootquestions.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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

    @PostMapping("/questions/edit")
    public String editQuestion(@RequestParam("questionId") String questionId, Model model) {
        model.addAttribute("qn", questionBankService.fetchQuestionAnswer(questionId));
        model.addAttribute("categories", questionBankService.fetchAllCategories());
        return "editQuestion";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam("categoryId") String categoryId) {
        questionBankService.deleteCategory(categoryId);
        return "redirect:/category";
    }

    @PostMapping("/questions/delete")
    public String deleteQuestion(@RequestParam("questionId") String questionId) {
        questionBankService.deleteQuestion(questionId);
        return "redirect:/questions";
    }

    @GetMapping("/oauthinfo")
    @ResponseBody
    public String oauthUserInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                @AuthenticationPrincipal OAuth2User oauth2User) {
        return
                "User Name: " + oauth2User.getName() + "<br/>" +
                        "User Authorities: " + oauth2User.getAuthorities() + "<br/>" +
                        "Client Name: " + authorizedClient.getClientRegistration().getClientName() + "<br/>" +
                        this.prettyPrintAttributes(oauth2User.getAttributes());
    }

    private String prettyPrintAttributes(Map<String, Object> attributes) {
        String acc = "User Attributes: <br/><div style='padding-left:20px'>";
        for (String key : attributes.keySet()) {
            Object value = attributes.get(key);
            acc += "<div>" + key + ":&nbsp" + value.toString() + "</div>";
        }
        return acc + "</div>";
    }

}
