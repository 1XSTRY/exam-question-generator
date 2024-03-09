package pro.sky.exam.question.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.exam.question.service.JavaQuestionService;
import model.Question;
import pro.sky.exam.question.service.QuestionRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRandomQuestion() {
        javaQuestionService.addQuestion(new Question("Q1", "A1"));
        javaQuestionService.addQuestion(new Question("Q2", "A2"));

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("Q1", "A1");
        javaQuestionService.addQuestion(question);

        verify(questionRepository, times(1)).save(question);
        List<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertNotNull(allQuestions);
        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = new Question("Q1", "A1");
        javaQuestionService.addQuestion(question);

        javaQuestionService.removeQuestion(question);

        verify(questionRepository, times(1)).delete(question);
        List<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertNotNull(allQuestions);
        assertTrue(allQuestions.isEmpty());
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
