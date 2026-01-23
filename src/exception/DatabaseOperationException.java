package exception;

public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException() {
        super();
    }

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseOperationException(Throwable cause) {
        super(cause);
    }
}
