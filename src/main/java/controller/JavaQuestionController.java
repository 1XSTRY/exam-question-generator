package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import model.Question;
import pro.sky.exam.question.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    @Autowired
    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addQuestion(@RequestParam String question, @RequestParam String answer) {
        Question newQuestion = new Question(question, answer);
        questionService.addQuestion(newQuestion);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @DeleteMapping("/remove")
    public void removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question targetQuestion = new Question(question, answer);
        questionService.removeQuestion(targetQuestion);
    }
}