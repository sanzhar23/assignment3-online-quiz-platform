import controller.QuestionController;
import controller.QuizController;
import model.EasyQuiz;
import model.Question;
import model.Quiz;
import repository.QuestionRepository;
import repository.QuizRepository;
import service.QuestionServiceImpl;
import service.QuizServiceImpl;
import service.interfaces.QuestionService;
import service.interfaces.QuizService;
import utils.ReflectionUtils;

public class Main {

    public static void main(String[] args) {

        //Repositories
        QuizRepository quizRepository = new QuizRepository();
        QuestionRepository questionRepository = new QuestionRepository();

        //Services
        QuizService quizService = new QuizServiceImpl(quizRepository);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);

        //Controllers
        QuizController quizController = new QuizController(quizService);
        QuestionController questionController = new QuestionController(questionService);

        //Create new quiz (unique name)
        Quiz quiz = new EasyQuiz("Test Quiz " + System.currentTimeMillis());
        quizController.createQuiz(quiz);

        //Print all quizzes
        System.out.println("= ALL QUIZZES =");
        for (Quiz q : quizController.getAllQuizzes()) {
            System.out.println(q.getId() + " | " + q.label());
        }

        //первый квиз из списка
        int quizId = quizController.getAllQuizzes().get(0).getId();

        //Quiz by id
        System.out.println("\n=== QUIZ BY ID ===");
        System.out.println(quizController.getQuizById(quizId).label());

        //Add question (WITHOUT повтор)
        String text = "2 + 2 = ?";
        String answer = "4";

        if (questionRepository.existsSameQuestion(quizId, text, answer)) {
            System.out.println("\nQuestion already exists. Skipping insert.");
        } else {
            Question question = new Question(quizId, text, answer, 1);
            questionController.createQuestion(question);
        }

        //Print questions for quiz
        System.out.println("\n=== QUESTIONS FOR QUIZ " + quizId + " ===");
        for (Question q : questionController.getQuestionsByQuizId(quizId)) {
            System.out.println(
                    q.getId() + " | " +
                            q.getText() + " | " +
                            q.getCorrectAnswer() + " | points=" + q.getPoints()
            );
        }

        //Lambda demo: sort quizzes by name
        System.out.println("\n=== ALL QUIZZES SORTED BY NAME (LAMBDA) ===");
        quizController.getAllQuizzes().stream()
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
                .forEach(q -> System.out.println(q.getId() + " | " + q.label()));

        //Reflection demo
        ReflectionUtils.printClassInfo(Quiz.class);
        ReflectionUtils.printClassInfo(Question.class);
    }
}
