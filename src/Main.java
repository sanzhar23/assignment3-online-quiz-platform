import model.Quiz;
import model.Question;
import repository.QuizRepository;
import repository.QuestionRepository;

public class Main {

    public static void main(String[] args) {

        QuizRepository quizRepo = new QuizRepository();
        QuestionRepository questionRepo = new QuestionRepository();

        System.out.println("=== ALL QUIZZES ===");
        for (Quiz q : quizRepo.getAll()) {
            System.out.println(q.getId() + " | " + q.label());
        }

        if (quizRepo.getAll().isEmpty()) {
            System.out.println("No quizzes found.");
            return;
        }

        int quizId = quizRepo.getAll().get(0).getId();

        System.out.println("\n=== QUIZ BY ID ===");
        System.out.println(quizRepo.getById(quizId).label());

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
