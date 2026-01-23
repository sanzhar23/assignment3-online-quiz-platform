import exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/quiz_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234"; // поставь свой пароль

    private DatabaseConnection() {
        // prevent instantiation
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to connect to database", e);
        }
    }
}
