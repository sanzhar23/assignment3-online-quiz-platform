import model.EasyQuiz;
import model.Quiz;
import model.Question;
import repository.QuizRepository;
import repository.QuestionRepository;

public class Main {

    public static void main(String[] args) {

        // ===== QUIZ PART =====
        QuizRepository quizRepo = new QuizRepository();

        // создаём новый квиз (уникальное имя, чтобы не ловить duplicate)
        Quiz quiz = new EasyQuiz("Test Quiz " + System.currentTimeMillis());
        quizRepo.create(quiz);

        System.out.println("=== ALL QUIZZES ===");
        for (Quiz q : quizRepo.getAll()) {
            System.out.println(q.getId() + " | " + q.label());
        }

        // берём первый квиз
        int quizId = quizRepo.getAll().get(0).getId();

        System.out.println("\n=== QUIZ BY ID ===");
        System.out.println(quizRepo.getById(quizId).label());

        // ===== QUESTION PART =====
        QuestionRepository questionRepo = new QuestionRepository();

        // добавляем вопрос к этому квизу
        Question question = new Question(
                quizId,
                "2 + 2 = ?",
                "4",
                1
        );
        questionRepo.create(question);

        System.out.println("\n=== QUESTIONS FOR QUIZ " + quizId + " ===");
        for (Question q : questionRepo.getAllByQuizId(quizId)) {
            System.out.println(
                    q.getId() + " | " +
                            q.getText() + " | " +
                            q.getCorrectAnswer() + " | points=" + q.getPoints()
            );
        }
    }
}
