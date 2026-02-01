package service.interfaces;

import model.Question;
import java.util.List;

public interface QuestionService {
    void createQuestion(Question q);
    Question getQuestionById(int id);
    List<Question> getQuestionsByQuizId(int quizId);
}
