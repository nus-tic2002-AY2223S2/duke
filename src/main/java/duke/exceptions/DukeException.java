package duke.exceptions;

/**
 * A custom exception class for Duke application.
 * DukeException is thrown to indicate that there is an error specific to Duke application.
 */
public class DukeException extends Exception{
    /**
     * Constructs a DukeException with the specified error message
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }
}