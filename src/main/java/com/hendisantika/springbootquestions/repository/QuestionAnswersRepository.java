package com.hendisantika.springbootquestions.repository;

import com.hendisantika.springbootquestions.entity.QuestionAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.27
 */

public interface QuestionAnswersRepository extends JpaRepository<QuestionAnswers, Integer>,
        JpaSpecificationExecutor<QuestionAnswers> {
    List<QuestionAnswers> findAllByOrderByQuestionId();
}
