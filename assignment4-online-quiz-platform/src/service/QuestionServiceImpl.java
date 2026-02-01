package service;

import exception.DuplicateResourceException;
import model.Question;
import repository.QuestionRepository;
import service.interfaces.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void createQuestion(Question q) {
        q.validate();

        if (questionRepository.existsSameQuestion(q.getQuizId(), q.getText(), q.getCorrectAnswer())) {
            throw new DuplicateResourceException("Duplicate question for quizId=" + q.getQuizId());
        }

        questionRepository.create(q);
    }

    @Override
    public Question getQuestionById(int id) {
        return questionRepository.getById(id);
    }

    @Override
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionRepository.getAllByQuizId(quizId);
    }
}
