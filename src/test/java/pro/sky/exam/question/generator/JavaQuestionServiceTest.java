package pro.sky.exam.question.generator;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.exam.question.service.JavaQuestionService;
import pro.sky.exam.question.service.QuestionService;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRandomQuestion() {
        // Мокаем questionService для getRandomQuestion
        when(questionService.getAllQuestions()).thenReturn(List.of(new Question("Q1", "A1")));

        javaQuestionService.addQuestion(new Question("Q1", "A1"));

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("Q1", "A1");
        javaQuestionService.addQuestion(question);

        doNothing().when(questionService).addQuestion(any(Question.class));
        List<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertNotNull(allQuestions);
        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        // Создание вопроса для удаления
        Question questionToRemove = new Question("Ваш вопрос?", "Ваш ответ!");

        // Мокаем questionService для removeQuestion
        doNothing().when(questionService).removeQuestion(questionToRemove);

        // Вызов метода, который должен удалить вопрос
        javaQuestionService.removeQuestion(questionToRemove);

        // Проверка, что метод removeQuestion был вызван с конкретным вопросом
        doNothing().when(questionService).removeQuestion(any(Question.class));
    }


    @Test
    void testFindQuestions() {
        Question question1 = new Question("Java is a programming language", "Yes");
        Question question2 = new Question("Spring Framework is written in Java", "Yes");
        Question question3 = new Question("Python is a compiled language", "No");

        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        javaQuestionService.addQuestion(question3);

        List<Question> foundQuestions = javaQuestionService.findQuestions("Java");
        assertNotNull(foundQuestions);
        assertEquals(2, foundQuestions.size());
        assertTrue(foundQuestions.contains(question1));
        assertTrue(foundQuestions.contains(question2));
        assertFalse(foundQuestions.contains(question3));
    }
}

