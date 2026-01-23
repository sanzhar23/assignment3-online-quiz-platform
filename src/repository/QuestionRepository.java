package repository;

import db.DatabaseConnection;
import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    public void create(Question q) {
        String sql = "INSERT INTO questions (quiz_id, text, correct_answer, points) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, q.getQuizId());
            ps.setString(2, q.getText());
            ps.setString(3, q.getCorrectAnswer());
            ps.setInt(4, q.getPoints());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create question", e);
        }
    }

    public Question getById(int id) {
        String sql = "SELECT * FROM questions WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToQuestion(rs);
                }
            }

            throw new ResourceNotFoundException("Question not found with id=" + id);

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get question by id", e);
        }
    }

    public List<Question> getAllByQuizId(int quizId) {
        String sql = "SELECT * FROM questions WHERE quiz_id = ? ORDER BY id";
        List<Question> questions = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quizId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    questions.add(mapRowToQuestion(rs));
                }
            }

            return questions;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get questions by quizId", e);
        }
    }

    public void update(int id, Question q) {
        String sql = "UPDATE questions SET quiz_id = ?, text = ?, correct_answer = ?, points = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, q.getQuizId());
            ps.setString(2, q.getText());
            ps.setString(3, q.getCorrectAnswer());
            ps.setInt(4, q.getPoints());
            ps.setInt(5, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ResourceNotFoundException("Question not found with id=" + id);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update question", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ResourceNotFoundException("Question not found with id=" + id);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete question", e);
        }
    }

    private Question mapRowToQuestion(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int quizId = rs.getInt("quiz_id");
        String text = rs.getString("text");
        String correctAnswer = rs.getString("correct_answer");
        int points = rs.getInt("points");

        return new Question(id, quizId, text, correctAnswer, points);
    }
    public boolean existsSameQuestion(int quizId, String text, String correctAnswer) {
        String sql = "SELECT 1 FROM questions WHERE quiz_id = ? AND text = ? AND correct_answer = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ps.setString(2, text);
            ps.setString(3, correctAnswer);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to check question duplicate", e);
        }
    }

}
