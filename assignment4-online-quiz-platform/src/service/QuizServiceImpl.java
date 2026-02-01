package service;


import exception.DuplicateResourceException;
import model.Quiz;
import repository.QuizRepository;
import service.interfaces.QuizService;


import java.util.List;

public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public void createQuiz(Quiz quiz) {
        quiz.validate();

        if (quizRepository.existsByName(quiz.getName())) {
            throw new DuplicateResourceException("Quiz with name already exists: " + quiz.getName());
        }

        quizRepository.create(quiz);
    }

    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.getById(id);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.getAll();
    }
}
