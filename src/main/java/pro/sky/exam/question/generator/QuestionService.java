package pro.sky.exam.question.generator;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question getRandomQuestion();
    void addQuestion(Question question);
    void removeQuestion(Question question);
    List<Question> findQuestions(String searchTerm);
}