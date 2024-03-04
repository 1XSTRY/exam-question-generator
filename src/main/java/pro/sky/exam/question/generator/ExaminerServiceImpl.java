package pro.sky.exam.question.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount > questionService.getAllQuestions().size()) {
            throw new IllegalArgumentException("Requested amount exceeds the total number of questions.");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            if (randomQuestion != null) {
                selectedQuestions.add(randomQuestion);
            }
        }

        return new ArrayList<>(selectedQuestions);
    }
}
