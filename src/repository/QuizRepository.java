package repository;

import db.DatabaseConnection;
import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.EasyQuiz;
import model.HardQuiz;
import model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {

    public void create(Quiz quiz) {
        String sql = "INSERT INTO quizzes (name, level) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, quiz.getName());
            ps.setString(2, quiz.getLevel());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create quiz", e);
        }
    }

    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM quizzes WHERE name = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to check quiz by name", e);
        }
    }

    public Quiz getById(int id) {
        String sql = "SELECT * FROM quizzes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToQuiz(rs);
                }
            }

            throw new ResourceNotFoundException("Quiz not found with id=" + id);

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get quiz by id", e);
        }
    }

    public List<Quiz> getAll() {
        String sql = "SELECT * FROM quizzes";
        List<Quiz> quizzes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                quizzes.add(mapRowToQuiz(rs));
            }

            return quizzes;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all quizzes", e);
        }
    }

    public void update(int id, Quiz quiz) {
        String sql = "UPDATE quizzes SET name = ?, level = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, quiz.getName());
            ps.setString(2, quiz.getLevel());
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ResourceNotFoundException("Quiz not found with id=" + id);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update quiz", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM quizzes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ResourceNotFoundException("Quiz not found with id=" + id);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete quiz", e);
        }
    }

    private Quiz mapRowToQuiz(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String level = rs.getString("level");

        if ("EASY".equalsIgnoreCase(level)) {
            return new EasyQuiz(id, name);
        } else if ("HARD".equalsIgnoreCase(level)) {
            return new HardQuiz(id, name);
        } else {
            throw new SQLException("Unknown quiz level: " + level);
        }
    }
}
