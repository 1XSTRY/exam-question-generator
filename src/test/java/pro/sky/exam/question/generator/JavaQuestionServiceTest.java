package pro.sky.exam.question.generator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void testGetRandomQuestion() {
        // Подготовка данных для теста
        Question question1 = new Question("Q1", "A1");
        Question question2 = new Question("Q2", "A2");
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);

        // Тестирование
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }

    // Другие тесты
}

class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {
        // Подготовка данных для теста
        Question question1 = new Question("Q1", "A1");
        Question question2 = new Question("Q2", "A2");
        when(questionService.getAllQuestions()).thenReturn(Arrays.asList(question1, question2));

        // Тестирование
        List<Question> selectedQuestions = examinerService.getQuestions(2);
        assertNotNull(selectedQuestions);
        assertEquals(2, selectedQuestions.size());
        assertTrue(selectedQuestions.contains(question1));
        assertTrue(selectedQuestions.contains(question2));
    }

    // Другие тесты
}
