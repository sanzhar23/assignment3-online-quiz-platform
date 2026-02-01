package controller;

import model.Question;
import service.interfaces.QuestionService;

import java.util.List;

public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void createQuestion(Question q) {
        questionService.createQuestion(q);
    }

    public Question getQuestionById(int id) {
        return questionService.getQuestionById(id);
    }

    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }
}
