package pro.sky.exam.question.service;

import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaQuestionService implements QuestionService {
    private List<Question> javaQuestions;

    public JavaQuestionService() {
        // Инициализация списка вопросов
        javaQuestions = new ArrayList<>();
        // Здесь можно добавить начальные вопросы при необходимости
    }

    @Override
    public List<Question> getAllQuestions() {
        return javaQuestions;
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestions.isEmpty()) {
            return null; // Можно рассмотреть вариант выбрасывания исключения
        }

        Random random = new Random();
        int randomIndex = random.nextInt(javaQuestions.size());
        return javaQuestions.get(randomIndex);
    }

    @Override
    public void addQuestion(Question question) {
        javaQuestions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        javaQuestions.remove(question);
    }

    @Override
    public List<Question> findQuestions(String searchTerm) {
        List<Question> foundQuestions = new ArrayList<>();
        for (Question question : javaQuestions) {
            if (question.getQuestion().contains(searchTerm) || question.getAnswer().contains(searchTerm)) {
                foundQuestions.add(question);
            }
        }
        return foundQuestions;
    }
}
