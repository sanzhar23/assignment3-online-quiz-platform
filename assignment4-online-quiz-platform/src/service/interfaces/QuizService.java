package service.interfaces;


public interface QuizService {
    void createQuiz(model.Quiz quiz);
    model.Quiz getQuizById(int id);
    java.util.List<model.Quiz> getAllQuizzes();
}

