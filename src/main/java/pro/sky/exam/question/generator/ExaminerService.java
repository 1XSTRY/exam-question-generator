package pro.sky.exam.question.generator;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}

