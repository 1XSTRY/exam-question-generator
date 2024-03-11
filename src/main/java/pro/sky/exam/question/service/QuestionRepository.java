package pro.sky.exam.question.service;

import model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuestionContainingIgnoreCase(String searchTerm);

    List<Question> findByAnswerContainingIgnoreCase(String searchTerm);

    @Query("SELECT q FROM Question q WHERE LOWER(q.question) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(q.answer) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Question> searchQuestions(@Param("searchTerm") String searchTerm);
}
