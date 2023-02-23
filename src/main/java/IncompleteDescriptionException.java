
public class IncompleteDescriptionException extends DukeException {
    private String message;
    public IncompleteDescriptionException(String msg) {
        super(msg);
    }

    public String toString() {
        return ("The task description is incorrect.");
    }

}



