import model.EasyQuiz;
import model.Quiz;
import model.Question;
import repository.QuizRepository;
import repository.QuestionRepository;

public class Main {

    public static void main(String[] args) {

        //quiz
        QuizRepository quizRepo = new QuizRepository();

        //создание нового quiz
        Quiz quiz = new EasyQuiz("Test Quiz " + System.currentTimeMillis());
        quizRepo.create(quiz);

        System.out.println("=== ALL QUIZZES ===");
        for (Quiz q : quizRepo.getAll()) {
            System.out.println(q.getId() + " | " + q.label());
        }

        //берем quiz 1
        int quizId = quizRepo.getAll().get(0).getId();

        System.out.println("\n=== QUIZ BY ID ===");
        System.out.println(quizRepo.getById(quizId).label());

        // вопросы
        QuestionRepository questionRepo = new QuestionRepository();

        String text = "2 + 2 = ?";
        String answer = "4";

        if (!questionRepo.existsSameQuestion(quizId, text, answer)) {
            Question question = new Question(quizId, text, answer, 1);
            questionRepo.create(question);
        }
        System.out.println("\n===QUESTIONS FOR QUIZ " + quizId + "===");
        for (Question q : questionRepo.getAllByQuizId(quizId)) {
            System.out.println(
                    q.getId() + " | " +
                            q.getText() + " | " +
                            q.getCorrectAnswer() + " | points=" + q.getPoints()
            );
        }
    }
}
