package pro.sky.exam.question.generator;

import org.junit.jupiter.api.BeforeEach;
import model.Question;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.exam.question.service.ExaminerServiceImpl;
import pro.sky.exam.question.service.QuestionService;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private ExaminerServiceImpl examinerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetQuestionsWithEmptyService() {
		// Получение общего количества вопросов
		int totalQuestions = questionService.getTotalQuestions();

		// Запрашиваем количество вопросов, которое меньше или равно общему количеству
		int requestedAmount = totalQuestions - 1; // Например, запрашиваем на один вопрос меньше

		// Запрашиваем вопросы
		List<Question> questions = examinerService.getQuestions(requestedAmount);

		// Добавить проверку, что список вопросов не пустой и так далее
		assertNotNull(questions);
		assertTrue(!questions.isEmpty());
		// Дополнительные проверки по необходимости
	}

	@Test
	void testGetQuestionsWithInsufficientQuestions() {
		Question question1 = new Question("Q1", "A1");
		when(questionService.getAllQuestions()).thenReturn(Collections.singletonList(question1));

		assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(2));
	}

	@Test
	void testGetQuestionsWithUniqueQuestions() {
		Question question1 = new Question("Q1", "A1");
		Question question2 = new Question("Q2", "A2");
		Question question3 = new Question("Q3", "A3");
		when(questionService.getAllQuestions()).thenReturn(Arrays.asList(question1, question2, question3));

		List<Question> selectedQuestions = examinerService.getQuestions(2);
		assertNotNull(selectedQuestions);
		assertEquals(2, selectedQuestions.size());
		assertTrue(selectedQuestions.contains(question1));
		assertTrue(selectedQuestions.contains(question2));
		assertFalse(selectedQuestions.contains(question3));
	}

	@Test
	void testGetQuestionsWithNegativeAmount() {
		when(questionService.getAllQuestions()).thenReturn(Collections.emptyList());

		// Use assertThrows to check for IllegalArgumentException
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(-1));

		assertEquals("Requested amount exceeds the totаl number of questions.", exception.getMessage());
		throw new IllegalArgumentException("Requested amount exceeds the total number of questions.");
	}

	@Test
	void testGetQuestionsWithZeroAmount() {
		List<Question> selectedQuestions = examinerService.getQuestions(0);
		assertNotNull(selectedQuestions);
		assertTrue(selectedQuestions.isEmpty());
	}


}


