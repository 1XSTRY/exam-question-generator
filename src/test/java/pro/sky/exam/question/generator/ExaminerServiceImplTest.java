package pro.sky.exam.question.generator;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.exam.question.service.ExaminerServiceImpl;
import pro.sky.exam.question.service.QuestionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
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
		when(questionService.getAllQuestions()).thenReturn(Collections.emptyList());

		List<Question> selectedQuestions = examinerService.getQuestions(2);
		assertNotNull(selectedQuestions);
		assertTrue(selectedQuestions.isEmpty());
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

		assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(-1));
	}

	@Test
	void testGetQuestionsWithZeroAmount() {

		List<Question> selectedQuestions = examinerService.getQuestions(0);
		assertNotNull(selectedQuestions);
		assertTrue(selectedQuestions.isEmpty());
	}

	@Test
	void testGetQuestionsWithMaxAmount() {
		Question question1 = new Question("Q1", "A1");
		Question question2 = new Question("Q2", "A2");
		Question question3 = new Question("Q3", "A3");
		when(questionService.getAllQuestions()).thenReturn(Arrays.asList(question1, question2, question3));

		List<Question> selectedQuestions = examinerService.getQuestions(3);
		assertNotNull(selectedQuestions);
		assertEquals(3, selectedQuestions.size());
		assertTrue(selectedQuestions.contains(question1));
		assertTrue(selectedQuestions.contains(question2));
		assertTrue(selectedQuestions.contains(question3));
	}
}
