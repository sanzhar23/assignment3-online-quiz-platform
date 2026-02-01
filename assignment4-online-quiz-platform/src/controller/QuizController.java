package controller;

import model.Quiz;
import service.interfaces.QuizService;

import java.util.List;

public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    public void createQuiz(Quiz quiz) {
        quizService.createQuiz(quiz);
    }

    public Quiz getQuizById(int id) {
        return quizService.getQuizById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
}
