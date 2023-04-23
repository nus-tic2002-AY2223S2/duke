package exception;

/**
 * Represents a custom exception class for handling application-specific errors.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
